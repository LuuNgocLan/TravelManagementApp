package luungoclan.min.traveltourmanagement.views.login;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.presenters.login.LoginPresenter;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.utils.Constants;
import luungoclan.min.traveltourmanagement.views.main.MainActivity;
import okhttp3.RequestBody;

import static android.content.Context.MODE_PRIVATE;

public class LoginActivity extends AppCompatActivity implements ILoginActivity, View.OnClickListener {

    private EditText edtUsername, edtPass;
    private TextView tvErrorSomethingWrong;
    private Button btnLogin;
    private LoginPresenter loginPresenter;
    private JSONObject jsonObject;
    private ProgressDialog progressDialog;
    private String username, password;
    private SharedPreferences sharedPreferences;
    private CheckBox cbRemember;
    private boolean isLoggingIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        loadSharedPreferences();
        setEvent();
    }

    private void setEvent() {
        btnLogin.setOnClickListener(this);
        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbRemember.isChecked()) {
                    saveInforLoginInSharedPreference(false);
                }
            }
        });
    }

    private void init() {
        sharedPreferences = getSharedPreferences(Constants.PREF_REMEMBER_ME, MODE_PRIVATE);
        loginPresenter = new LoginPresenter(this);
        tvErrorSomethingWrong = findViewById(R.id.tv_error_SomethingWrong);
        edtUsername = findViewById(R.id.edt_email);
        edtPass = findViewById(R.id.edt_password);
        cbRemember = findViewById(R.id.cb_remember);
        btnLogin = findViewById(R.id.btn_login);
        //init progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(true);

    }

    @Override
    public void getTokenSuccess(String token) {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), token, Toast.LENGTH_LONG).show();
        saveInforLoginInSharedPreference(true);
        MainActivity.isLoggingIn = true;
        finish();
    }

    @Override
    public void getTokenFailure_ServerError() {
        progressDialog.dismiss();
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void getTokenFailure_WrongData() {
        progressDialog.dismiss();
        tvErrorSomethingWrong.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (checkValidFieldUsernameAndPassword()) {
                    passDataLoginToJson();
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                    if (Common.isConnected(getBaseContext())) {
                        loginPresenter.getToken(body);
                        showProgressDialog(getString(R.string.message_logging_in));
                    } else {
                        showProgressDialog("No internet!");
                    }

                } else {

                }

                break;

        }
    }

    private boolean checkValidFieldUsernameAndPassword() {
        edtUsername.setError(null);
        edtPass.setError(null);
        boolean cancel = false;
        View focusView = null;
        username = edtUsername.getText().toString();
        password = edtPass.getText().toString();

        if (TextUtils.isEmpty(username)) {
            edtUsername.setError(getString(R.string.message_invalid_username_empty));
            focusView = edtUsername;
            cancel = true;
        }
        if (TextUtils.isEmpty(password)) {
            edtPass.setError(getString(R.string.message_invalid_password_empty));
            focusView = edtPass;
            cancel = true;
            return false;
        }

        return true;
    }

    private void showProgressDialog(String msg) {
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    private void passDataLoginToJson() {
        jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadSharedPreferences() {
        String email = sharedPreferences.getString(Constants.USERNAME, null);
        String pass = sharedPreferences.getString(Constants.PASSWORD, null);
        boolean isRemembered = sharedPreferences.getBoolean(Constants.REMEMBER_ME, false);
        cbRemember.setChecked(isRemembered);
        if (isRemembered) {
            if (email != null) {
                edtUsername.setText(email);
                edtPass.setText(pass);
            }
        } else {
            edtUsername.setText("");
            edtPass.setText("");
        }

    }

    /**
     * save data:  into shared preference to reuse
     *
     * @param isLoggingIn
     */
    private void saveInforLoginInSharedPreference(boolean isLoggingIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.IS_LOGGING_IN, isLoggingIn);
        if (cbRemember.isChecked()) {
            editor.putBoolean(Constants.REMEMBER_ME, true);
        } else {
            editor.putBoolean(Constants.REMEMBER_ME, false);
        }
        editor.putString(Constants.USERNAME, edtUsername.getText().toString());
        editor.putString(Constants.PASSWORD, edtPass.getText().toString());
        editor.commit();
    }

}

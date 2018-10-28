package luungoclan.min.traveltourmanagement.views.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import luungoclan.min.traveltourmanagement.utils.ValidateData;
import luungoclan.min.traveltourmanagement.views.main.MainActivity;
import okhttp3.RequestBody;

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
                if(cbRemember.isChecked()){
                    saveInforLoginInSharedPreference(false);
                }
            }
        });
    }

    private void init() {
        sharedPreferences = getSharedPreferences("rememberMe", MODE_PRIVATE);
        loginPresenter = new LoginPresenter(this);
        tvErrorSomethingWrong = findViewById(R.id.tv_error_SomethingWrong);
        edtUsername = findViewById(R.id.edt_email);
        edtPass = findViewById(R.id.edt_password);
        cbRemember = findViewById(R.id.cb_remember);
        btnLogin = findViewById(R.id.btn_login);
        //init progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);

    }

    @Override
    public void getTokenSuccess(String token) {
        progressDialog.dismiss();
        Toast.makeText(this, token, Toast.LENGTH_LONG).show();
        saveInforLoginInSharedPreference(true);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void getTokenFailure_ServerError() {
        progressDialog.dismiss();
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
                    loginPresenter.getToken(body);
                    showProgressDialog(getString(R.string.message_logging_in));
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
        String email = sharedPreferences.getString("username", null);
        String pass = sharedPreferences.getString("password", null);
        boolean isRemembered = sharedPreferences.getBoolean("remember", false);
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
        editor.putBoolean("isLoggingIn", isLoggingIn);
        if (cbRemember.isChecked()) {
            editor.putBoolean("remember", true);
        } else {
            editor.putBoolean("remember", false);
        }
        editor.putString("username", edtUsername.getText().toString());
        editor.putString("password", edtPass.getText().toString());
        editor.commit();
    }

}

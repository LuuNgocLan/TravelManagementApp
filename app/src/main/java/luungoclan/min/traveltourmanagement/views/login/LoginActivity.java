package luungoclan.min.traveltourmanagement.views.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfile;
import luungoclan.min.traveltourmanagement.presenters.login.LoginImpl;
import luungoclan.min.traveltourmanagement.presenters.myProfile.IMyProfileImpl;
import luungoclan.min.traveltourmanagement.presenters.myProfile.MyProfileImpl;
import luungoclan.min.traveltourmanagement.utils.Utils;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.views.myProfile.IProfileView;
import okhttp3.RequestBody;

import static luungoclan.min.traveltourmanagement.utils.Common.RESULT_LOGIN_SUCCESS;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener,IProfileView {

    private EditText edtUsername, edtPass;
    private TextView tvErrorSomethingWrong;
    private Button btnLogin;
    private LoginImpl loginPresenter;
    private JSONObject jsonObject;
    private ProgressDialog progressDialog;
    private String username, password;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private CheckBox cbRemember;
    private boolean isLoggingIn;
    private IMyProfileImpl iMyProfileImpl;

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
        sharedPreferences = getSharedPreferences(Common.PREF_REMEMBER_ME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        loginPresenter = new LoginImpl(this);
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
        Toast.makeText(getBaseContext(), token, Toast.LENGTH_LONG).show();
        saveInforLoginInSharedPreference(true);
        //save token to sharedPreference
        editor.putString(Common.TOKEN_SAVED, token);
        getProfileFromServer(token);
        editor.commit();
        setResult(RESULT_LOGIN_SUCCESS);
    }

    public void getProfileFromServer(String token) {
        iMyProfileImpl = new MyProfileImpl(this);
        iMyProfileImpl.onGetProfile(token);
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
    public void getProfileSuccess(MyProfile myProfile) {
        progressDialog.dismiss();
        if (myProfile != null) {
            Gson gson = new Gson();
            String myProfileJson = gson.toJson(myProfile);
            editor.putString(Common.MYPROFILE, myProfileJson);
            editor.commit();
        }
        finish();
    }

    @Override
    public void getProfileFailure() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (checkValidFieldUsernameAndPassword()) {
                    passDataLoginToJson();
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                    if (Utils.isConnected(getBaseContext())) {
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
        String email = sharedPreferences.getString(Common.USERNAME, null);
        String pass = sharedPreferences.getString(Common.PASSWORD, null);
        boolean isRemembered = sharedPreferences.getBoolean(Common.REMEMBER_ME, false);
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
        editor.putBoolean(Common.IS_LOGGING_IN, isLoggingIn);
        if (cbRemember.isChecked()) {
            editor.putBoolean(Common.REMEMBER_ME, true);
        } else {
            editor.putBoolean(Common.REMEMBER_ME, false);
        }
        editor.putString(Common.USERNAME, edtUsername.getText().toString());
        editor.putString(Common.PASSWORD, edtPass.getText().toString());
        editor.commit();
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onShowProgressDialog(String msg) {

    }

    @Override
    public void onDismissProgressDialog() {

    }
}

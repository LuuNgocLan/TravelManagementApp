package luungoclan.min.traveltourmanagement.views.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setEvent();
    }

    private void setEvent() {
        btnLogin.setOnClickListener(this);
    }

    private void init() {
        loginPresenter = new LoginPresenter(this);
        tvErrorSomethingWrong = findViewById(R.id.tv_error_SomethingWrong);
        edtUsername = findViewById(R.id.edt_email);
        edtPass = findViewById(R.id.edt_password);
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
        saveTokenToSharePreference();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void saveTokenToSharePreference() {
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
}

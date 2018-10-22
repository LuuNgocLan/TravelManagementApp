package luungoclan.min.traveltourmanagement.presenters.login;

import android.util.Log;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.models.login.LoginResponse;
import luungoclan.min.traveltourmanagement.views.login.ILoginActivity;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILoginPresenter {

    public final String TAG = "LOGIN_PRESENTER";
    private ILoginActivity iLoginActivity;
    public static String token = null;

    public LoginPresenter(ILoginActivity iLoginActivity) {
        this.iLoginActivity = iLoginActivity;
    }


    @Override
    public void getToken(RequestBody json) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> call = apiService.getToken(json);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d(TAG, String.valueOf(response.code()));
                if (response.code() >= 300) {
                    if (response.code() == 422) {
                        iLoginActivity.getTokenFailure_WrongData();
                    }
                    if (response.code() == 500){
                        iLoginActivity.getTokenFailure_ServerError();
                    }

                } else if (response.code() == 200) {
                    //Save token
                    token = response.body().getData();
                    iLoginActivity.getTokenSuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d(TAG, "failure");
                iLoginActivity.getTokenFailure_ServerError();
            }
        });
    }
}

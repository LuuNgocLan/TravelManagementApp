package luungoclan.min.traveltourmanagement.presenters.login;

import android.util.Log;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.base.BasePresenter;
import luungoclan.min.traveltourmanagement.models.login.LoginResponse;
import luungoclan.min.traveltourmanagement.views.login.ILoginView;
import luungoclan.min.traveltourmanagement.views.main.MainActivity;
import luungoclan.min.traveltourmanagement.views.myProfile.IProfileView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginImpl extends BasePresenter<ILoginView> implements ILoginImpl {

    public final String TAG = "LOGIN_PRESENTER";

    public LoginImpl(ILoginView view) {
        super(view);
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
                    view.getTokenFailure_ServerError();
                } else if (response.code() == 200) {
                    if (response.body().getResultCode() == 422) {
                        view.getTokenFailure_WrongData();
                    }
                    if (response.body().getResultCode() == 500) {
                        view.getTokenFailure_ServerError();
                    }
                    if (response.body().getResultCode() == 200) {
                        //Save token
                        MainActivity.token = response.body().getData();
                        view.getTokenSuccess(response.body().getData());
                    }
                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d(TAG, "failure");
                view.getTokenFailure_ServerError();
            }
        });
    }
}

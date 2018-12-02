package luungoclan.min.traveltourmanagement.presenters.myAccount;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.base.BasePresenter;
import luungoclan.min.traveltourmanagement.models.logout.LogoutResponse;
import luungoclan.min.traveltourmanagement.views.myAccount.IMyAccountFragment;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutImpl extends BasePresenter<IMyAccountFragment> implements ILogoutImpl {

    public LogoutImpl(IMyAccountFragment view) {
        super(view);
    }

    @Override
    public void logout(String token, RequestBody json) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LogoutResponse> call = apiService.logout(token, json);
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if (response.code() >= 300) {
                    view.logoutFailure("Log out failure!");
                } else if (response.code() == 200) {
                    if (response.body().getResultCode() == 200) {
                        view.logoutSuccess(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                view.logoutFailure("Logout failure!");
            }
        });
    }
}


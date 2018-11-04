package luungoclan.min.traveltourmanagement.presenters.myAccount;

import android.util.Log;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.models.logout.LogoutResponse;
import luungoclan.min.traveltourmanagement.views.myAccount.IMyAccountFragment;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAccountPresenter implements IMyAccountPresenter {
    private IMyAccountFragment iMyAccountFragment;

    public MyAccountPresenter(IMyAccountFragment iMyAccountFragment) {
        this.iMyAccountFragment = iMyAccountFragment;
    }

    @Override
    public void logout(String token, RequestBody json) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LogoutResponse> call = apiService.logout(token, json);
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if (response.code() >= 300) {
                    iMyAccountFragment.logoutFailure("Log out failure!");
                } else if (response.code() == 200) {
                    if (response.body().getResultCode() == 200) {
                        iMyAccountFragment.logoutSuccess(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                iMyAccountFragment.logoutFailure("Logout failure!");
            }
        });
    }
}


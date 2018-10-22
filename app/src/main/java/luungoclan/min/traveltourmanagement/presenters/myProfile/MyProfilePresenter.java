package luungoclan.min.traveltourmanagement.presenters.myProfile;

import android.util.Log;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.models.login.LoginResponse;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfileResponse;
import luungoclan.min.traveltourmanagement.views.myProfile.IMyProfileActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfilePresenter implements IMyProfilePresenter {
    public static String TAG = "MY PROFILE PRESENTER";
    private IMyProfileActivity iMyProfileActivity;

    public MyProfilePresenter(IMyProfileActivity iMyProfileActivity) {
        this.iMyProfileActivity = iMyProfileActivity;
    }

    @Override
    public void getMyProfile(String token) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MyProfileResponse> call = apiService.getMyProfile(token);
        call.enqueue(new Callback<MyProfileResponse>() {
            @Override
            public void onResponse(Call<MyProfileResponse> call, Response<MyProfileResponse> response) {
                Log.d(TAG, String.valueOf(response.code()));
                if (response.code() >= 300) {
                    iMyProfileActivity.getMyProfileFailure();
                } else if (response.code() == 200) {
                    iMyProfileActivity.getMyProfileSuccess(response.body().getMyProfile());
                }
            }

            @Override
            public void onFailure(Call<MyProfileResponse> call, Throwable t) {
                Log.d(TAG, "failure");
                iMyProfileActivity.getMyProfileFailure();
            }
        });

    }
}

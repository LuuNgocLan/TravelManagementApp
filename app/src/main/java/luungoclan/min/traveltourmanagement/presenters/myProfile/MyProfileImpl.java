package luungoclan.min.traveltourmanagement.presenters.myProfile;

import android.util.Log;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.base.BasePresenter;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfileResponse;
import luungoclan.min.traveltourmanagement.views.login.ILoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileImpl extends BasePresenter<ILoginActivity> implements IMyProfileImpl {
    public static String TAG = "MY PROFILE PRESENTER";

    public MyProfileImpl(ILoginActivity view) {
        super(view);
    }


    @Override
    public void onGetProfile(String token) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MyProfileResponse> call = apiService.getMyProfile(token);
        call.enqueue(new Callback<MyProfileResponse>() {
            @Override
            public void onResponse(Call<MyProfileResponse> call, Response<MyProfileResponse> response) {
                Log.d(TAG, String.valueOf(response.code()));
                if (response.code() >= 300) {
                    if (view != null) {
                        view.getProfileFailure();
                    }
                } else if (response.code() == 200) {
                    view.getProfileSuccess(response.body().getMyProfile());
                }
            }

            @Override
            public void onFailure(Call<MyProfileResponse> call, Throwable t) {
                Log.d(TAG, "failure");
                view.getProfileFailure();
            }
        });

    }

}

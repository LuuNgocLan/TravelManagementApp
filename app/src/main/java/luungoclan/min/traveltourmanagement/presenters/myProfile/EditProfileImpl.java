package luungoclan.min.traveltourmanagement.presenters.myProfile;

import android.util.Log;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.base.BasePresenter;
import luungoclan.min.traveltourmanagement.models.myProfile.EditProfileResponse;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfileResponse;
import luungoclan.min.traveltourmanagement.views.myProfile.IEditProfileView;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileImpl extends BasePresenter<IEditProfileView> implements IEditProfileImpl {
    public EditProfileImpl(IEditProfileView view) {
        super(view);
    }

    @Override
    public void onEditProfile(String token,
                              MultipartBody.Part avatar,
                              MultipartBody.Part fullname,
                              MultipartBody.Part email,
                              MultipartBody.Part phone,
                              MultipartBody.Part addr) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<EditProfileResponse> call = apiService.editProfile(token, avatar, fullname, email, phone, addr);
        call.enqueue(new Callback<EditProfileResponse>() {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                if (response.code() >= 300) {
                    if (view != null) {
                        view.editProfileFailure();
                    }
                } else if (response.code() == 200) {
                    view.editProfileSuccess();
                }
            }

            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                view.editProfileFailure();
            }
        });

    }

    @Override
    public void onChangePassword(String token, MultipartBody.Part password) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<EditProfileResponse> call = apiService.changePassword(token, password);
        call.enqueue(new Callback<EditProfileResponse>() {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                if (response.code() >= 300) {
                    if (view != null) {
                        view.editProfileFailure();
                    }
                } else if (response.code() == 200) {
                    view.editProfileSuccess();
                }
            }

            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                view.editProfileFailure();
            }
        });

    }

}

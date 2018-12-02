package luungoclan.min.traveltourmanagement.presenters.myProfile;

import okhttp3.MultipartBody;

public interface IEditProfileImpl {

    public void onEditProfile(String token,
                              MultipartBody.Part avatar,
                              MultipartBody.Part fullname,
                              MultipartBody.Part email,
                              MultipartBody.Part phone,
                              MultipartBody.Part addr
    );

    public void onChangePassword(String token, MultipartBody.Part password);
}

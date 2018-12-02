package luungoclan.min.traveltourmanagement.views.login;

import luungoclan.min.traveltourmanagement.base.IBaseView;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfile;

public interface ILoginActivity extends IBaseView {
    public void getTokenSuccess(String token);

    public void getTokenFailure_ServerError();

    public void getTokenFailure_WrongData();

    public void getProfileSuccess(MyProfile myProfile);

    public void getProfileFailure();
}

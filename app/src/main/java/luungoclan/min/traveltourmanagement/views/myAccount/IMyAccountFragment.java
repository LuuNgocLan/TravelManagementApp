package luungoclan.min.traveltourmanagement.views.myAccount;

import luungoclan.min.traveltourmanagement.base.IBaseView;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfile;

public interface IMyAccountFragment extends IBaseView {
    public void logoutSuccess(String msg);

    public void logoutFailure(String msg);

}

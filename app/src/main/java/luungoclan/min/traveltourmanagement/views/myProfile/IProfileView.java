package luungoclan.min.traveltourmanagement.views.myProfile;

import luungoclan.min.traveltourmanagement.base.IBaseView;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfile;

public interface IProfileView extends IBaseView {
    public void getProfileSuccess(MyProfile myProfile);

    public void getProfileFailure();
}

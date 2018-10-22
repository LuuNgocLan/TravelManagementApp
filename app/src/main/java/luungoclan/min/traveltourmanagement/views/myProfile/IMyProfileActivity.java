package luungoclan.min.traveltourmanagement.views.myProfile;

import luungoclan.min.traveltourmanagement.models.myProfile.MyProfile;

public interface IMyProfileActivity {

    public void getMyProfileSuccess(MyProfile myProfile);

    public void getMyProfileFailure();
}

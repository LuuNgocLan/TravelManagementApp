package luungoclan.min.traveltourmanagement.presenters.myAccount;

import okhttp3.RequestBody;

public interface IMyAccountPresenter {
    public void logout(String token, RequestBody json);
}

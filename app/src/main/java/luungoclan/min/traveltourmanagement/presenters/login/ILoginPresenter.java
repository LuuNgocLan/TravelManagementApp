package luungoclan.min.traveltourmanagement.presenters.login;

import okhttp3.RequestBody;

public interface ILoginPresenter {
    public void getToken(RequestBody json);
}

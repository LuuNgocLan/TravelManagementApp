package luungoclan.min.traveltourmanagement.views.login;

public interface ILoginActivity {
    public void getTokenSuccess(String token);

    public void getTokenFailure_ServerError();

    public void getTokenFailure_WrongData();
}

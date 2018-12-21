package luungoclan.min.traveltourmanagement.presenters.booking;

import okhttp3.RequestBody;

public interface IAddBoookingImpl {

    public void addNewBooking(String token, RequestBody body);
}

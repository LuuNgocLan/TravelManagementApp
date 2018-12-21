package luungoclan.min.traveltourmanagement.presenters.booking;

import okhttp3.RequestBody;

public interface ICancelBookingImpl {
    public void cancelMyBooking(String token, RequestBody json);
}

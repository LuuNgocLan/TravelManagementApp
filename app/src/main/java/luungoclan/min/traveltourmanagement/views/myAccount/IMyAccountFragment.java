package luungoclan.min.traveltourmanagement.views.myAccount;

import java.util.List;

import luungoclan.min.traveltourmanagement.base.IBaseView;
import luungoclan.min.traveltourmanagement.models.booking.Booking;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfile;

public interface IMyAccountFragment extends IBaseView {
    public void logoutSuccess(String msg);

    public void logoutFailure(String msg);

    public void getListBookingSuccess(List<Booking> bookingList);

    public void getListBookingFailure();

}

package luungoclan.min.traveltourmanagement.presenters.booking;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.base.BasePresenter;
import luungoclan.min.traveltourmanagement.models.booking.BookingResponse;
import luungoclan.min.traveltourmanagement.models.reviewOfUser.addReview.AddReviewResponse;
import luungoclan.min.traveltourmanagement.views.booking.BookingView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookingImpl extends BasePresenter<BookingView> implements IAddBoookingImpl {
    public AddBookingImpl(BookingView view) {
        super(view);
    }

    @Override
    public void addNewBooking(String token, RequestBody body) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BookingResponse> call = apiService.addBooking(token, body);
        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                if (response.code() >= 300) {
                    view.addBookingFailure();
                } else if (response.code() == 200) {
                    view.addBookingSuccess();
                }
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                view.addBookingFailure();
            }
        });
    }
}

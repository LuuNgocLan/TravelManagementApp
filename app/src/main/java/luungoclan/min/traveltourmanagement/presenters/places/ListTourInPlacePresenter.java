package luungoclan.min.traveltourmanagement.presenters.places;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.models.places.PlaceResponse;
import luungoclan.min.traveltourmanagement.models.places.tourInPlace.TourInPlaceResponse;
import luungoclan.min.traveltourmanagement.views.places.IListTourInPlaceView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTourInPlacePresenter implements IListTourInPlacePresenter {
    private IListTourInPlaceView iListTourInPlaceView;

    public ListTourInPlacePresenter(IListTourInPlaceView iListTourInPlaceView) {
        this.iListTourInPlaceView = iListTourInPlaceView;
    }

    @Override
    public void getListTourInPlace(final int idPlace) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TourInPlaceResponse> call = apiService.getTourInPlace(idPlace);
        call.enqueue(new Callback<TourInPlaceResponse>() {
            @Override
            public void onResponse(Call<TourInPlaceResponse> call, Response<TourInPlaceResponse> response) {
                if (response.body().getResultCode() >= 300) {
                    iListTourInPlaceView.getListTourInPlaceFailure();
                } else if (response.body().getResultCode() == 200) {
                    iListTourInPlaceView.getListTourInPlaceSuccess(response.body().getDataTour());
                }
            }

            @Override
            public void onFailure(Call<TourInPlaceResponse> call, Throwable t) {
                iListTourInPlaceView.getListTourInPlaceFailure();
            }
        });

    }
}

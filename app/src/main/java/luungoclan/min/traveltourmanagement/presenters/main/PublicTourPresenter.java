package luungoclan.min.traveltourmanagement.presenters.main;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.models.tourList.TourListResponse;
import luungoclan.min.traveltourmanagement.views.publicTour.IPublicTourFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicTourPresenter implements IPublicTourPresenter {

    private IPublicTourFragment iPublicTourFragment;

    public PublicTourPresenter(IPublicTourFragment iPublicTourFragment) {
        this.iPublicTourFragment = iPublicTourFragment;
    }

    @Override
    public void getSaleTour() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TourListResponse> call = apiService.getTourSaleList();
        call.enqueue(new Callback<TourListResponse>() {
            @Override
            public void onResponse(Call<TourListResponse> call, Response<TourListResponse> response) {
                if (response.body().getResultCode() >= 300) {
                    iPublicTourFragment.getListSaleTourFailure();
                } else if (response.body().getResultCode() == 200) {
                    iPublicTourFragment.getListSaleTourSuccess(response.body().getDataTourList());
                }
            }

            @Override
            public void onFailure(Call<TourListResponse> call, Throwable t) {
                iPublicTourFragment.getListSaleTourFailure();
            }
        });

    }
}

package luungoclan.min.traveltourmanagement.presenters.tour;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.models.places.PlaceResponse;
import luungoclan.min.traveltourmanagement.models.tourList.SearchTourResponse;
import luungoclan.min.traveltourmanagement.models.tourList.TourListResponse;
import luungoclan.min.traveltourmanagement.views.publicTour.IPublicTourFragment;
import okhttp3.RequestBody;
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

    @Override
    public void getLatestTour() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TourListResponse> call = apiService.getTourSaleList();
        call.enqueue(new Callback<TourListResponse>() {
            @Override
            public void onResponse(Call<TourListResponse> call, Response<TourListResponse> response) {
                if (response.body().getResultCode() >= 300) {
                    iPublicTourFragment.getListLatestTourFailure();
                } else if (response.body().getResultCode() == 200) {
                    iPublicTourFragment.getListLatestTourSuccess(response.body().getDataTourList());
                }
            }

            @Override
            public void onFailure(Call<TourListResponse> call, Throwable t) {
                iPublicTourFragment.getListLatestTourFailure();
            }
        });

    }

    @Override
    public void getFavoritePlace() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<PlaceResponse> call = apiService.getTopFavoritePlaces();
        call.enqueue(new Callback<PlaceResponse>() {
            @Override
            public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                if (response.body().getResultCode() >= 300) {
                    iPublicTourFragment.getListTopPlaceFailure();
                } else if (response.body().getResultCode() == 200) {
                    iPublicTourFragment.getListTopPlacesSuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {
                iPublicTourFragment.getListTopPlaceFailure();
            }
        });
    }

    @Override
    public void getSearchResult(RequestBody body) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SearchTourResponse> call = apiService.getResultSearch(body);
        call.enqueue(new Callback<SearchTourResponse>() {
            @Override
            public void onResponse(Call<SearchTourResponse> call, Response<SearchTourResponse> response) {
                if (response.body().getResultCode() >= 300) {
                    iPublicTourFragment.searchTourFailure();
                } else if (response.body().getResultCode() == 200) {
                    iPublicTourFragment.searchTourSuccess(response.body().getData().getTour());
                }
            }

            @Override
            public void onFailure(Call<SearchTourResponse> call, Throwable t) {
                iPublicTourFragment.searchTourFailure();
            }
        });
    }
}

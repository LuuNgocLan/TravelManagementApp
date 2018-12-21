package luungoclan.min.traveltourmanagement.presenters.tour;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.base.BasePresenter;
import luungoclan.min.traveltourmanagement.models.places.PlaceResponse;
import luungoclan.min.traveltourmanagement.models.slide.SlidesResponse;
import luungoclan.min.traveltourmanagement.models.tourList.SearchTourResponse;
import luungoclan.min.traveltourmanagement.models.tourList.TourListResponse;
import luungoclan.min.traveltourmanagement.views.publicTour.IPublicTourView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicTourImpl extends BasePresenter<IPublicTourView> implements IPublicTourImpl {

    public PublicTourImpl(IPublicTourView view) {
        super(view);
    }

    @Override
    public void getSaleTour() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TourListResponse> call = apiService.getTourSaleList();
        call.enqueue(new Callback<TourListResponse>() {
            @Override
            public void onResponse(Call<TourListResponse> call, Response<TourListResponse> response) {
                if (response.body().getResultCode() >= 300) {
                    view.getListSaleTourFailure();
                } else if (response.body().getResultCode() == 200) {
                    view.getListSaleTourSuccess(response.body().getDataTourList());
                }
            }

            @Override
            public void onFailure(Call<TourListResponse> call, Throwable t) {
                view.getListSaleTourFailure();
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
                    view.getListLatestTourFailure();
                } else if (response.body().getResultCode() == 200) {
                    view.getListLatestTourSuccess(response.body().getDataTourList());
                }
            }

            @Override
            public void onFailure(Call<TourListResponse> call, Throwable t) {
                view.getListLatestTourFailure();
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
                    view.getListTopPlaceFailure();
                } else if (response.body().getResultCode() == 200) {
                    view.getListTopPlacesSuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {
                view.getListTopPlaceFailure();
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
                    view.searchTourFailure();
                } else if (response.body().getResultCode() == 200) {
                    view.searchTourSuccess(response.body().getData().getTour());
                }
            }

            @Override
            public void onFailure(Call<SearchTourResponse> call, Throwable t) {
                view.searchTourFailure();
            }
        });
    }

    @Override
    public void getAllSlides() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SlidesResponse> call = apiService.getAllSlides();
        call.enqueue(new Callback<SlidesResponse>() {
            @Override
            public void onResponse(Call<SlidesResponse> call, Response<SlidesResponse> response) {
                if (response.body().getResultCode() >= 300) {
                    view.getAllSlideFailure();
                } else if (response.body().getResultCode() == 200) {
                    view.getAllSlideSuccess(response.body().getData().getList());
                }
            }

            @Override
            public void onFailure(Call<SlidesResponse> call, Throwable t) {
                view.getAllSlideFailure();
            }
        });
    }
}

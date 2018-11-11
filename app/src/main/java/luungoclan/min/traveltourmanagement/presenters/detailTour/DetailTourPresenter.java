package luungoclan.min.traveltourmanagement.presenters.detailTour;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.models.detailTour.DetailTourResponse;
import luungoclan.min.traveltourmanagement.models.detailTour.TourAnotherDayResponse;
import luungoclan.min.traveltourmanagement.models.reviewOfTour.ReviewOfTourResponse;
import luungoclan.min.traveltourmanagement.views.detailTour.IDetailTourActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTourPresenter implements IDetailTourPresenter {
    private IDetailTourActivity iDetailTourActivity;

    public DetailTourPresenter(IDetailTourActivity iDetailTourActivity) {
        this.iDetailTourActivity = iDetailTourActivity;
    }

    @Override
    public void getDetailTour(int id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DetailTourResponse> call = apiService.getDetailTour(id);
        call.enqueue(new Callback<DetailTourResponse>() {
            @Override
            public void onResponse(Call<DetailTourResponse> call, Response<DetailTourResponse> response) {
                if (response.code() >= 300) {
                    iDetailTourActivity.getDetailTourFailure();
                } else if (response.code() == 200) {
                    iDetailTourActivity.getDetailTourSuccess(response.body().getDataDetailTour());
                }
            }

            @Override
            public void onFailure(Call<DetailTourResponse> call, Throwable t) {
                iDetailTourActivity.getDetailTourFailure();
            }
        });

    }

    @Override
    public void getListReviewOfTour(int idTour) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ReviewOfTourResponse> call = apiService.getReviewOfTour(idTour);
        call.enqueue(new Callback<ReviewOfTourResponse>() {
            @Override
            public void onResponse(Call<ReviewOfTourResponse> call, Response<ReviewOfTourResponse> response) {
                if (response.code() >= 300) {
                    iDetailTourActivity.getDetailTourFailure();
                } else if (response.code() == 200) {
                    iDetailTourActivity.getListReviewOfTourSuccess(response.body().getDataReview());
                }
            }

            @Override
            public void onFailure(Call<ReviewOfTourResponse> call, Throwable t) {
                iDetailTourActivity.getListReviewOfTourFailure();
            }
        });
    }

    @Override
    public void getListTourChangeDate(int idTour) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TourAnotherDayResponse> call = apiService.getAnotherDayOfTour(idTour);
        call.enqueue(new Callback<TourAnotherDayResponse>() {
            @Override
            public void onResponse(Call<TourAnotherDayResponse> call, Response<TourAnotherDayResponse> response) {
                if (response.code() >= 300) {
                    iDetailTourActivity.getDetailTourFailure();
                } else if (response.code() == 200) {
                    iDetailTourActivity.getAnotherTourSuccess(response.body().getDataTour());
                }
            }

            @Override
            public void onFailure(Call<TourAnotherDayResponse> call, Throwable t) {
                iDetailTourActivity.getListReviewOfTourFailure();
            }
        });
    }
}

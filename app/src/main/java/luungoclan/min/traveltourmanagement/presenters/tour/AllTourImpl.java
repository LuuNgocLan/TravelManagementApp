package luungoclan.min.traveltourmanagement.presenters.tour;

import android.icu.util.ValueIterator;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.base.BasePresenter;
import luungoclan.min.traveltourmanagement.models.tourList.AllTourResponse;
import luungoclan.min.traveltourmanagement.models.tourList.SearchTourResponse;
import luungoclan.min.traveltourmanagement.views.tours.IAllTourView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTourImpl extends BasePresenter<IAllTourView> implements IAllTourImpl {
    public AllTourImpl(IAllTourView view) {
        super(view);
    }

    @Override
    public void onGetAllTour() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AllTourResponse> call = apiService.getAllTour();
        call.enqueue(new Callback<AllTourResponse>() {
            @Override
            public void onResponse(Call<AllTourResponse> call, Response<AllTourResponse> response) {
                if (response.body().getResultCode() >= 300) {
                    view.getAllTourFailure();
                } else if (response.body().getResultCode() == 200) {
                    view.getAllTourSuccess(response.body().getData().getTour());
                }
            }

            @Override
            public void onFailure(Call<AllTourResponse> call, Throwable t) {
                view.getAllTourFailure();
            }
        });
    }
}

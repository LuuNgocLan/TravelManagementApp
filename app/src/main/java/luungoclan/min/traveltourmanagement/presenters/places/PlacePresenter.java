package luungoclan.min.traveltourmanagement.presenters.places;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.base.BasePresenter;
import luungoclan.min.traveltourmanagement.models.places.PlaceResponse;
import luungoclan.min.traveltourmanagement.views.places.IPlaceView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacePresenter extends BasePresenter<IPlaceView> implements IPlaceImpl {

    public PlacePresenter(IPlaceView view) {
        super(view);
    }

    @Override
    public void getPlaceList() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<PlaceResponse> call = apiService.getPlaceList();
        call.enqueue(new Callback<PlaceResponse>() {
            @Override
            public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                if (response.body().getResultCode() >= 300) {
                    view.getPlaceListFailure();
                } else if (response.body().getResultCode() == 200) {
                    view.getPlaceListSuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {
                view.getPlaceListFailure();
            }
        });

    }
}

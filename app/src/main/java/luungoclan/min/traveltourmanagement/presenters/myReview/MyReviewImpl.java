package luungoclan.min.traveltourmanagement.presenters.myReview;

import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.base.BasePresenter;
import luungoclan.min.traveltourmanagement.models.reviewOfUser.ReviewOfUserResponse;
import luungoclan.min.traveltourmanagement.views.myReview.IMyReviewView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReviewImpl extends BasePresenter<IMyReviewView> implements IMyReviewImpl {

    public MyReviewImpl(IMyReviewView view) {
        super(view);
    }

    @Override
    public void getMyReview(String token) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ReviewOfUserResponse> call = apiService.getReviewOfUser(token);
        call.enqueue(new Callback<ReviewOfUserResponse>() {
            @Override
            public void onResponse(Call<ReviewOfUserResponse> call, Response<ReviewOfUserResponse> response) {
                if (response.code() >= 300) {
                    view.getMyReviewFailure();
                } else if (response.code() == 200) {
                    view.getMyDataReviewSuccess(response.body().getReview());
                }
            }

            @Override
            public void onFailure(Call<ReviewOfUserResponse> call, Throwable t) {
                view.getMyReviewFailure();
            }
        });
    }
}

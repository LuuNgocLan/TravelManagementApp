package luungoclan.min.traveltourmanagement.views.myReview;

import java.util.List;

import luungoclan.min.traveltourmanagement.base.IBaseView;
import luungoclan.min.traveltourmanagement.models.reviewOfUser.Review;

public interface IMyReviewView extends IBaseView {
    public void getMyDataReviewSuccess(List<Review> reviewList);

    public void getMyReviewFailure();
}

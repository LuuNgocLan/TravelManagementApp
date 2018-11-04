package luungoclan.min.traveltourmanagement.views.detailTour;

import luungoclan.min.traveltourmanagement.models.detailTour.DataDetailTour;
import luungoclan.min.traveltourmanagement.models.reviewOfTour.DataReview;

public interface IDetailTourActivity {
    public void getDetailTourSuccess(DataDetailTour dataDetailTour);

    public void getDetailTourFailure();

    public void getListReviewOfTourSuccess(DataReview dataReview);

    public void getListReviewOfTourFailure();

}

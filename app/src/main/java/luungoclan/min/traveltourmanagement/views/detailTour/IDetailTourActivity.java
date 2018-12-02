package luungoclan.min.traveltourmanagement.views.detailTour;

import java.util.List;

import luungoclan.min.traveltourmanagement.models.detailTour.DataDetailTour;
import luungoclan.min.traveltourmanagement.models.detailTour.DataTour;
import luungoclan.min.traveltourmanagement.models.detailTour.Detail;
import luungoclan.min.traveltourmanagement.models.reviewOfTour.DataReview;
import retrofit2.http.PUT;

public interface IDetailTourActivity {
    public void getDetailTourSuccess(DataDetailTour dataDetailTour);

    public void getDetailTourFailure();

    public void getListReviewOfTourSuccess(DataReview dataReview);

    public void getListReviewOfTourFailure();

    public void getAnotherTourSuccess(List<DataTour> dataTour);

    public void getAnotherTourFailure();

    public void addReviewSuccess();

    public void addReviewFailure();
}

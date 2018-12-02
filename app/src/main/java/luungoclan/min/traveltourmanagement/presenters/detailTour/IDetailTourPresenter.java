package luungoclan.min.traveltourmanagement.presenters.detailTour;

import okhttp3.RequestBody;

public interface IDetailTourPresenter {
    public void getDetailTour(int idTour);

    public void getListReviewOfTour(int idTour);

    public void getListTourChangeDate(int idTour);

    public void addReview(String token, RequestBody json);
}

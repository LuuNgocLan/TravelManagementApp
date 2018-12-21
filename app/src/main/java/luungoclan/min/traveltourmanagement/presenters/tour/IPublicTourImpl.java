package luungoclan.min.traveltourmanagement.presenters.tour;

import okhttp3.RequestBody;

public interface IPublicTourImpl {
    public void getSaleTour();

    public void getLatestTour();

    public void getFavoritePlace();

    public void getSearchResult(RequestBody body);

    public void getAllSlides();
}

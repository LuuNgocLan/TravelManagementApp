package luungoclan.min.traveltourmanagement.views.publicTour;

import java.util.List;

import luungoclan.min.traveltourmanagement.base.IBaseView;
import luungoclan.min.traveltourmanagement.models.places.PlaceData;
import luungoclan.min.traveltourmanagement.models.slide.Slide;
import luungoclan.min.traveltourmanagement.models.tourList.DataTourList;
import luungoclan.min.traveltourmanagement.models.tourList.Tour;

public interface IPublicTourView extends IBaseView {
    public void getListSaleTourSuccess(DataTourList dataTourList);

    public void getListSaleTourFailure();

    public void getListLatestTourSuccess(DataTourList dataTourList);

    public void getListLatestTourFailure();

    public void getListTopPlacesSuccess(PlaceData placeData);

    public void getListTopPlaceFailure();

    public void searchTourSuccess(List<Tour> tourList);

    public void searchTourFailure();

    public void getAllSlideSuccess(List<Slide> slides);

    public void getAllSlideFailure();

}

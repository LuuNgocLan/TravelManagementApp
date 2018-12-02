package luungoclan.min.traveltourmanagement.views.publicTour;

import java.util.List;

import luungoclan.min.traveltourmanagement.models.places.PlaceData;
import luungoclan.min.traveltourmanagement.models.tourList.DataTourList;
import luungoclan.min.traveltourmanagement.models.tourList.Tour;

public interface IPublicTourFragment {
    public void getListSaleTourSuccess(DataTourList dataTourList);

    public void getListSaleTourFailure();

    public void getListLatestTourSuccess(DataTourList dataTourList);

    public void getListLatestTourFailure();

    public void getListTopPlacesSuccess(PlaceData placeData);

    public void getListTopPlaceFailure();

    public void searchTourSuccess(List<Tour> tourList);

    public void searchTourFailure();

}

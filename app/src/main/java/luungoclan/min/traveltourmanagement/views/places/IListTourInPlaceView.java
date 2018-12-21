package luungoclan.min.traveltourmanagement.views.places;

import luungoclan.min.traveltourmanagement.models.tourList.tourInPlace.DataTour;

public interface IListTourInPlaceView {
    public void getListTourInPlaceSuccess(DataTour dataTour);
    public void getListTourInPlaceFailure();
}

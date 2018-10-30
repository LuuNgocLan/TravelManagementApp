package luungoclan.min.traveltourmanagement.views.publicTour;

import luungoclan.min.traveltourmanagement.models.tourList.DataTourList;

public interface IPublicTourFragment {
    public void getListSaleTourSuccess(DataTourList dataTourList);
    public void getListSaleTourFailure();
}

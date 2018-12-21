package luungoclan.min.traveltourmanagement.views.tours;

import java.util.List;

import luungoclan.min.traveltourmanagement.base.IBaseView;
import luungoclan.min.traveltourmanagement.models.tourList.Tour;

public interface IAllTourView extends IBaseView {
    public void getAllTourSuccess(List<Tour> tourList);

    public void getAllTourFailure();

}

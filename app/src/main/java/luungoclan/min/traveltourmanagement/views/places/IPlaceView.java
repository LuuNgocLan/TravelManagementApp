package luungoclan.min.traveltourmanagement.views.places;

import luungoclan.min.traveltourmanagement.base.IBaseView;
import luungoclan.min.traveltourmanagement.models.places.PlaceData;

public interface IPlaceView extends IBaseView {
    public void getPlaceListSuccess(PlaceData placeData);

    public void getPlaceListFailure();
}

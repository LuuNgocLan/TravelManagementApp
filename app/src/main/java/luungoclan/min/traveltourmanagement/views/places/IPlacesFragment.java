package luungoclan.min.traveltourmanagement.views.places;

import luungoclan.min.traveltourmanagement.models.places.PlaceData;

public interface IPlacesFragment {
    public void getPlaceListSuccess(PlaceData placeData);

    public void getPlaceListFailure();
}

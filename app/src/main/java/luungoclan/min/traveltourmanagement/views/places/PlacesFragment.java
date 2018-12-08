package luungoclan.min.traveltourmanagement.views.places;


import android.os.Bundle;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.placeAdapter.PlaceAdapter;
import luungoclan.min.traveltourmanagement.models.places.PlaceData;
import luungoclan.min.traveltourmanagement.presenters.places.PlacePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesFragment extends Fragment implements IPlaceView {
    @BindView(R.id.gv_places)
    HorizontalGridView gvPlaces;
    @BindView(R.id.indicatorView)
    AVLoadingIndicatorView indicatorView;

    private PlaceAdapter placeAdapter;
    private PlacePresenter placePresenter;
    private PlaceData placeData;

    public PlacesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places, container, false);
        ButterKnife.bind(this, view);
        init(view);
        initPresenter();
        getDataPlaces();
        setEvent();
        return view;
    }

    private void getDataPlaces() {
        placePresenter.getPlaceList();
    }

    private void initPresenter() {
        placePresenter = new PlacePresenter(this);
    }

    private void setEvent() {
    }

    private void init(View view) {
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        gvPlaces.setLayoutAnimation(animation);
        gvPlaces.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
    }

    @Override
    public void getPlaceListSuccess(PlaceData placeData) {
        this.placeData = placeData;
        if (placeData.getTotal() > 0) {
            //load list place to gridview
            gvPlaces.setAdapter(new PlaceAdapter(getContext(), placeData.getPlace()));
        }
        indicatorView.smoothToHide();

    }

    @Override
    public void getPlaceListFailure() {

    }

    @Override
    public void onShowProgressDialog(String msg) {

    }

    @Override
    public void onDismissProgressDialog() {

    }
}

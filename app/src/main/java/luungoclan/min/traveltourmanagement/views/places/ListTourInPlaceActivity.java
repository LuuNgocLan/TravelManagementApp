package luungoclan.min.traveltourmanagement.views.places;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.tourAdapter.TourInPlaceAdapter;
import luungoclan.min.traveltourmanagement.base.BaseActivity;
import luungoclan.min.traveltourmanagement.models.tourList.tourInPlace.DataTour;
import luungoclan.min.traveltourmanagement.presenters.places.ListTourInPlacePresenter;
import luungoclan.min.traveltourmanagement.ui.BaseHeaderBar;
import luungoclan.min.traveltourmanagement.utils.Common;

import static luungoclan.min.traveltourmanagement.ui.BaseHeaderBar.HeaderBarType.HEADER_BAR_DEFAULT;

public class ListTourInPlaceActivity extends BaseActivity implements IListTourInPlaceView {
    @BindView(R.id.baseHeaderBar)
    BaseHeaderBar mBaseHeaderBar;

    @BindView(R.id.rv_listTour)
    RecyclerView rvTours;

    @BindView(R.id.tv_msg_no_tour)
    TextView tvMsgNoTour;

    @BindView(R.id.indicatorView)
    AVLoadingIndicatorView indicatorView;

    private ListTourInPlacePresenter listTourInPlacePresenter;
    private int idPlace;
    private String namePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tour_in_place);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        mBaseHeaderBar.setHeaderBarStyle(HEADER_BAR_DEFAULT);
        namePlace = getIntent().getStringExtra(Common.NAME_LOCATION);
        mBaseHeaderBar.setTitleToolBar(namePlace);
        mBaseHeaderBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fall_down);
        rvTours.setLayoutAnimation(animation);
        //get data idPlace from intent
        idPlace = getIntent().getIntExtra(Common.ID_LOCATION, -1);
        if (idPlace != -1) {
            onShowProgressDialog("Loading...");
            rvTours.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            listTourInPlacePresenter = new ListTourInPlacePresenter(this);
            listTourInPlacePresenter.getListTourInPlace(idPlace);
        } else {
            onShowProgressDialog("Error");
        }

    }

    @Override
    public void getListTourInPlaceSuccess(DataTour dataTour) {
        onDismissProgressDialog();
        if (dataTour.getTotal() > 0) {
            rvTours.setAdapter(new TourInPlaceAdapter(dataTour.getTour(), this));
            tvMsgNoTour.setVisibility(View.GONE);
        } else {
            tvMsgNoTour.setVisibility(View.VISIBLE);

        }
        indicatorView.smoothToHide();

    }

    @Override
    public void getListTourInPlaceFailure() {
        indicatorView.smoothToHide();
    }

}

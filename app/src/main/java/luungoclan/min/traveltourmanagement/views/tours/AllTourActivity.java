package luungoclan.min.traveltourmanagement.views.tours;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.tourAdapter.TourInPlaceAdapter;
import luungoclan.min.traveltourmanagement.adapters.tourAdapter.TourOfferAdapter;
import luungoclan.min.traveltourmanagement.models.tourList.Tour;
import luungoclan.min.traveltourmanagement.presenters.tour.AllTourImpl;
import luungoclan.min.traveltourmanagement.presenters.tour.IAllTourImpl;
import luungoclan.min.traveltourmanagement.ui.BaseHeaderBar;
import luungoclan.min.traveltourmanagement.ui.ToolBarStyle;

import static luungoclan.min.traveltourmanagement.ui.BaseHeaderBar.HeaderBarType.HEADER_BAR_DEFAULT_WHITE;

public class AllTourActivity extends AppCompatActivity implements IAllTourView {

    @BindView(R.id.baseHeaderBar)
    BaseHeaderBar baseHeaderBar;
    @BindView(R.id.rv_all_tour)
    RecyclerView rvAllTours;
    @BindView(R.id.tv_msg_no_tour)
    TextView tvMsgNoTour;
    @BindView(R.id.indicatorView)
    AVLoadingIndicatorView indicatorView;

    private IAllTourImpl iAllTourImpl;
    private TourInPlaceAdapter adapter;
    private List<Tour> listAllTour = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tour);
        ButterKnife.bind(this);
        baseHeaderBar.setHeaderBarStyle(HEADER_BAR_DEFAULT_WHITE);
        baseHeaderBar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        baseHeaderBar.setTitleToolBar("All tours");
        getDataSearchFormIntent();
        iAllTourImpl = new AllTourImpl(this);
        rvAllTours.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        adapter = new TourOfferAdapter(listAllTour, this);
        iAllTourImpl.onGetAllTour();
    }

    private void getDataSearchFormIntent() {
    }

    @Override
    public void getAllTourSuccess(List<Tour> tourList) {
        this.listAllTour = tourList;
        adapter = new TourInPlaceAdapter(listAllTour, this);
        rvAllTours.setAdapter(adapter);
        indicatorView.smoothToHide();
    }

    @Override
    public void getAllTourFailure() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onShowProgressDialog(String msg) {

    }

    @Override
    public void onDismissProgressDialog() {

    }
}

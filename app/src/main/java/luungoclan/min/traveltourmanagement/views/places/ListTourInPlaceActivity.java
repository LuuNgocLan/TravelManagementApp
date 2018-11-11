package luungoclan.min.traveltourmanagement.views.places;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.tourAdapter.TourInPlaceAdapter;
import luungoclan.min.traveltourmanagement.base.BaseActivity;
import luungoclan.min.traveltourmanagement.models.places.tourInPlace.DataTour;
import luungoclan.min.traveltourmanagement.presenters.places.ListTourInPlacePresenter;
import luungoclan.min.traveltourmanagement.utils.Common;

public class ListTourInPlaceActivity extends BaseActivity implements IListTourInPlaceView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_listTour)
    RecyclerView rvTours;

    @BindView(R.id.tv_msg_no_tour)
    TextView tvMsgNoTour;

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
        setSupportActionBar(toolbar);
        namePlace = getIntent().getStringExtra(Common.NAME_LOCATION);
        setTitle(namePlace);
        //Display back home button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    }

    @Override
    public void getListTourInPlaceFailure() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

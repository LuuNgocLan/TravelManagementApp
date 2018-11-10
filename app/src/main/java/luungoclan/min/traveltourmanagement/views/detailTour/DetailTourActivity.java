package luungoclan.min.traveltourmanagement.views.detailTour;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.reviewAdapter.ReviewAdapter;
import luungoclan.min.traveltourmanagement.adapters.tourAdapter.TourAdapter;
import luungoclan.min.traveltourmanagement.base.BaseActivity;
import luungoclan.min.traveltourmanagement.models.detailTour.DataDetailTour;
import luungoclan.min.traveltourmanagement.models.detailTour.DataTour;
import luungoclan.min.traveltourmanagement.models.detailTour.Detail;
import luungoclan.min.traveltourmanagement.models.reviewOfTour.DataReview;
import luungoclan.min.traveltourmanagement.presenters.detailTour.DetailTourPresenter;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.utils.ViewDataUtils;

public class DetailTourActivity extends BaseActivity implements View.OnClickListener, IDetailTourActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private LinearLayout llContent;
    private Button btnDescription, btnPrograms, btnReview, btnChangeDate;
    private View viewDescription, viewReviews, viewPrograms, viewChangeDate;
    private DetailTourPresenter detailTourPresenter;
    //view in description tab
    private TextView tvDetailTourName, tvDetailTourDateDeparture, tvDetailTourSlot, tvDetailTourCategory,
            tvDetailTourGatheringPlace, tvDetailTourProgram, tvGuiderName, tvPriceAdult, tvPriceChild;
    //view in review tab
    private TextView tvMsgNoReview;
    private RecyclerView rvReviews;
    private ReviewAdapter reviewAdapter;
    private int id_tour = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);
        ButterKnife.bind(this);
        getDataFromIntent() ;
        init();
        setEvent();
        getDataDetailTour();
    }

    private void getDataFromIntent() {
        id_tour = getIntent().getIntExtra(Common.ID_TOUR,-1);
    }

    private void getDataDetailTour() {
        if(id_tour>0) {
            detailTourPresenter.getDetailTour(id_tour);
            detailTourPresenter.getListReviewOfTour(id_tour);
            detailTourPresenter.getListTourChangeDate(id_tour);
        } else
        {
            onShowProgressDialog("Error");
        }
    }

    private void setEvent() {
        btnDescription.setOnClickListener(this);
        btnPrograms.setOnClickListener(this);
        btnReview.setOnClickListener(this);
        btnChangeDate.setOnClickListener(this);
    }

    private void init() {
        viewDescription = getLayoutInflater().inflate(R.layout.view_tour_description, null);
        initViewDescription();
        viewPrograms = getLayoutInflater().inflate(R.layout.view_tour_program, null);
        initViewProgram();
        viewReviews = getLayoutInflater().inflate(R.layout.view_tour_reviews, null);
        initViewRiew();
        viewChangeDate = getLayoutInflater().inflate(R.layout.view_tour_change_date, null);
        btnDescription = findViewById(R.id.btn_detail_description);
        btnPrograms = findViewById(R.id.btn_detail_program);
        btnReview = findViewById(R.id.btn_detail_reviews);
        btnChangeDate = findViewById(R.id.btn_detail_changeDate);
        llContent = findViewById(R.id.ll_content);
        initViewDefault();
        initCollapsingToolbar();
        detailTourPresenter = new DetailTourPresenter(this);
    }

    private void initViewRiew() {
        tvMsgNoReview = viewReviews.findViewById(R.id.tv_msg_no_reviews);
        rvReviews = viewReviews.findViewById(R.id.rv_tour_reviews);
        rvReviews.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void initViewProgram() {
        tvDetailTourProgram = viewPrograms.findViewById(R.id.tv_detailTour_program);
    }

    private void initViewDescription() {
        tvDetailTourName = viewDescription.findViewById(R.id.tv_detailTour_name);
        tvDetailTourDateDeparture = viewDescription.findViewById(R.id.tv_detailTour_dateDeparture);
        tvDetailTourSlot = viewDescription.findViewById(R.id.tv_detailTour_slot);
        tvDetailTourCategory = viewDescription.findViewById(R.id.tv_detailTour_category);
        tvDetailTourGatheringPlace = viewDescription.findViewById(R.id.tv_detailTour_gathering_place);
        tvGuiderName = viewDescription.findViewById(R.id.tv_detailTour_guider_name);
        tvPriceAdult = viewDescription.findViewById(R.id.tv_detailTour_price_adult);
    }

    private void initViewDefault() {
        llContent.addView(viewDescription);
    }

    private void initCollapsingToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Detail Tour");
        //Display back home button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_detail_description:
                llContent.removeAllViews();
                llContent.addView(viewDescription);
                break;
            case R.id.btn_detail_program:
                llContent.removeAllViews();
                llContent.addView(viewPrograms);
                break;
            case R.id.btn_detail_reviews:
                llContent.removeAllViews();
                llContent.addView(viewReviews);
                break;
            case R.id.btn_detail_changeDate:
                llContent.removeAllViews();
                llContent.addView(viewChangeDate);
                break;
        }
    }

    @Override
    public void getDetailTourSuccess(DataDetailTour dataDetailTour) {
        if (dataDetailTour != null) {
            ViewDataUtils.setDataToView(tvDetailTourProgram, dataDetailTour.getPrograms());
            ViewDataUtils.setDataToView(tvDetailTourName, dataDetailTour.getName());
            if (dataDetailTour.getDetail() != null) {
                ViewDataUtils.setDataToView(tvDetailTourDateDeparture, dataDetailTour.getDetail().getDateDepart());
                ViewDataUtils.setDataToView(tvDetailTourSlot, dataDetailTour.getDetail().getSlot().toString());
                ViewDataUtils.setDataToView(tvDetailTourGatheringPlace, dataDetailTour.getDetail().getAddressDepart());
                ViewDataUtils.setDataToView(tvPriceAdult, dataDetailTour.getDetail().getPriceAdults() + "VNĐ");
            }
            if (dataDetailTour.getGuide() != null) {
                ViewDataUtils.setDataToView(tvGuiderName, dataDetailTour.getGuide().getName());
            }
        }
    }

    @Override
    public void getDetailTourFailure() {

    }

    @Override
    public void getListReviewOfTourSuccess(DataReview dataReview) {
        if (dataReview.getTotal() > 0) {
            tvMsgNoReview.setVisibility(View.GONE);
            reviewAdapter = new ReviewAdapter(dataReview.getReviewOfTour(), this);
            rvReviews.setAdapter(reviewAdapter);
        } else {
            tvMsgNoReview.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void getListReviewOfTourFailure() {

    }

    @Override
    public void getAnotherTourSuccess(List<DataTour> dataTour) {
        RecyclerView rvTourChangeDay = viewChangeDate.findViewById(R.id.rv_tourChangeDate);
        rvTourChangeDay.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        TourAdapter tourAdapter = new TourAdapter(dataTour,this);
        rvTourChangeDay.setAdapter(tourAdapter);
    }

    @Override
    public void getAnotherTourFailure() {

    }
}

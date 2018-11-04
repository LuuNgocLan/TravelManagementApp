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

import butterknife.BindView;
import butterknife.ButterKnife;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.reviewAdapter.ReviewAdapter;
import luungoclan.min.traveltourmanagement.models.detailTour.DataDetailTour;
import luungoclan.min.traveltourmanagement.models.reviewOfTour.DataReview;
import luungoclan.min.traveltourmanagement.presenters.detailTour.DetailTourPresenter;

public class DetailTourActivity extends AppCompatActivity implements View.OnClickListener, IDetailTourActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);
        ButterKnife.bind(this);
        init();
        setEvent();
        getDataDetailTour();
    }

    private void getDataDetailTour() {
        detailTourPresenter.getDetailTour(1);
        detailTourPresenter.getListReviewOfTour(2);
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
        tvDetailTourName.setText(dataDetailTour.getName());
        tvDetailTourDateDeparture.setText(dataDetailTour.getDetail().getDateDepart().toString());
        tvDetailTourSlot.setText(dataDetailTour.getDetail().getSlot().toString());
        tvDetailTourGatheringPlace.setText(dataDetailTour.getDetail().getAddressDepart());
        tvDetailTourProgram.setText(dataDetailTour.getPrograms());
        tvGuiderName.setText(dataDetailTour.getGuide().getName());
        tvPriceAdult.setText(dataDetailTour.getDetail().getPriceAdults() + "VNĐ");
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
}

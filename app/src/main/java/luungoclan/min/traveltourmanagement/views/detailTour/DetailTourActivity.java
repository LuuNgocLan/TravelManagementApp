package luungoclan.min.traveltourmanagement.views.detailTour;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import luungoclan.min.traveltourmanagement.R;

public class DetailTourActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private LinearLayout llContent;
    private Button btnDescription, btnPrograms, btnReview, btnChangeDate;
    private View viewDescription, viewReviews, viewPrograms, viewChangeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);
        ButterKnife.bind(this);
        init();
        setEvent();
    }

    private void setEvent() {
        btnDescription.setOnClickListener(this);
        btnPrograms.setOnClickListener(this);
        btnReview.setOnClickListener(this);
        btnChangeDate.setOnClickListener(this);
    }

    private void init() {
        btnDescription = findViewById(R.id.btn_detail_description);
        btnPrograms = findViewById(R.id.btn_detail_program);
        btnReview = findViewById(R.id.btn_detail_reviews);
        btnChangeDate = findViewById(R.id.btn_detail_changeDate);
        llContent = findViewById(R.id.ll_content);
        initViewDefault();
        initCollapsingToolbar();
    }

    private void initViewDefault() {
        View viewDescription = getLayoutInflater().inflate(R.layout.view_tour_description, null);
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
                viewDescription = getLayoutInflater().inflate(R.layout.view_tour_description, null);
                llContent.addView(viewDescription);
                break;
            case R.id.btn_detail_program:
                llContent.removeAllViews();
                viewPrograms = getLayoutInflater().inflate(R.layout.view_tour_program, null);
                llContent.addView(viewPrograms);
                break;
            case R.id.btn_detail_reviews:
                llContent.removeAllViews();
                viewReviews = getLayoutInflater().inflate(R.layout.view_tour_reviews, null);
                llContent.addView(viewReviews);
                break;
            case R.id.btn_detail_changeDate:
                llContent.removeAllViews();
                viewChangeDate = getLayoutInflater().inflate(R.layout.view_tour_change_date, null);
                llContent.addView(viewChangeDate);
                break;
        }
    }
}

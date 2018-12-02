package luungoclan.min.traveltourmanagement.views.detailTour;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.reviewAdapter.ReviewOfTourAdapter;
import luungoclan.min.traveltourmanagement.adapters.tourAdapter.TourAdapter;
import luungoclan.min.traveltourmanagement.base.BaseActivity;
import luungoclan.min.traveltourmanagement.models.detailTour.DataDetailTour;
import luungoclan.min.traveltourmanagement.models.detailTour.DataTour;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfile;
import luungoclan.min.traveltourmanagement.models.reviewOfTour.DataReview;
import luungoclan.min.traveltourmanagement.presenters.detailTour.DetailTourPresenter;
import luungoclan.min.traveltourmanagement.ui.BaseHeaderBar;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.utils.Utils;
import luungoclan.min.traveltourmanagement.utils.ViewDataUtils;
import luungoclan.min.traveltourmanagement.views.booking.BookingActivity;
import luungoclan.min.traveltourmanagement.views.login.LoginActivity;
import luungoclan.min.traveltourmanagement.views.main.MainActivity;
import okhttp3.RequestBody;

public class DetailTourActivity extends BaseActivity implements View.OnClickListener, IDetailTourActivity {

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
    private Button btnSubmitReview;
    private RatingBar rbRatingReview;
    private EditText edtContent, edtName, edtEmail;
    private ReviewOfTourAdapter reviewAdapter;
    private int id_tour = -1;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.ll_dots)
    LinearLayout mDotsLayout;
    @BindView(R.id.baseHeaderBar)
    BaseHeaderBar mBaseHeaderBar;
    @BindView(R.id.tv_price_tour)
    TextView tvPriceTour;

    private int[] mImages = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_1, R.drawable.img_2, R.drawable.img_1};
    private TextView[] mDots;
    private SlideAdapter mSlideAdapter;
    private boolean isLoggedIn = false;

    private MyProfile userCurrent = null;
    private String tokenUser = null;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(Common.PREF_REMEMBER_ME, MODE_PRIVATE);

        getDataFromIntent();
        getCurrentUser();
        init();
        setEvent();
        getDataDetailTour();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatusLogin();
        Toast.makeText(this, "islogin = " + isLoggedIn, Toast.LENGTH_SHORT).show();
        if (isLoggedIn && userCurrent != null) {
            edtName.setText(userCurrent.getUsername());
            edtEmail.setText(userCurrent.getEmail());
        }
    }

    private void getCurrentUser() {
        tokenUser = sharedPreferences.getString(Common.TOKEN_SAVED, null);
        if (tokenUser != null) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString(Common.MYPROFILE, null);
            userCurrent = gson.fromJson(json, MyProfile.class);
            Toast.makeText(this, userCurrent.getFullname(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getDataFromIntent() {
        id_tour = getIntent().getIntExtra(Common.ID_TOUR, -1);
    }

    private void getDataDetailTour() {
        if (id_tour > 0) {
            detailTourPresenter.getDetailTour(id_tour);
            detailTourPresenter.getListReviewOfTour(id_tour);
            detailTourPresenter.getListTourChangeDate(id_tour);
        } else {
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
        setViewPager();
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
        initToolBar();
        detailTourPresenter = new DetailTourPresenter(this);
    }

    private void initToolBar() {
        mBaseHeaderBar.setHeaderBarStyle(BaseHeaderBar.HeaderBarType.HEADER_BAR_DEFAULT);
    }

    private void updateStatusLogin() {
        isLoggedIn = sharedPreferences.getBoolean(Common.IS_LOGGING_IN, false);
    }

    private void initViewRiew() {
        tvMsgNoReview = viewReviews.findViewById(R.id.tv_msg_no_reviews);
        rvReviews = viewReviews.findViewById(R.id.rv_tour_reviews);
        rvReviews.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        btnSubmitReview = viewReviews.findViewById(R.id.btn_submit_review_form);
        rbRatingReview = viewReviews.findViewById(R.id.rb_tour);
        edtName = viewReviews.findViewById(R.id.edt_review_form_name);
        edtEmail = viewReviews.findViewById(R.id.edt_review_form_email);
        edtContent = viewReviews.findViewById(R.id.edt_review_form_content);
        btnSubmitReview.setOnClickListener(this);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_book_tour)
    public void onCallBookTour(View view) {
        startActivity(new Intent(this, BookingActivity.class));
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
            case R.id.btn_submit_review_form:
                if (isValidReview()) {
                    Toast.makeText(this, "Submit review!", Toast.LENGTH_SHORT).show();
                    sendDataReviewToServer();
                }
                break;
        }
    }

    private boolean isValidReview() {
        if (edtContent.getText() == null || edtContent.getText().length() > 0) {
            return true;
        }
        return false;
    }

    private void sendDataReviewToServer() {
        if (isLoggedIn) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("score", (int) rbRatingReview.getRating());
                jsonObject.put("content", edtContent.getText());
                jsonObject.put("id_tour", id_tour);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
            if (Utils.isConnected(getBaseContext())) {
                detailTourPresenter.addReview(MainActivity.token, body);
            } else {
                onShowProgressDialog("No internet!");
            }
        } else {
            startActivity(new Intent(getContext(), LoginActivity.class));
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
            reviewAdapter = new ReviewOfTourAdapter(dataReview.getReviewOfTour(), this);
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
        TourAdapter tourAdapter = new TourAdapter(dataTour, this, R.layout.item_tour_vertical);
        rvTourChangeDay.setAdapter(tourAdapter);
    }

    @Override
    public void getAnotherTourFailure() {

    }

    @Override
    public void addReviewSuccess() {
        Toast.makeText(this, "   Add Review  Success!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void addReviewFailure() {
        Toast.makeText(this, "   Add Review  Failure!", Toast.LENGTH_SHORT).show();
    }

    private void setViewPager() {
        addBottomDots(0);
        mSlideAdapter = new SlideAdapter();
        mViewPager.setAdapter(mSlideAdapter);
        mViewPager.addOnPageChangeListener(mViewPagerChangeListener);

    }

    private ViewPager.OnPageChangeListener mViewPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void addBottomDots(int currentPage) {
        mDots = new TextView[mImages.length];

        int colorsActive = getResources().getColor(R.color.colorAccent);
        int colorsInActive = getResources().getColor(android.R.color.white);

        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(getContext());
            mDots[i].setText(Html.fromHtml("•"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(colorsInActive);
            mDotsLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[currentPage].setTextColor(colorsActive);
        }
    }

    ////////////////////////////////////
    public class SlideAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public SlideAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_image_detail, container, false);
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);

            ImageView image = view.findViewById(R.id.imv_tour);
            image.setImageDrawable(getResources().getDrawable(mImages[position]));
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((View) obj);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}

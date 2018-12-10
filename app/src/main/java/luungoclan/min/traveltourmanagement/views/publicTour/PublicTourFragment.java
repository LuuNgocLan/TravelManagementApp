package luungoclan.min.traveltourmanagement.views.publicTour;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.placeAdapter.PlaceAdapter;
import luungoclan.min.traveltourmanagement.adapters.placeAdapter.PlaceSearchAdapter;
import luungoclan.min.traveltourmanagement.adapters.tourAdapter.TourOfferAdapter;
import luungoclan.min.traveltourmanagement.models.fakes.TourOffer;
import luungoclan.min.traveltourmanagement.models.places.Place;
import luungoclan.min.traveltourmanagement.models.places.PlaceData;
import luungoclan.min.traveltourmanagement.models.places.SelectedPlace;
import luungoclan.min.traveltourmanagement.models.slide.Slide;
import luungoclan.min.traveltourmanagement.models.tourList.DataTourList;
import luungoclan.min.traveltourmanagement.models.tourList.Tour;
import luungoclan.min.traveltourmanagement.presenters.places.IPlaceImpl;
import luungoclan.min.traveltourmanagement.presenters.places.PlaceImpl;
import luungoclan.min.traveltourmanagement.presenters.tour.PublicTourImpl;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.views.places.IPlaceView;
import luungoclan.min.traveltourmanagement.views.tours.AllTourActivity;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicTourFragment extends Fragment implements View.OnClickListener, IPublicTourView, IPlaceView, PlaceSearchAdapter.OnItemSelectedListener {

    private Button btnWhere, btnWhen;
    private LinearLayout llSearchWhere, llSearchWhen;
    private List<TourOffer> tourOfferList;
    private RecyclerView rvTourOffer;
    private TourOfferAdapter tourOfferAdapter;
    public boolean isShowWhereSearch = true, isShowWhenSearch = true;
    private PublicTourImpl publicTourPresenter;
    private IPlaceImpl iPlaceImpl;
    private Calendar c = Calendar.getInstance();
    private int mYear, mMonth, mDay;

    @BindView(R.id.layout_sale_tour_placeholder)
    LinearLayout layoutSaleTourPlaceholder;
    @BindView(R.id.layout_latest_tour_placeholder)
    LinearLayout layoutLatestTourPlaceholder;
    @BindView(R.id.rv_topDestinations)
    RecyclerView rvTopDestinations;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.ll_dots)
    LinearLayout mDotsLayout;
    @BindView(R.id.rv_topPlaces)
    HorizontalGridView rvTopPlaces;
    @BindView(R.id.edt_check_in)
    EditText edtCheckIn;
    @BindView(R.id.indicatorView)
    AVLoadingIndicatorView indicatorView;
    @BindView(R.id.rv_places)
    RecyclerView rvPlaces;

    private int[] mImages = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_1};
    private TextView[] mDots;
    private SlideAdapter mSlideAdapter;
    private List<Slide> slideList = new ArrayList<>();
    private List<Place> placeList = new ArrayList<>();
    private PlaceSearchAdapter mPlaceSearchAdapter;
    private Place placeInforSearch;

    private static final long SLIDER_TIMER = 3000; // change slider interval
    private int currentPage = 0; // this will tell us the current page available on the view pager
    //ViewPager Listener on the onPageSelected method to see how we are updating
    // currentPage variable

    private boolean isCountDownTimerActive = false; // let the timer start if and only if it has completed previous task


    private Handler handler;
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if (!isCountDownTimerActive) {
                automateSlider();
            }

            handler.postDelayed(runnable, 1000);
            // our runnable should keep running for every 1000 milliseconds (1 seconds)
        }
    };

    public PublicTourFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_public_tour, container, false);
        ButterKnife.bind(this, view);
        handler = new Handler();
        handler.postDelayed(runnable, 1000);
        getSetCurrentDay();
        initPresenter();
        init(view);
        loadDataDetailTourFromServer();
        setEvent();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        runnable.run();
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
            if (position == 0) {
                currentPage = 0;
            } else if (position == 1) {
                currentPage = 1;
            } else {
                currentPage = 2;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void loadDataDetailTourFromServer() {
        publicTourPresenter.getSaleTour();
        publicTourPresenter.getLatestTour();
        publicTourPresenter.getFavoritePlace();
    }

    private void initPresenter() {
        publicTourPresenter = new PublicTourImpl(this);
        publicTourPresenter.getAllSlides();
        iPlaceImpl = new PlaceImpl(this);
        iPlaceImpl.getPlaceList();
    }

    private void setEvent() {
        btnWhen.setOnClickListener(this);
        btnWhere.setOnClickListener(this);
    }

    private void init(View view) {
        btnWhen = view.findViewById(R.id.btn_when);
        btnWhere = view.findViewById(R.id.btn_where);
        llSearchWhen = view.findViewById(R.id.ll_wheneSearch);
        llSearchWhere = view.findViewById(R.id.ll_whereSearch);
        rvTourOffer = view.findViewById(R.id.rv_latestOffers);

        rvTourOffer.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvTopDestinations = view.findViewById(R.id.rv_topDestinations);
        rvTopDestinations.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvTopPlaces.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        tourOfferList = new ArrayList<>();

        rvPlaces.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_when:
                Toast.makeText(getContext(), isShowWhenSearch + "", Toast.LENGTH_SHORT).show();
                if (isShowWhenSearch) {
                    llSearchWhen.setAnimation(AnimationUtils.makeInAnimation(getActivity(), true));
                    llSearchWhen.setVisibility(View.VISIBLE);
                } else {
                    llSearchWhen.setVisibility(View.GONE);
                }
                isShowWhenSearch = !isShowWhenSearch;
                break;
            case R.id.btn_where:
                Toast.makeText(getContext(), isShowWhereSearch + "", Toast.LENGTH_SHORT).show();
                if (isShowWhereSearch) {
                    llSearchWhere.setAnimation(AnimationUtils.makeInAnimation(getActivity(), true));
                    llSearchWhere.setVisibility(View.VISIBLE);
                } else {
                    llSearchWhere.setVisibility(View.GONE);
                }
                isShowWhereSearch = !isShowWhereSearch;
                break;
        }
    }

    @OnClick(R.id.btn_proceedToResult)
    public void onSearch(View view) {
        getDataJsonSearch();
        indicatorView.smoothToShow();
    }

    @OnClick({R.id.btnSeeAll_1, R.id.btnSeeAll_2})
    public void onSeeAllTour(View view) {
        startActivity(new Intent(getActivity(), AllTourActivity.class));

    }

    @OnClick(R.id.btn_check_in)
    public void onChooseCheckInDay(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String syear = "", smonth = "", sday = "";
                syear = String.valueOf(year);
                if (monthOfYear < 9) smonth = "0";
                smonth += String.valueOf(monthOfYear + 1);
                if (dayOfMonth < 9) sday = "0";
                sday += String.valueOf(dayOfMonth);
                edtCheckIn.setText(sday + smonth + syear);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void getSetCurrentDay() {
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        String syear = "", smonth = "", sday = "";
        syear = String.valueOf(mYear);
        if (mMonth < 9) smonth = "0";
        smonth += String.valueOf(mMonth + 1);
        if (mDay < 9) sday = "0";
        sday += String.valueOf(mDay);
    }

    private void getDataJsonSearch() {
        JSONObject jsonObject = new JSONObject();
        try {
            if (edtCheckIn.getText().toString().equals("dd-MM-yyyy")) {
                jsonObject.put("date_depart", "");
            } else {
                jsonObject.put("date_depart", edtCheckIn.getText().toString());
            }
            if (placeInforSearch == null) {
                jsonObject.put("id_location", "");
            } else {
                jsonObject.put("id_location", placeInforSearch.getId());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        publicTourPresenter.getSearchResult(body);
    }

    @Override
    public void getListSaleTourSuccess(DataTourList dataTourList) {
        tourOfferAdapter = new TourOfferAdapter(dataTourList.getTour(), getActivity());
        rvTourOffer.setAdapter(tourOfferAdapter);
        layoutSaleTourPlaceholder.setVisibility(View.GONE);
    }

    @Override
    public void getListSaleTourFailure() {

    }

    @Override
    public void getListLatestTourSuccess(DataTourList dataTourList) {
        rvTopDestinations.setAdapter(new TourOfferAdapter(dataTourList.getTour(), getActivity()));
        layoutLatestTourPlaceholder.setVisibility(View.GONE);
    }

    @Override
    public void getListLatestTourFailure() {

    }

    @Override
    public void getListTopPlacesSuccess(PlaceData placeData) {
        rvTopPlaces.setAdapter(new PlaceAdapter(getActivity(), placeData.getPlace()));
        indicatorView.smoothToHide();
    }

    @Override
    public void getListTopPlaceFailure() {

    }

    @Override
    public void searchTourSuccess(List<Tour> tourList) {

        Intent intent = new Intent(getActivity(), AllTourActivity.class);
        Bundle bundle = new Bundle();
        ArrayList<Tour> listTourResult = new ArrayList<Tour>();
        listTourResult.addAll(tourList);
//        bundle.putStringArrayList(Common.BUNDLE_KEY_LIST_TOUR_RESULT, listTourResult);
        intent.putExtras(bundle);
        Toast.makeText(getActivity(), tourList.size() + "", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        indicatorView.smoothToHide();
    }

    @Override
    public void searchTourFailure() {

    }

    @Override
    public void getAllSlideSuccess(List<Slide> slides) {
        this.slideList = slides;
        if (slides.size() > 0) {
            setViewPager();
        }
    }

    @Override
    public void getAllSlideFailure() {

    }

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

    private void automateSlider() {
        isCountDownTimerActive = true;
        new CountDownTimer(SLIDER_TIMER, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                int nextSlider = currentPage + 1;
                if (nextSlider == 3) {
                    nextSlider = 0; // if it's last Image, let it go to the first image
                }

                mViewPager.setCurrentItem(nextSlider);
                isCountDownTimerActive = false;
            }
        }.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        // Kill this background task once the activity has been killed
        handler.removeCallbacks(runnable);
    }

    @Override
    public void getPlaceListSuccess(PlaceData placeData) {
        this.placeList = placeData.getPlace();
        Toast.makeText(getContext(), placeData.toString(), Toast.LENGTH_LONG).show();
        mPlaceSearchAdapter = new PlaceSearchAdapter(getContext(), placeList, this);
        rvPlaces.setAdapter(mPlaceSearchAdapter);
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

    @Override
    public void onItemSelected(Place item) {
        placeInforSearch = item;
        Toast.makeText(getContext(), placeInforSearch.getName(), Toast.LENGTH_SHORT).show();
        mPlaceSearchAdapter.notifyDataSetChanged();
    }

    ////////////////////////////////////
    public class SlideAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public SlideAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Slide slide = slideList.get(position);
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_slide, container, false);
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);

            ImageView image = view.findViewById(R.id.imv_tour);
            TextView tvNameTour = view.findViewById(R.id.tv_name_tour);
            TextView tvSlogan = view.findViewById(R.id.tv_slogan);

            tvNameTour.setAnimation(animation);
            tvNameTour.setText(slide.getNameTour());
            tvSlogan.setAnimation(animation);
            tvSlogan.setText(slide.getDescription());
            String urlImage = Common.BASE_URL + slide.getUrl();
            Glide.with(getContext())
                    .load(urlImage)
                    .placeholder(mImages[position])
                    .into(image);
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

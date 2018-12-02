package luungoclan.min.traveltourmanagement.views.publicTour;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.placeAdapter.PlaceAdapter;
import luungoclan.min.traveltourmanagement.adapters.tourAdapter.TourOfferAdapter;
import luungoclan.min.traveltourmanagement.models.fakes.TourOffer;
import luungoclan.min.traveltourmanagement.models.places.PlaceData;
import luungoclan.min.traveltourmanagement.models.tourList.DataTourList;
import luungoclan.min.traveltourmanagement.models.tourList.Tour;
import luungoclan.min.traveltourmanagement.presenters.tour.PublicTourPresenter;
import luungoclan.min.traveltourmanagement.views.tours.AllTourActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicTourFragment extends Fragment implements View.OnClickListener, IPublicTourFragment {

    private Button btnWhere, btnWhen;
    private LinearLayout llSearchWhere, llSearchWhen;
    private List<TourOffer> tourOfferList;
    private RecyclerView rvTourOffer;
    private TourOfferAdapter tourOfferAdapter;
    public boolean isShowWhereSearch = true, isShowWhenSearch = true;
    private PublicTourPresenter publicTourPresenter;

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

    private int[] mImages = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_1};
    private TextView[] mDots;
    private SlideAdapter mSlideAdapter;

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
        initPresenter();
        init(view);
        setViewPager();
        loadData();
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

    private void loadData() {
        publicTourPresenter.getSaleTour();
        publicTourPresenter.getLatestTour();
        publicTourPresenter.getFavoritePlace();
    }

    private void initPresenter() {
        publicTourPresenter = new PublicTourPresenter(this);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_when:
                Toast.makeText(getContext(), isShowWhenSearch + "", Toast.LENGTH_SHORT).show();
                if (isShowWhenSearch) {
                    llSearchWhen.setAnimation(AnimationUtils.makeInAnimation(getActivity(),true));
                    llSearchWhen.setVisibility(View.VISIBLE);
                } else {
                    llSearchWhen.setVisibility(View.GONE);
                }
                isShowWhenSearch = !isShowWhenSearch;
                break;
            case R.id.btn_where:
                Toast.makeText(getContext(), isShowWhereSearch + "", Toast.LENGTH_SHORT).show();
                if (isShowWhereSearch) {
                    llSearchWhere.setAnimation(AnimationUtils.makeInAnimation(getActivity(),true));
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
    }

    @OnClick({R.id.btnSeeAll_1, R.id.btnSeeAll_2})
    public void onSeeAllTour(View view) {
        startActivity(new Intent(getActivity(), AllTourActivity.class));
    }

    private void getDataJsonSearch() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("date_depart", "2018-12-01");
            jsonObject.put("id_location", 2);
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
    }

    @Override
    public void getListTopPlaceFailure() {

    }

    @Override
    public void searchTourSuccess(List<Tour> tourList) {

        Intent intent = new Intent(getActivity(), AllTourActivity.class);
        ArrayList<Tour> arrayList = new ArrayList<>(tourList.size());
        arrayList.addAll(tourList);
        Toast.makeText(getActivity(), tourList.size() + "", Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }

    @Override
    public void searchTourFailure() {

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

    ////////////////////////////////////
    public class SlideAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public SlideAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_slide, container, false);
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);

            ImageView image = view.findViewById(R.id.imv_tour);
            TextView tvNameTour = view.findViewById(R.id.tv_name_tour);
            TextView tvSlogan = view.findViewById(R.id.tv_slogan);

            tvNameTour.setAnimation(animation);
            tvSlogan.setAnimation(animation);
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

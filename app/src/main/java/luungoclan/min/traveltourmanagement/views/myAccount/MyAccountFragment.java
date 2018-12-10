package luungoclan.min.traveltourmanagement.views.myAccount;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.BasePagerAdapter;
import luungoclan.min.traveltourmanagement.models.booking.Booking;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfile;
import luungoclan.min.traveltourmanagement.models.reviewOfUser.Review;
import luungoclan.min.traveltourmanagement.presenters.myAccount.ILogoutImpl;
import luungoclan.min.traveltourmanagement.presenters.myAccount.LogoutImpl;
import luungoclan.min.traveltourmanagement.presenters.myProfile.IMyProfileImpl;
import luungoclan.min.traveltourmanagement.presenters.myReview.IMyReviewImpl;
import luungoclan.min.traveltourmanagement.presenters.myReview.MyReviewImpl;
import luungoclan.min.traveltourmanagement.ui.BaseHeaderBar;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.views.login.LoginActivity;
import luungoclan.min.traveltourmanagement.views.main.MainActivity;
import luungoclan.min.traveltourmanagement.views.myBooking.MyBookingFragment;
import luungoclan.min.traveltourmanagement.views.myProfile.EditProfileActivity;
import luungoclan.min.traveltourmanagement.views.myReview.IMyReviewView;
import luungoclan.min.traveltourmanagement.views.myReview.MyReviewFragment;
import okhttp3.RequestBody;

import static android.content.Context.MODE_PRIVATE;
import static luungoclan.min.traveltourmanagement.ui.BaseHeaderBar.HeaderBarType.HEADER_BAR_ACCOUNT;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment implements IMyAccountFragment, View.OnClickListener, IMyReviewView {
    @BindView(R.id.imv_avatar)
    ImageView imvAvatar;

    @BindView(R.id.btn_signUp)
    Button btnSignUp;
    @BindView(R.id.btn_signIn)
    Button btnSignIn;

    @BindView(R.id.tv_name_user)
    TextView tvName;

    @BindView(R.id.tv_addr_user)
    TextView tvAddr;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.toolbar)
    BaseHeaderBar mToolbar;

    @BindView(R.id.indicatorView)
    AVLoadingIndicatorView indicatorView;

    private List<Fragment> fragmentList = new ArrayList<>();
    private MyBookingFragment myBookingFragment = new MyBookingFragment();
    private MyReviewFragment myReviewFragment = new MyReviewFragment();
    private BasePagerAdapter adapter;
    private String[] titles = {"My Booking", "My Reviews"};
    private SharedPreferences sharedPreferences;
    private View view;
    private JSONObject jsonObject;
    private boolean isLoggingIn;
    private MyProfile currentUser = null;
    private IMyProfileImpl iMyProfileImpl;
    private ILogoutImpl iLogoutImpl;
    private IMyReviewImpl iMyReviewImpl;
    private String token;
    private MenuItem logoutMenu;

    public MyAccountFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_account, container, false);
        ButterKnife.bind(this, view);

        sharedPreferences = getActivity().getSharedPreferences(Common.PREF_REMEMBER_ME, MODE_PRIVATE);
        isLoggingIn = sharedPreferences.getBoolean(Common.IS_LOGGING_IN, false);

        iLogoutImpl = new LogoutImpl(this);
        iMyReviewImpl = new MyReviewImpl(this);

        initView();
        initTabs();
        checkDisplayView();

        return view;
    }

    private void initView() {
        mToolbar.setHeaderBarStyle(HEADER_BAR_ACCOUNT);
        logoutMenu = mToolbar.getMenuItem();
        logoutMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_logout) {
                    Toast.makeText(getContext(), "Log out click!", Toast.LENGTH_SHORT).show();
                    passDataLogoutToJson();
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                    iLogoutImpl.logout(token, body);
                }
                return false;
            }
        });
    }

    public void initTabs() {
        fragmentList.add(myBookingFragment);
        fragmentList.add(myReviewFragment);

        adapter = new BasePagerAdapter(getChildFragmentManager(), fragmentList, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    @Override
    public void onResume() {
        super.onResume();
        token = sharedPreferences.getString(Common.TOKEN_SAVED, null);
        Toast.makeText(getActivity(), "On resume", Toast.LENGTH_SHORT).show();
        isLoggingIn = sharedPreferences.getBoolean(Common.IS_LOGGING_IN, false);
        checkDisplayView();

    }


    private void checkDisplayView() {
        if (isLoggingIn) {
            logoutMenu.setVisible(true);
            btnSignIn.setVisibility(View.GONE);
            btnSignUp.setVisibility(View.GONE);
            tvName.setVisibility(View.VISIBLE);
            tvAddr.setVisibility(View.VISIBLE);
            //get infor profile to display
            Gson gson = new Gson();
            String json = sharedPreferences.getString(Common.MYPROFILE, null);
            currentUser = gson.fromJson(json, MyProfile.class);

            if (currentUser != null) {
                String urlAvatar = Common.BASE_URL + currentUser.getAvatar();
                Glide.with(getContext())
                        .load(urlAvatar)
                        .placeholder(R.drawable.img_avatar)
                        .error(R.drawable.img_avatar)
                        .into(imvAvatar);
                tvName.setText(currentUser.getUsername());
                tvAddr.setText(currentUser.getAddress());
            }

            iLogoutImpl.getMyBooking(token);
            iMyReviewImpl.getMyReview(token);
        } else {
            logoutMenu.setVisible(false);
            tvName.setVisibility(View.GONE);
            tvAddr.setVisibility(View.GONE);
            btnSignUp.setOnClickListener(this);
            btnSignIn.setOnClickListener(this);
        }
    }

    private void passDataLogoutToJson() {
        String username = sharedPreferences.getString(Common.USERNAME, "");
        String password = sharedPreferences.getString(Common.PASSWORD, "");
        jsonObject = new JSONObject();
        try {
            jsonObject.put(Common.USERNAME, username);
            jsonObject.put(Common.PASSWORD, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.rl_infor)
    public void onCallEditProfile(View view) {
        startActivityForResult(new Intent(getActivity(), EditProfileActivity.class), Common.REQUEST_EDIT_PROFILE);
    }

    @Override
    public void logoutSuccess(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        MainActivity.token = null;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Common.IS_LOGGING_IN, false);
        editor.putString(Common.TOKEN_SAVED, null);
        editor.putString(Common.MYPROFILE, null);
        editor.commit();
        fragmentList.clear();
        //send message to tab my reviews
//        myReviewFragment.getListReviewFromServer(null);
        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    @Override
    public void logoutFailure(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getListBookingSuccess(List<Booking> bookingList) {
        Toast.makeText(getContext(), "Get Booking Success", Toast.LENGTH_SHORT).show();
        myBookingFragment.dataBooking(bookingList);
        indicatorView.smoothToHide();
    }

    @Override
    public void getListBookingFailure() {
        Toast.makeText(getContext(), "Get Booking Failure", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signIn:
                createIntentSignin();
                break;
            case R.id.btn_signUp:
                //todo: call sign up
                break;
        }
    }

    private void createIntentSignin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivityForResult(intent, Common.REQUEST_LOGIN);
    }

    @Override
    public void onShowProgressDialog(String msg) {

    }

    @Override
    public void onDismissProgressDialog() {

    }

    @Override
    public void getMyDataReviewSuccess(List<Review> reviewList) {
        myReviewFragment.getListReviewFromServer(reviewList);

    }

    @Override
    public void getMyReviewFailure() {

    }
}

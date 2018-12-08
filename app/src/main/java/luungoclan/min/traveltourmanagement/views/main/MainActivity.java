package luungoclan.min.traveltourmanagement.views.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.ViewPagerAdapter;
import luungoclan.min.traveltourmanagement.models.db.Profile;
import luungoclan.min.traveltourmanagement.presenters.myProfile.IMyProfileImpl;
import luungoclan.min.traveltourmanagement.utils.Common;

public class MainActivity extends AppCompatActivity implements IMainView {
    public static String token = null;
    public static Profile mProfile = null;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private ViewPagerAdapter mViewPagerAdapter;
    private IMyProfileImpl iMyProfileImpl;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(Common.PREF_REMEMBER_ME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        addControls();

    }

    private void addControls() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mTabLayout.setupWithViewPager(mViewPager);
        setIcon();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setIcon() {
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_tour);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_place);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_account);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Common.REQUEST_LOGIN && resultCode == Common.RESULT_LOGIN_SUCCESS) {
            Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Context getContext() {
        return getContext();
    }

    @Override
    public void onShowProgressDialog(String msg) {

    }

    @Override
    public void onDismissProgressDialog() {

    }


}

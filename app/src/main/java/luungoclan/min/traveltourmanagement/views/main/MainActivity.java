package luungoclan.min.traveltourmanagement.views.main;

import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.ViewPagerAdapter;
import luungoclan.min.traveltourmanagement.utils.Common;

public class MainActivity extends AppCompatActivity {
    public static String token = null;
    public static boolean isLoggingIn = false;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences(Common.PREF_REMEMBER_ME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Common.IS_LOGGING_IN, isLoggingIn);
        editor.commit();
        addControls();
    }

    private void addControls() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(viewPagerAdapter);

        final TabLayout.Tab toursTab = tabLayout.newTab();
        final TabLayout.Tab placesTab = tabLayout.newTab();
        final TabLayout.Tab profileTab = tabLayout.newTab();

        View toursView = getLayoutInflater().inflate(R.layout.custom_tab, null);
        final ImageView tourImage = toursView.findViewById(R.id.img_tab);
        final TextView tourText = toursView.findViewById(R.id.txt_name_tab);
        tourImage.setImageResource(R.drawable.ic_tab_map_selected);
        tourText.setText(R.string.tab_name_tours);

        View placesView = getLayoutInflater().inflate(R.layout.custom_tab, null);
        final ImageView placesImage = placesView.findViewById(R.id.img_tab);
        final TextView placesText = placesView.findViewById(R.id.txt_name_tab);
        placesImage.setImageResource(R.drawable.ic_tab_around);
        placesText.setText(R.string.tab_name_places);

        View profileView = getLayoutInflater().inflate(R.layout.custom_tab, null);
        final ImageView profileImage = profileView.findViewById(R.id.img_tab);
        final TextView profileText = profileView.findViewById(R.id.txt_name_tab);
        profileImage.setImageResource(R.drawable.ic_tab_profile);
        profileText.setText(R.string.tab_name_myaccount);

        toursTab.setCustomView(toursView);
        placesTab.setCustomView(placesView);
        profileTab.setCustomView(profileView);

        tabLayout.addTab(toursTab, 0);
        tabLayout.addTab(placesTab, 1);
        tabLayout.addTab(profileTab, 2);

        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tourImage.setImageResource(R.drawable.ic_tab_map_selected);
                        tourText.setTextColor(getResources().getColor(R.color.colorAccent));
                        placesImage.setImageResource(R.drawable.ic_tab_around);
                        placesText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        profileImage.setImageResource(R.drawable.ic_tab_profile);
                        profileText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                        break;
                    case 1:
                        tourImage.setImageResource(R.drawable.ic_tab_map);
                        tourText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        placesImage.setImageResource(R.drawable.ic_tab_around_selected);
                        placesText.setTextColor(getResources().getColor(R.color.colorAccent));
                        profileImage.setImageResource(R.drawable.ic_tab_profile);
                        profileText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        break;
                    case 2:
                        placesImage.setImageResource(R.drawable.ic_tab_around);
                        placesText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        tourImage.setImageResource(R.drawable.ic_tab_map);
                        tourText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        profileImage.setImageResource(R.drawable.ic_tab_profile_selected);
                        profileText.setTextColor(getResources().getColor(R.color.colorAccent));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {

    }

}

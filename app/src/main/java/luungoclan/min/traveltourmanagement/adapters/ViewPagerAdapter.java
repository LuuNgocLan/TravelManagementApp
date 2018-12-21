package luungoclan.min.traveltourmanagement.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import luungoclan.min.traveltourmanagement.views.places.PlacesFragment;
import luungoclan.min.traveltourmanagement.views.myAccount.MyAccountFragment;
import luungoclan.min.traveltourmanagement.views.publicTour.PublicTourFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 3;
    private Context context;
    private String[] mTitles = {"Tours", "Places", "Account"};

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PublicTourFragment();
            case 1:
                return new PlacesFragment();
            case 2:
                return new MyAccountFragment();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}

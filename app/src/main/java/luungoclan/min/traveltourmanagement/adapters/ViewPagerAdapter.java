package luungoclan.min.traveltourmanagement.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import luungoclan.min.traveltourmanagement.views.places.PlacesFragment;
import luungoclan.min.traveltourmanagement.views.myAccount.MyAccountFragment;
import luungoclan.min.traveltourmanagement.views.publicTour.PublicTourFragment;
import luungoclan.min.traveltourmanagement.views.tours.ToursFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 3;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
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

        switch (position) {
            case 0:
                return "Tours";
            case 1:
                return "Places";
            case 2:
                return "MyAccount";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}

package luungoclan.min.traveltourmanagement.views.myAccount;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.models.menu.MenuModel;
import luungoclan.min.traveltourmanagement.views.myProfile.MyProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    public MyAccountFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        init(view);
        createExpandlist();
        prepareMenuData();
        return view;
    }

    private void createExpandlist() {
        expandableListAdapter = new luungoclan.min.traveltourmanagement.adapters.ExpandableListAdapter(getContext(), headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        if (headerList.get(groupPosition).id.equals(getString(R.string.menu_id_mybooking))) {
                            Toast.makeText(getContext(), "My booking", Toast.LENGTH_SHORT).show();
//                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OverTimeListFragment()).commit();
//                            getSupportActionBar().setTitle(R.string.menu_overtime);
                        }
                        if (headerList.get(groupPosition).id.equals(getString(R.string.menu_id_myprofile))) {
                            startActivity(new Intent(getActivity(), MyProfileActivity.class));
//                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OverTimeListFragment()).commit();
//                            getSupportActionBar().setTitle(R.string.menu_overtime);
                        }
                    }
                }
                return false;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    Log.d("GROUP", model.menuName);
                }
                return false;
            }
        });


    }

    private void prepareMenuData() {
        MenuModel menuModel = new MenuModel(getString(R.string.menu_id_mybooking), getString(R.string.menu_mybooking), true, false, getResources().getDrawable(R.drawable.ic_booking));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        menuModel = new MenuModel(getString(R.string.menu_id_myreviews), getString(R.string.menu_myreviews), true, false, getResources().getDrawable(R.drawable.ic_review));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        menuModel = new MenuModel(getString(R.string.menu_id_myprofile), getString(R.string.menu_myprofile), true, false, getResources().getDrawable(R.drawable.ic_person));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        menuModel = new MenuModel(getString(R.string.menu_id_setting), getString(R.string.menu_setting), true, false, getResources().getDrawable(R.drawable.ic_settings));
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

    }

    private void init(View view) {
        expandableListView = view.findViewById(R.id.expandableListView);

    }

}

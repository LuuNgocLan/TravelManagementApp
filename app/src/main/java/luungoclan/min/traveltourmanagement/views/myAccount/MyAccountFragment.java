package luungoclan.min.traveltourmanagement.views.myAccount;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.models.menu.MenuModel;
import luungoclan.min.traveltourmanagement.presenters.myAccount.MyAccountPresenter;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.views.login.LoginActivity;
import luungoclan.min.traveltourmanagement.views.main.MainActivity;
import luungoclan.min.traveltourmanagement.views.myProfile.MyProfileActivity;
import okhttp3.RequestBody;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment implements IMyAccountFragment, View.OnClickListener {
    @BindView(R.id.imv_avatar)
    ImageView imvAvatar;
    @BindView(R.id.btn_signUp)
    Button btnSignUp;
    @BindView(R.id.btn_signIn)
    Button btnSignIn;
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;

    private ExpandableListAdapter expandableListAdapter;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    private SharedPreferences sharedPreferences;
    private View view;
    private JSONObject jsonObject;
    private MyAccountPresenter myAccountPresenter;
    private boolean isLoggingIn;

    public MyAccountFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_account, container, false);
        ButterKnife.bind(this, view);
        sharedPreferences = getActivity().getSharedPreferences(Common.PREF_REMEMBER_ME, MODE_PRIVATE);
        isLoggingIn = sharedPreferences.getBoolean(Common.IS_LOGGING_IN, false);
        myAccountPresenter = new MyAccountPresenter(this);
        checkDisplayView();
        createExpandlist();
        prepareMenuData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        isLoggingIn = sharedPreferences.getBoolean(Common.IS_LOGGING_IN, false);
        //Delete all data headerList
        if (isLoggingIn()) {
            checkDisplayView();
            headerList.clear();
            //create new menu when user logged in
            prepareMenuData();

        }
    }

    private void checkDisplayView() {
        if (isLoggingIn()) {
            btnSignIn.setVisibility(View.GONE);
            btnSignUp.setVisibility(View.GONE);
        } else {
            btnSignUp.setOnClickListener(this);
            btnSignIn.setOnClickListener(this);
        }
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
                            if (isLoggingIn()) {
                                Toast.makeText(getContext(), "My booking", Toast.LENGTH_SHORT).show();
                            } else {
                                createIntentSignin();
                            }
//                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OverTimeListFragment()).commit();
//                            getSupportActionBar().setTitle(R.string.menu_overtime);
                        }
                        if (headerList.get(groupPosition).id.equals(getString(R.string.menu_id_myprofile))) {
                            if (isLoggingIn()) {
                                startActivity(new Intent(getActivity(), MyProfileActivity.class));
                            } else {
                                createIntentSignin();
                            }
//                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OverTimeListFragment()).commit();
//                            getSupportActionBar().setTitle(R.string.menu_overtime);
                        }
                        if (headerList.get(groupPosition).id.equals(getString(R.string.menu_id_myreviews))) {
                            if (isLoggingIn()) {
                                Toast.makeText(getContext(), "My Reviews", Toast.LENGTH_SHORT).show();
                            } else {
                                createIntentSignin();
                            }
//                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OverTimeListFragment()).commit();
//                            getSupportActionBar().setTitle(R.string.menu_overtime);
                        }
                        if (headerList.get(groupPosition).id.equals(getString(R.string.menu_id_logout))) {
                            passDataLogoutToJson();
                            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                            myAccountPresenter.logout(MainActivity.token, body);
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
        if (isLoggingIn()) {
            menuModel = new MenuModel(getString(R.string.menu_id_logout), getString(R.string.menu_logout), true, false, getResources().getDrawable(R.drawable.ic_logout));
            headerList.add(menuModel);
            if (!menuModel.hasChildren) {
                childList.put(menuModel, null);
            }
        }

    }

    @Override
    public void logoutSuccess(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        MainActivity.token = null;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Common.IS_LOGGING_IN, false);
        editor.commit();
        headerList.clear();
        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();

    }

    @Override
    public void logoutFailure(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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
        getActivity().startActivity(intent);
    }

    public boolean isLoggingIn() {
        return isLoggingIn;
    }
}

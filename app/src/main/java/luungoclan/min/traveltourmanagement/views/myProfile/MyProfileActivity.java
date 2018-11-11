package luungoclan.min.traveltourmanagement.views.myProfile;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfile;
import luungoclan.min.traveltourmanagement.presenters.login.LoginPresenter;
import luungoclan.min.traveltourmanagement.presenters.myProfile.MyProfilePresenter;
import luungoclan.min.traveltourmanagement.views.main.MainActivity;

public class MyProfileActivity extends AppCompatActivity implements IMyProfileActivity {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView tvFullname, tvUsername, tvEmail, tvPhone, tvAddress;
    private MyProfilePresenter myProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        init();
        getProfile();
    }

    private void getProfile() {

        String token = MainActivity.token;
        if (token != null) {
            myProfilePresenter.getMyProfile(token);
        } else {
            loadDefaultDataToView();
        }
    }

    private void init() {
        tvUsername = findViewById(R.id.tv_username);
        tvFullname = findViewById(R.id.tv_fullname);
        tvPhone = findViewById(R.id.tv_phone);
        tvEmail = findViewById(R.id.tv_email);
        tvAddress = findViewById(R.id.tv_address);
        initCollapsingToolbar();
        myProfilePresenter = new MyProfilePresenter(this);

    }

    private void initCollapsingToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("My Profile");
        //Display back home button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
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

    @Override
    public void getMyProfileSuccess(MyProfile myProfile) {
        loadDataToView(myProfile);
    }

    private void loadDataToView(MyProfile myProfile) {
        tvUsername.setText(myProfile.getUsername());
        tvFullname.setText(myProfile.getFullname());
        tvEmail.setText(myProfile.getEmail());
        tvPhone.setText(myProfile.getPhone());
        tvAddress.setText(myProfile.getAddress());
    }

    @Override
    public void getMyProfileFailure() {
        loadDefaultDataToView();
    }

    private void loadDefaultDataToView() {
        tvUsername.setText("--");
        tvFullname.setText("--");
        tvEmail.setText("--");
        tvPhone.setText("--");
        tvAddress.setText("--");
    }
}

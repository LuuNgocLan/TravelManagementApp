package luungoclan.min.traveltourmanagement.views.booking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.models.detailTour.DataDetailTour;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfile;
import luungoclan.min.traveltourmanagement.presenters.booking.AddBookingImpl;
import luungoclan.min.traveltourmanagement.presenters.booking.IAddBoookingImpl;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.utils.Utils;
import luungoclan.min.traveltourmanagement.utils.ViewDataUtils;
import luungoclan.min.traveltourmanagement.views.login.LoginActivity;
import luungoclan.min.traveltourmanagement.views.main.MainActivity;
import okhttp3.RequestBody;

public class BookingActivity extends AppCompatActivity implements BookingView {

    @BindView(R.id.tv_name_tour)
    TextView tvNameTour;
    @BindView(R.id.tv_departure_day)
    TextView tvDepartureDay;
    @BindView(R.id.tv_last_day)
    TextView tvLastDay;
    @BindView(R.id.tv_slot_remain)
    TextView tvSlotRemain;
    @BindView(R.id.tv_price_tour)
    TextView tvPriceTour;
    @BindView(R.id.edt_fullname)
    EditText edtFullname;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_addr)
    EditText edtAddr;
    @BindView(R.id.spn_adult)
    Spinner spnAdults;
    @BindView(R.id.spn_child)
    Spinner spnChilds;
    @BindView(R.id.edt_note)
    EditText edtNote;

    private IAddBoookingImpl iAddBoookingImpl;
    private SharedPreferences mSharedPreferences;
    private MyProfile currentUser = null;
    private String token;
    private int id_detail_tour = -1;
    private DataDetailTour detailTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);
        loadDefaultData();
        getDataIntent();
    }

    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        detailTour = (DataDetailTour) bundle.getSerializable(Common.BUNDLE_TOUR_DETAIL);
        if (detailTour != null) {
            ViewDataUtils.setDataToView(tvNameTour, detailTour.getName());
            ViewDataUtils.setDataToView(tvDepartureDay, detailTour.getDetail().getDateDepart());
            ViewDataUtils.setDataToView(tvSlotRemain, detailTour.getDetail().getSlot() + "");
            ViewDataUtils.setDataToView(tvPriceTour, detailTour.getDetail().getPriceAdults() + " VNĐ");

            id_detail_tour = detailTour.getDetail().getId();
        }
    }

    private void loadDefaultData() {
        mSharedPreferences = getSharedPreferences(Common.PREF_REMEMBER_ME, MODE_PRIVATE);
        token = mSharedPreferences.getString(Common.TOKEN_SAVED, null);

        Gson gson = new Gson();
        String json = mSharedPreferences.getString(Common.MYPROFILE, null);
        currentUser = gson.fromJson(json, MyProfile.class);

        if (currentUser != null) {
            ViewDataUtils.setDataToView(edtFullname, currentUser.getFullname());
            ViewDataUtils.setDataToView(edtEmail, currentUser.getEmail());
            ViewDataUtils.setDataToView(edtAddr, currentUser.getAddress());
            ViewDataUtils.setDataToView(edtPhone, currentUser.getPhone());
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @OnClick(R.id.btn_book_tour)
    public void onConfirmBookTour(View view) {

        iAddBoookingImpl = new AddBookingImpl(this);
        getDataAndCallApi();

        //Check send mail
//        Intent i = new Intent(Intent.ACTION_SEND);
//        i.setType("message/rfc822");
//        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"luungoclan125690@gmail.com"});
//        i.putExtra(Intent.EXTRA_SUBJECT, "Book Tour");
//        i.putExtra(Intent.EXTRA_TEXT, "Hi Lan, Thank you for booking tour!");
//        try {
//            startActivity(Intent.createChooser(i, "Send mail..."));
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//        }
    }

    private void getDataAndCallApi() {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("name", edtFullname.getText());
            jsonObject.put("email", edtEmail.getText());
            jsonObject.put("phone", edtPhone.getText());
            jsonObject.put("address", edtAddr.getText());
            jsonObject.put("note", edtNote.getText());
            jsonObject.put("num_adults", 1);
            jsonObject.put("num_childs", 1);
            jsonObject.put("id_detail_tour", id_detail_tour);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        if (Utils.isConnected(getBaseContext())) {
            iAddBoookingImpl.addNewBooking(token, body);

        } else {
            onShowProgressDialog("No internet!");
        }
    }

    @Override
    public void addBookingSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addBookingFailure() {
        Toast.makeText(this, "Failure!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onShowProgressDialog(String msg) {

    }

    @Override
    public void onDismissProgressDialog() {

    }
}

package luungoclan.min.traveltourmanagement.views.myProfile;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfile;
import luungoclan.min.traveltourmanagement.presenters.myProfile.EditProfileImpl;
import luungoclan.min.traveltourmanagement.presenters.myProfile.IEditProfileImpl;
import luungoclan.min.traveltourmanagement.ui.BaseHeaderBar;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.utils.ImageUtils;
import luungoclan.min.traveltourmanagement.views.main.MainActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class EditProfileActivity extends AppCompatActivity implements IEditProfileView {

    private static final int REQUEST_IMAGE = 1010;
    @BindView(R.id.baseHeaderBar)
    BaseHeaderBar mBaseHeaderBar;

    @BindView(R.id.imv_avatar)
    ImageView imvAvatar;

    @BindView(R.id.edt_username)
    EditText edtUsername;

    @BindView(R.id.edt_fullName)
    EditText edtFullName;

    @BindView(R.id.edt_email)
    EditText edtEmail;

    @BindView(R.id.edt_phone)
    EditText edtPhone;

    @BindView(R.id.edt_adress)
    EditText edtAddr;

    private SharedPreferences sharedPreferences;
    private MyProfile currentUser = null;
    private Uri imageUri;
    private IEditProfileImpl iEditProfileImpl;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_profile);
        ButterKnife.bind(this);
        init();
        getCurrentUser();

    }

    private void getCurrentUser() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Common.MYPROFILE, null);
        token = sharedPreferences.getString(Common.TOKEN_SAVED, null);
        currentUser = gson.fromJson(json, MyProfile.class);
        //show infor user
        edtUsername.setText(currentUser.getUsername());
        edtFullName.setText(currentUser.getFullname());
        edtEmail.setText(currentUser.getEmail());
        edtAddr.setText(currentUser.getAddress());
        edtPhone.setText(currentUser.getPhone());
        String urlImage = Common.BASE_URL + currentUser.getAvatar();
        Picasso.with(this)
                .load(urlImage)
                .placeholder(R.drawable.img_avatar)
                .error(R.drawable.img_avatar)
                .into(imvAvatar);

    }

    private void init() {
        sharedPreferences = getSharedPreferences(Common.PREF_REMEMBER_ME, MODE_PRIVATE);
        mBaseHeaderBar.setHeaderBarStyle(BaseHeaderBar.HeaderBarType.HEADER_BAR_EDIT_PROFILE);
        mBaseHeaderBar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mBaseHeaderBar.setTitleToolBar("Edit Profile");
        MenuItem save_item = mBaseHeaderBar.getMenuItem();
        save_item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_save) {
                    Toast.makeText(getBaseContext(), "save profile", Toast.LENGTH_SHORT).show();
                    uploadFile(imageUri);
                }
                return false;
            }
        });

        iEditProfileImpl = new EditProfileImpl(this);
    }

    private void uploadFile(Uri fileUri) {

        String filePath = ImageUtils.getRealPathFromUri(this, imageUri);
        if (filePath != null && !filePath.isEmpty()) {
            File file = new File(filePath);
            if (file.exists()) {
                // creates RequestBody instance from file
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                // MultipartBody.Part is used to send also the actual filename
                MultipartBody.Part avatar = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);
                MultipartBody.Part fullname = MultipartBody.Part.createFormData("fullname", edtFullName.getText().toString());
                MultipartBody.Part email = MultipartBody.Part.createFormData("email", edtEmail.getText().toString());
                MultipartBody.Part phone = MultipartBody.Part.createFormData("phone", edtPhone.getText().toString());
                MultipartBody.Part address = MultipartBody.Part.createFormData("address", edtAddr.getText().toString());
                iEditProfileImpl.onEditProfile(
                        token,
                        avatar,
                        fullname,
                        email,
                        phone,
                        address);


            }
        }
    }

    @OnClick(R.id.imv_avatar)
    public void chooseImage(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        Uri data = Uri.parse(pictureDirectoryPath);
        photoPickerIntent.setDataAndType(data, "image/*");
        startActivityForResult(photoPickerIntent, REQUEST_IMAGE);
    }

    @OnClick(R.id.btn_change_password)
    public void onCallActionChangePassword(View view) {
        Toast.makeText(this, "Change password!", Toast.LENGTH_SHORT).show();
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_change_password);
        Button dialogButton = (Button) dialog.findViewById(R.id.btnUpdatePass);

        dialog.show();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE) {
                imageUri = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap imageAvatar = BitmapFactory.decodeStream(inputStream);
                    imvAvatar.setImageBitmap(imageAvatar);
                    //avatarBitmap = imageAvatar;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Image not found", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void editProfileSuccess() {
        Toast.makeText(this, "Edit profile success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editProfileFailure() {
        Toast.makeText(this, "Edit profile Failure!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changePasswordSuccess() {

    }

    @Override
    public void changePasswordFailure() {

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
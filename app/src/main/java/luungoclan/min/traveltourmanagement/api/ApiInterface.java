package luungoclan.min.traveltourmanagement.api;

import luungoclan.min.traveltourmanagement.models.login.LoginResponse;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfileResponse;
import luungoclan.min.traveltourmanagement.models.places.PlaceResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("api/login")
    Call<LoginResponse> getToken(@Body RequestBody json);

    @GET("api/profile")
    Call<MyProfileResponse> getMyProfile(@Header("Authorization") String token);

    @GET("api/location")
    Call<PlaceResponse> getPlaceList();



}

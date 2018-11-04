package luungoclan.min.traveltourmanagement.api;

import luungoclan.min.traveltourmanagement.models.detailTour.DetailTourResponse;
import luungoclan.min.traveltourmanagement.models.login.LoginResponse;
import luungoclan.min.traveltourmanagement.models.logout.LogoutResponse;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfileResponse;
import luungoclan.min.traveltourmanagement.models.places.PlaceResponse;
import luungoclan.min.traveltourmanagement.models.reviewOfTour.ReviewOfTourResponse;
import luungoclan.min.traveltourmanagement.models.tourList.TourListResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    /**
     * Login
     *
     * @param json
     * @return
     */
    @POST("api/logout")
    Call<LogoutResponse> logout(@Header("Authorization") String token, @Body RequestBody json);

    /**
     * Login
     *
     * @param json
     * @return
     */
    @POST("api/login")
    Call<LoginResponse> getToken(@Body RequestBody json);


    /**
     * get my profile
     *
     * @param token
     * @return
     */
    @GET("api/profile")
    Call<MyProfileResponse> getMyProfile(@Header("Authorization") String token);

    /**
     * Get list place
     *
     * @return
     */
    @GET("api/location")
    Call<PlaceResponse> getPlaceList();

    /**
     * get list tour sale
     *
     * @return
     */
    @GET("api/tour/sales")
    Call<TourListResponse> getTourSaleList();

    /**
     * get Detail of tour by id
     *
     * @param idTour
     * @return
     */
    @GET("api/tour/{id}")
    Call<DetailTourResponse> getDetailTour(@Path(value = "id", encoded = true) int idTour);

    /**
     * get list review of Tour
     *
     * @param idTour
     * @return
     */
    @GET("api/review/tour/{id}")
    Call<ReviewOfTourResponse> getReviewOfTour(@Path(value = "id", encoded = true) int idTour);

}

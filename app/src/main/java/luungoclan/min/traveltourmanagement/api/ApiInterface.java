package luungoclan.min.traveltourmanagement.api;

import luungoclan.min.traveltourmanagement.models.detailTour.DetailTourResponse;
import luungoclan.min.traveltourmanagement.models.detailTour.TourAnotherDayResponse;
import luungoclan.min.traveltourmanagement.models.login.LoginResponse;
import luungoclan.min.traveltourmanagement.models.logout.LogoutResponse;
import luungoclan.min.traveltourmanagement.models.myProfile.EditProfileResponse;
import luungoclan.min.traveltourmanagement.models.myProfile.MyProfileResponse;
import luungoclan.min.traveltourmanagement.models.places.PlaceResponse;
import luungoclan.min.traveltourmanagement.models.tourList.tourInPlace.TourInPlaceResponse;
import luungoclan.min.traveltourmanagement.models.reviewOfTour.ReviewOfTourResponse;
import luungoclan.min.traveltourmanagement.models.reviewOfUser.ReviewOfUserResponse;
import luungoclan.min.traveltourmanagement.models.reviewOfUser.addReview.AddReviewResponse;
import luungoclan.min.traveltourmanagement.models.tourList.AllTourResponse;
import luungoclan.min.traveltourmanagement.models.tourList.SearchTourResponse;
import luungoclan.min.traveltourmanagement.models.tourList.TourLatestResponse;
import luungoclan.min.traveltourmanagement.models.tourList.TourListResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {
    @POST("api/logout")
    Call<LogoutResponse> logout(@Header("Authorization") String token, @Body RequestBody json);

    @POST("api/login")
    Call<LoginResponse> getToken(@Body RequestBody json);

    @GET("api/profile")
    Call<MyProfileResponse> getMyProfile(@Header("Authorization") String token);

    @GET("api/location")
    Call<PlaceResponse> getPlaceList();

    @GET("api/tour/sales")
    Call<TourListResponse> getTourSaleList();

    @GET("api/tour")
    Call<AllTourResponse> getAllTour();

    @POST("api/search-tour")
    Call<SearchTourResponse> getResultSearch(@Body RequestBody json);

    @GET("api/detail/{id}")
    Call<DetailTourResponse> getDetailTour(@Path(value = "id", encoded = true) int idTour);

    @GET("api/review/tour/{id}")
    Call<ReviewOfTourResponse> getReviewOfTour(@Path(value = "id", encoded = true) int idTour);

    @GET("api/detail/other/{id}")
    Call<TourAnotherDayResponse> getAnotherDayOfTour(@Path(value = "id", encoded = true) int idTour);

    @GET("api/tour/location/{idPlace}")
    Call<TourInPlaceResponse> getTourInPlace(@Path(value = "idPlace", encoded = true) int idPlace);

    @GET("api/user/review")
    Call<ReviewOfUserResponse> getReviewOfUser(@Header("Authorization") String token);

    @POST("api/review")
    Call<AddReviewResponse> addReview(@Header("Authorization") String token,
                                      @Body RequestBody json);

    @GET("api/tour/latest")
    Call<TourLatestResponse> getLatestTourList();

    @GET("api/location/favorite")
    Call<PlaceResponse> getTopFavoritePlaces();

    @Multipart
    @POST("api/profile/edit")
    Call<EditProfileResponse> editProfile(@Header("Authorization") String token,
                                          @Part MultipartBody.Part avatar,
                                          @Part MultipartBody.Part fullname,
                                          @Part MultipartBody.Part email,
                                          @Part MultipartBody.Part phone,
                                          @Part MultipartBody.Part address
    );

    @Multipart
    @POST("api/profile/edit")
    Call<EditProfileResponse> changePassword(@Header("Authorization") String token,
                                             @Part MultipartBody.Part password);

    

}
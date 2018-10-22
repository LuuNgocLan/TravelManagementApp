package luungoclan.min.traveltourmanagement.models.myProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyProfileResponse {
    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private MyProfile myProfile;

    /**
     * No args constructor for use in serialization
     *
     */
    public MyProfileResponse() {
    }

    /**
     *
     * @param resultCode
     * @param myProfile
     * @param resultMessage
     */
    public MyProfileResponse(Integer resultCode, String resultMessage, MyProfile myProfile) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.myProfile = myProfile;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public MyProfile getMyProfile() {
        return myProfile;
    }

    public void setMyProfile(MyProfile myProfile) {
        this.myProfile = myProfile;
    }

}

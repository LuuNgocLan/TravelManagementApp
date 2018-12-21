package luungoclan.min.traveltourmanagement.models.places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceResponse {
    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private PlaceData data;

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

    public PlaceData getData() {
        return data;
    }

    public void setData(PlaceData data) {
        this.data = data;
    }
}

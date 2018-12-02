package luungoclan.min.traveltourmanagement.models.tourList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourLatestResponse {

    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private DataTourList data;

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

    public DataTourList getData() {
        return data;
    }

    public void setData(DataTourList data) {
        this.data = data;
    }

}

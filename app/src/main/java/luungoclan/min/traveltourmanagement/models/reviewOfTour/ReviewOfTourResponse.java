package luungoclan.min.traveltourmanagement.models.reviewOfTour;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewOfTourResponse {
    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private DataReview dataReview;

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

    public DataReview getDataReview() {
        return dataReview;
    }

    public void setDataReview(DataReview dataReview) {
        this.dataReview = dataReview;
    }
}

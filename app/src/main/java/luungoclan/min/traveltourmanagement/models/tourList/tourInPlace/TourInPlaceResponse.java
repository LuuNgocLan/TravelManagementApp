package luungoclan.min.traveltourmanagement.models.tourList.tourInPlace;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourInPlaceResponse {
    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private DataTour dataTour;

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

    public DataTour getDataTour() {
        return dataTour;
    }

    public void setDataTour(DataTour dataTour) {
        this.dataTour = dataTour;
    }
}

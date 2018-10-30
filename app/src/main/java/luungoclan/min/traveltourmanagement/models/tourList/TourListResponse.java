package luungoclan.min.traveltourmanagement.models.tourList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourListResponse {
    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private DataTourList dataTourList;

    /**
     * No args constructor for use in serialization
     *
     */
    public TourListResponse() {
    }

    /**
     *
     * @param resultCode
     * @param dataTourList
     * @param resultMessage
     */
    public TourListResponse(Integer resultCode, String resultMessage, DataTourList dataTourList) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.dataTourList = dataTourList;
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

    public DataTourList getDataTourList() {
        return dataTourList;
    }

    public void setDataTourList(DataTourList dataTourList) {
        this.dataTourList = dataTourList;
    }

}

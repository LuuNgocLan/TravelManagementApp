package luungoclan.min.traveltourmanagement.models.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingData {
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private java.util.List<Booking> list = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public java.util.List<Booking> getList() {
        return list;
    }

    public void setList(java.util.List<Booking> list) {
        this.list = list;
    }

}

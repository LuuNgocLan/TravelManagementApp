package luungoclan.min.traveltourmanagement.models.slide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSlide {
    private Integer total;
    @SerializedName("list")
    @Expose
    private java.util.List<Slide> list = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public java.util.List<Slide> getList() {
        return list;
    }

    public void setList(java.util.List<Slide> list) {
        this.list = list;
    }

}

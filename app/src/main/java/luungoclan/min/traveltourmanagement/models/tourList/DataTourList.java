package luungoclan.min.traveltourmanagement.models.tourList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataTourList {
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private List<Tour> tour = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public DataTourList() {
    }

    /**
     *
     * @param total
     * @param tour
     */
    public DataTourList(Integer total, List<Tour> tour) {
        super();
        this.total = total;
        this.tour = tour;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Tour> getTour() {
        return tour;
    }

    public void setTour(List<Tour> tour) {
        this.tour = tour;
    }
}

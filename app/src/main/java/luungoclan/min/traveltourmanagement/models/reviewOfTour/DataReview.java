package luungoclan.min.traveltourmanagement.models.reviewOfTour;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataReview {
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private List<Review> reviewOfTour = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Review> getReviewOfTour() {
        return reviewOfTour;
    }

    public void setReviewOfTour(List<Review> reviewOfTour) {
        this.reviewOfTour = reviewOfTour;
    }
}

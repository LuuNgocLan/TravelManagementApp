package luungoclan.min.traveltourmanagement.models.detailTour;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataTour {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date_depart")
    @Expose
    private String dateDepart;
    @SerializedName("price_adults")
    @Expose
    private Integer priceAdults;
    @SerializedName("price_childs")
    @Expose
    private Integer priceChilds;
    @SerializedName("time_depart")
    @Expose
    private String timeDepart;
    @SerializedName("address_depart")
    @Expose
    private String addressDepart;
    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("guide")
    @Expose
    private Guide guide;
    @SerializedName("hotel")
    @Expose
    private List<Hotel> hotel = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Integer getPriceAdults() {
        return priceAdults;
    }

    public void setPriceAdults(Integer priceAdults) {
        this.priceAdults = priceAdults;
    }

    public Integer getPriceChilds() {
        return priceChilds;
    }

    public void setPriceChilds(Integer priceChilds) {
        this.priceChilds = priceChilds;
    }

    public String getTimeDepart() {
        return timeDepart;
    }

    public void setTimeDepart(String timeDepart) {
        this.timeDepart = timeDepart;
    }

    public String getAddressDepart() {
        return addressDepart;
    }

    public void setAddressDepart(String addressDepart) {
        this.addressDepart = addressDepart;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    public List<Hotel> getHotel() {
        return hotel;
    }

    public void setHotel(List<Hotel> hotel) {
        this.hotel = hotel;
    }
}

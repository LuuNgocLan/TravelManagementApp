package luungoclan.min.traveltourmanagement.models.places.tourInPlace;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tour {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number_days")
    @Expose
    private Integer numberDays;
    @SerializedName("item_tour")
    @Expose
    private String itemTour;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("images")
    @Expose
    private String images;
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
    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("booked")
    @Expose
    private Integer booked;
    @SerializedName("id_type_tour")
    @Expose
    private Integer idTypeTour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(Integer numberDays) {
        this.numberDays = numberDays;
    }

    public String getItemTour() {
        return itemTour;
    }

    public void setItemTour(String itemTour) {
        this.itemTour = itemTour;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Integer getBooked() {
        return booked;
    }

    public void setBooked(Integer booked) {
        this.booked = booked;
    }

    public Integer getIdTypeTour() {
        return idTypeTour;
    }

    public void setIdTypeTour(Integer idTypeTour) {
        this.idTypeTour = idTypeTour;
    }
}

package luungoclan.min.traveltourmanagement.models.tourList;

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
    @SerializedName("booked")
    @Expose
    private Integer booked;
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

    /**
     * No args constructor for use in serialization
     *
     */
    public Tour() {
    }

    /**
     *
     * @param id
     * @param numberDays
     * @param itemTour
     * @param priceAdults
     * @param dateDepart
     * @param booked
     * @param name
     * @param priceChilds
     * @param images
     * @param slot
     * @param timeDepart
     * @param discount
     */
    public Tour(Integer id, String name, Integer numberDays, String itemTour, Integer discount, Integer booked, String images, String dateDepart, Integer priceAdults, Integer priceChilds, String timeDepart, Integer slot) {
        super();
        this.id = id;
        this.name = name;
        this.numberDays = numberDays;
        this.itemTour = itemTour;
        this.discount = discount;
        this.booked = booked;
        this.images = images;
        this.dateDepart = dateDepart;
        this.priceAdults = priceAdults;
        this.priceChilds = priceChilds;
        this.timeDepart = timeDepart;
        this.slot = slot;
    }

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

    public Integer getBooked() {
        return booked;
    }

    public void setBooked(Integer booked) {
        this.booked = booked;
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
}

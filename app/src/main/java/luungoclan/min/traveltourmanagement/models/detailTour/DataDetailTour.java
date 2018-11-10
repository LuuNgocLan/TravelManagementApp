package luungoclan.min.traveltourmanagement.models.detailTour;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataDetailTour {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number_days")
    @Expose
    private Integer numberDays;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
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
    @SerializedName("programs")
    @Expose
    private String programs;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("type_tour")
    @Expose
    private String typeTour;
    @SerializedName("detail")
    @Expose
    private Detail detail;
    @SerializedName("location")
    @Expose
    private List<Location> location = null;
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
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

    public String getPrograms() {
        return programs;
    }

    public void setPrograms(String programs) {
        this.programs = programs;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTypeTour() {
        return typeTour;
    }

    public void setTypeTour(String typeTour) {
        this.typeTour = typeTour;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
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

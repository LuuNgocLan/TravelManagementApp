package luungoclan.min.traveltourmanagement.models.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Booking {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("num_adults")
    @Expose
    private Integer numAdults;
    @SerializedName("num_childs")
    @Expose
    private Integer numChilds;
    @SerializedName("date_ordered")
    @Expose
    private String dateOrdered;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("id_detail_tour")
    @Expose
    private Integer idDetailTour;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNumAdults() {
        return numAdults;
    }

    public void setNumAdults(Integer numAdults) {
        this.numAdults = numAdults;
    }

    public Integer getNumChilds() {
        return numChilds;
    }

    public void setNumChilds(Integer numChilds) {
        this.numChilds = numChilds;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIdDetailTour() {
        return idDetailTour;
    }

    public void setIdDetailTour(Integer idDetailTour) {
        this.idDetailTour = idDetailTour;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}

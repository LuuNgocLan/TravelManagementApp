package luungoclan.min.traveltourmanagement.models.myProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyProfile {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;

    /**
     * No args constructor for use in serialization
     */
    public MyProfile() {
    }

    /**
     * @param id
     * @param phone
     * @param username
     * @param address
     * @param email
     * @param active
     * @param fullname
     */
    public MyProfile(Integer id, String username, Integer active, String fullname, String email, String phone, String address) {
        super();
        this.id = id;
        this.username = username;
        this.active = active;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

}
package luungoclan.min.traveltourmanagement.models.db;

public class Profile {
    public String username;
    public String email;
    public String fullname;
    public String address;
    public String phone;

    public Profile(String username, String email, String fullname, String address, String phone) {
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

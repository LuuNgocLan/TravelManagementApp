package luungoclan.min.traveltourmanagement.models.slide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Slide {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("id_tour")
    @Expose
    private Integer idTour;
    @SerializedName("id_detail_tour")
    @Expose
    private Integer idDetailTour;
    @SerializedName("name_tour")
    @Expose
    private String nameTour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIdTour() {
        return idTour;
    }

    public void setIdTour(Integer idTour) {
        this.idTour = idTour;
    }

    public Integer getIdDetailTour() {
        return idDetailTour;
    }

    public void setIdDetailTour(Integer idDetailTour) {
        this.idDetailTour = idDetailTour;
    }

    public String getNameTour() {
        return nameTour;
    }

    public void setNameTour(String nameTour) {
        this.nameTour = nameTour;
    }

}

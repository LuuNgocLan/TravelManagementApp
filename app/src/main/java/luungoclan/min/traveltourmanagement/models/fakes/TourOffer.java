package luungoclan.min.traveltourmanagement.models.fakes;

public class TourOffer {
    private int imageTour;
    private String titleTour;

    public TourOffer(int imageTour, String titleTour) {
        this.imageTour = imageTour;
        this.titleTour = titleTour;
    }

    public int getImageTour() {
        return imageTour;
    }

    public void setImageTour(int imageTour) {
        this.imageTour = imageTour;
    }

    public String getTitleTour() {
        return titleTour;
    }

    public void setTitleTour(String titleTour) {
        this.titleTour = titleTour;
    }
}

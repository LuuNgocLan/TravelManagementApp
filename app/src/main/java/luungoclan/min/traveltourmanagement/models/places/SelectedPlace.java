package luungoclan.min.traveltourmanagement.models.places;

public class SelectedPlace extends Place {
    private boolean isSelected = false;


    public SelectedPlace(Place item, boolean isSelected) {
        super(item.getName(),item.getId());
        this.isSelected = isSelected;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

package luungoclan.min.traveltourmanagement.models.menu;

import android.graphics.drawable.Drawable;

public class MenuModel {
    public String id;
    public String menuName;
    public Drawable drawable;
    public boolean hasChildren, isGroup;

    public MenuModel(String id, String menuName, boolean isGroup, boolean hasChildren, Drawable drawable) {
        this.id = id;
        this.menuName = menuName;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
        this.drawable = drawable;

    }
}

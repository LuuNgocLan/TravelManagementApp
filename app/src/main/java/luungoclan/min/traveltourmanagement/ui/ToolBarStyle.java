package luungoclan.min.traveltourmanagement.ui;

public class ToolBarStyle {

    public static final int DEFAULT_NONE_RESOURCE_ID = -1;

    private final int leftResId;
    private final int rightResId;
    private String title;

    private ToolBarStyle(Builder builder) {
        this.leftResId = builder.leftResId;
        this.rightResId = builder.rightResId;
        this.title = builder.title;
    }

    public int getLeftResId() {
        return leftResId;
    }

    public String getTitle() {
        return title;
    }

    public int getRightResId() {
        return rightResId;
    }

    public static class Builder {
        private int leftResId = DEFAULT_NONE_RESOURCE_ID;
        private int rightResId = DEFAULT_NONE_RESOURCE_ID;
        private String title;

        public Builder leftResId(int leftResId) {
            this.leftResId = leftResId;
            return this;
        }

        public Builder rightResId(int rightResId) {
            this.rightResId = rightResId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public ToolBarStyle build() {
            ToolBarStyle user = new ToolBarStyle(this);
//            if (user.getLeftResId() > 120) {
//                throw new IllegalStateException("Age out of range"); // thread-safe
//            }
            return user;
        }
    }
}
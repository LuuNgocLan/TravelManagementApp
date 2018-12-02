package luungoclan.min.traveltourmanagement.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import luungoclan.min.traveltourmanagement.R;

import static luungoclan.min.traveltourmanagement.ui.ToolBarStyle.DEFAULT_NONE_RESOURCE_ID;

public class BaseHeaderBar extends LinearLayout {
    View statusbar;
    Toolbar toolbar;
    TextView titleToolbar;

    public BaseHeaderBar(Context context) {
        super(context);
        init();
    }

    public BaseHeaderBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseHeaderBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.partial_base_header_bar, this);
        this.toolbar = findViewById(R.id.header_bar_toolbar);
        this.titleToolbar = findViewById(R.id.toolbar_title);
        setEvent();
    }

    private void setEvent() {
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).onBackPressed();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_search) {

                }
                return false;
            }
        });
    }

    public void setHeaderBarStyle(HeaderBarType type) {
        switch (type) {
            case HEADER_BAR_MAIN:
                setToolbar(new ToolBarStyle.Builder()
                        .rightResId(R.menu.search_menu)
                        .build());
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case HEADER_BAR_EDIT_PROFILE:
                setToolbar(new ToolBarStyle.Builder()
                        .leftResId(R.drawable.ic_back_white)
                        .rightResId(R.menu.edit_profile_menu)
                        .build());
                break;
            case HEADER_BAR_DEFAULT:
                setToolbar(new ToolBarStyle.Builder()
                        .leftResId(R.drawable.ic_back)
                        .build());
                break;
            case HEADER_BAR_DEFAULT_WHITE:
                setToolbar(new ToolBarStyle.Builder()
                        .leftResId(R.drawable.ic_back_white)
                        .build());
                break;
            case HEADER_BAR_ACCOUNT:
                setToolbar(new ToolBarStyle.Builder()
                        .leftResId(R.drawable.ic_slider)
                        .rightResId(R.menu.logout_menu)
                        .build());
                break;
        }

    }

    private void setToolbar(ToolBarStyle style) {

        if (style.getLeftResId() != DEFAULT_NONE_RESOURCE_ID) {
            toolbar.setNavigationIcon(style.getLeftResId());
        }

        if (style.getRightResId() != DEFAULT_NONE_RESOURCE_ID) {
            toolbar.inflateMenu(style.getRightResId());
        }
    }

    public void setTitleToolBar(String title) {
        if (!TextUtils.isEmpty(title)) {
            titleToolbar.setText(title);
        }
    }

    public void setTitleSize(int size) {
        titleToolbar.setTextSize(size);
    }

    public void setBackgroundColor(int color) {
        toolbar.setBackgroundColor(color);
    }

    public void setColor(int color) {
        titleToolbar.setTextColor(color);

    }

    public void disableStatusBar() {
        statusbar.setVisibility(GONE);
    }

    public MenuItem getMenuItem() {
        return toolbar.getMenu().getItem(0);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public TextView getTitleToolbar() {
        return titleToolbar;
    }

    public void setTitleToolbar(TextView titleToolbar) {
        this.titleToolbar = titleToolbar;
    }

    public enum HeaderBarType {
        HEADER_BAR_MAIN,
        HEADER_BAR_EDIT_PROFILE,
        HEADER_BAR_DEFAULT_WHITE,
        HEADER_BAR_DEFAULT,
        HEADER_BAR_ACCOUNT
    }

}
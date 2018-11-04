package luungoclan.min.traveltourmanagement.base;

import android.content.Context;

/**
 * by luungoclan
 */
public interface IBaseView {
    Context getContext();
    void onShowProgressDialog(String msg);
    void onDismissProgressDialog();
}

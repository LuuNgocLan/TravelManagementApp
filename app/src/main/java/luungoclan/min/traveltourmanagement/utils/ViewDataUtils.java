package luungoclan.min.traveltourmanagement.utils;

import android.widget.TextView;


import luungoclan.min.traveltourmanagement.R;

public class ViewDataUtils {

    /**
     *  check data have value or null, set default "--" value if data null
     *  avoid getting data null form server--> crash app
     * @param view
     * @param data
     */
    public static void setDataToView(TextView view, Object data){
        if(data!=null) {
            view.setText(data.toString());
        } else {
            view.setText(R.string.infor_null);
        }
    }

    /**
     * check date not null, after: date yyyy-mm-dd will convert to dd-mm-yyyy
     * @param view
     * @param data
     */
    public static void setDataDateToView(TextView view, Object data){
        if(data!=null) {
            view.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(data.toString()));
        } else {
            view.setText(R.string.infor_null);
        }
    }

    /**
     *
     * @param view
     * @param data
     */
    public static void setDataTimeWithHH_MM(TextView view, Object data) {
        if (data != null) {
            view.setText(StringUtils.formatTimehh_mm(data.toString()));
        } else {
            view.setText(R.string.infor_null);
        }
    }

}

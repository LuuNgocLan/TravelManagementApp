package luungoclan.min.traveltourmanagement.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Common {
    /**
     * check status of Internet
     *
     * @return status
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    /**
     * resize length of string with default value
     * Ex: "welcome to vietnam, this is beautiful contry! " -> "welcome to vietnam, ..."
     * @param inputString
     * @return
     */
    public static String cuttingString(String inputString){
        if(inputString.length()>Constants.LENGTH_TITLE_IN_ITEM){
            return inputString.substring(0,Constants.LENGTH_TITLE_IN_ITEM-3)+"...";
        }
        return inputString;
    }
}

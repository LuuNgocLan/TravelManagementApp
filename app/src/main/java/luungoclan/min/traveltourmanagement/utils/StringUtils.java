package luungoclan.min.traveltourmanagement.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {
    /**
     * convert string date with format yyyy-mm-dd to dd-mm-yyyy
     *
     * @param st
     * @return
     */
    public static String yyyy_mm_ddTodd_mm_yyyy(String st) {

        return st.split("[-/]")[0].length() == 4
                ? st.split("[-/]")[2] + "-" + st.split("[-/]")[1] + "-" + st.split("[-/]")[0]
                : st;
    }

    public static String toUpperCaseFirstChar(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * delete unnecessary space in string
     * * @param st
     * @return
     */
    public static String deleteUnnecessarySpace(String st){
        String s="";
        if(st.length()==0) return "";
        for(int i=0; i<st.length()-1; i++){
            if(st.charAt(i)==' ' && st.charAt(i+1)==' '){
                continue;
            }
            s+=(char)st.charAt(i);
        }
        if(st.charAt(st.length()-1)!=' '){
            s+=(char)st.charAt(st.length()-1);
        }
        return s;
    }

    /**
     * if st=12.0 I will display number 12, else st=12.5 display 12.5 number
     * this function to format number absence in screen
     * @param st
     * @return
     */
    public static String formatDisplayNumberDouble(String st){
        int indexOfDot = st.length()-2;

        if(st.substring(indexOfDot+1).equals("0")){
            return st.substring(0,indexOfDot);
        }
        return st;
    }

    /**
     * chuyen chuoi co dau thanh khong dau
     * @param title
     * @return
     */
    public static String createSlug(String title) {
        String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        slug = pattern.matcher(slug).replaceAll("");
        slug = slug.toLowerCase();
        // Thay đ thành d
        slug = slug.replaceAll("đ", "d");
        // Xóa các ký tự đặt biệt
        slug = slug.replaceAll("([^0-9a-z-\\s])", "");
        // đổi nhiều ký tự gạch ngang liên tiếp thành 1 ký tự gạch ngang
        slug = slug.replaceAll("(-+)", "-");
        // Xóa các ký tự gạch ngang ở đầu và cuối
        slug = slug.replaceAll("^-+", "");
        slug = slug.replaceAll("-+$", "");
        return slug;
    }

    /**
     * convert text with format (yyyy-MM-dd) to text with format ddMMyyyy
     * @param text
     * @return ddMMyyyy
     */
    public static String convertDateFromServerToEditText(String text){
        String[] split = text.split("-");
        return split[2]+split[1]+split[0];
    }
    /**
     * convert text with format (dd-MM-yyyy) to text with format yyyy-MM-dd
     * @param text
     * @return dd-MM-yyyy
     */
    public static String convertDateEditTextToServer(String text){
        String[] split = text.split("-");
        return split[2]+"-"+split[1]+"-"+split[0];
    }

    /**
     * convert text with format hh:mm:ss to text with format hhmm
     * @param text
     * @return hhmm
     */
    public static String convertTimeFromServerToEditText(String text){
        String[] split = text.split(":");
        return split[0]+split[1];
    }

    /**
     * convert text with format hh:mm:ss to text with hh:mm
     *
     * @param text
     * @return hh:mm
     */
    public static String formatTimehh_mm(String text) {
        String[] split = text.split(":");
        return split[0] +":"+ split[1];
    }
    /**
     * convert text with format hh:mm to text with hh:mm:ss
     *
     * @param text
     * @return hh:mm:ss
     */
    public static String convertTimehh_mmTohh_mm_ss(String text) {
        String[] split = text.split(":");
        return split[0] +":"+ split[1] +":00";
    }
}

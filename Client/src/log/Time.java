package log;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author LHS
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {

    public static final String DATE_FORMAT_NOW = "HH:mm:ss";
    public static final String DATE_FORMAT_NOW1 = "yyyy-MM-dd HH:mm:ss";

    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW1);
        return sdf.format(cal.getTime());
    }
}
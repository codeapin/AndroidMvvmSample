package id.codeapin.samplejetpack.utils;


import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import id.codeapin.samplejetpack.injection.annotation.PerApplication;
import timber.log.Timber;

@PerApplication
public class DateTimeUtil {


    public static final String DEFAULT_TIME_FORMAT = "HH:mm";
    public static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy";
    public static final String DEFAULT_DATE_FORMAT_NO_LINE = "yyyyMMdd";
    public static final String ID_DATE_FORMAT = "EEEE, dd MMMM yyyy";
    public static final String Iso_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm";
    public static final String TOKEN_DATE_FORMAT = "EEE, d MMM yyyy HH:mm:ss z";

    private static SimpleDateFormat simpleDateFormat;

    @Inject
    public DateTimeUtil() {
        this.simpleDateFormat = new SimpleDateFormat();
    }


    // ---------------------------------------------------------------------------------------------
    // TIME ----------------------------------------------------------------------------------------

    public String getStringTimeFromDate(Date date) {
        simpleDateFormat.applyPattern(DEFAULT_TIME_FORMAT);
        return simpleDateFormat.format(date);
    }


    public String getStringTimeFromCalendar(Calendar calendar) {
        simpleDateFormat.applyPattern(DEFAULT_TIME_FORMAT);
        return simpleDateFormat.format(calendar.getTime());
    }

    public String getStringFromTimeIsoString(String IsoTime) {
        Date dateFromIsoString = getDateFromIsoString(IsoTime);
        return getStringTimeFromDate(dateFromIsoString);
    }

    public String getIsoStringFromStringTime(String time, Calendar calendar) {
        Calendar c = (Calendar) calendar.clone();
        try {
            setCalendarFromStringTime(c, time);
            return getIsoStringFromDate(c.getTime());
        } catch (ParseException e) {
            Timber.e(e);
            return null;
        }
    }

    public void setCalendarFromStringTime(Calendar calendar, String stringTime) throws ParseException {
        if (stringTime.length() > 5) {
            throw new ParseException("String time is not 'HH:mm'", 7);
        }

        Calendar c = Calendar.getInstance();
        stringTime = stringTime + ":00";
        Time time = Time.valueOf(stringTime);
        c.setTime(time);

        calendar.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
    }

    // ---------------------------------------------------------------------------------------------
    // DATE --------------------------------------------------------------------------------------

    /**
     * Get String of Default Date format format
     *
     * @param date Date source
     * @return
     */
    public String getStringFromDate(Date date) {
        simpleDateFormat.applyPattern(DEFAULT_DATE_FORMAT);
        return simpleDateFormat.format(date);
    }

    public String getStringFromDate(Date date, String patternOutput) {
        simpleDateFormat.applyPattern(patternOutput);
        return simpleDateFormat.format(date);
    }

    public String getIsoStringFromDate(Date date) {
        simpleDateFormat.applyPattern(Iso_DATE_FORMAT);
        return simpleDateFormat.format(date);
    }

    public String getIsoStringFromStringDate(String dateString) {
        simpleDateFormat.applyPattern(DEFAULT_DATE_FORMAT);
        Date result = null;
        try {
            result = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            Timber.e(e);
            return null;
        }
        return getIsoStringFromDate(result);
    }

    /**
     * Get Date from "dd-MM-yyyy"
     *
     * @param text "dd-MM-yyyy"
     * @return Date
     */
    public static Date getDateFromString(String text) {
        simpleDateFormat.applyPattern(DEFAULT_DATE_FORMAT);
        try {
            return simpleDateFormat.parse(text);
        } catch (ParseException e) {
            Timber.e(e);
            return null;
        }
    }

    public Date getDateFromIsoString(String text) {
        simpleDateFormat.applyPattern(Iso_DATE_FORMAT);
        try {
            return simpleDateFormat.parse(text);
        } catch (ParseException e) {
            Timber.e(e);
            return null;
        }
    }

    public Date getDateFromString(String text, String patternSource) {
        simpleDateFormat.applyPattern(patternSource);
        try {
            return simpleDateFormat.parse(text);
        } catch (ParseException e) {
            Timber.e(e);
            return null;
        }
    }

    //region StringDate to StringDate

    /**
     * get "yyyy-MM-dd'T'HH:mm" to "dd-MM-yyyy"
     *
     * @param source string "yyyy-MM-dd'T'HH:mm"
     * @return string "dd-MM-yyyy"
     */
    public String getStringFromIsoDateString(String source) {
        simpleDateFormat.applyPattern(Iso_DATE_FORMAT);
        Date result = null;
        try {
            result = simpleDateFormat.parse(source);
            return getStringFromDate(result);
        } catch (ParseException e) {
            Timber.e(e);
            return null;
        }
    }

    public String getStringFromIsoDateString(String source, String outputPattern) {
        simpleDateFormat.applyPattern(Iso_DATE_FORMAT);
        Date result = null;
        try {
            result = simpleDateFormat.parse(source);
        } catch (ParseException e) {
            Timber.e(e);
            return null;
        }
        return getStringFromDate(result, outputPattern);
    }

    public String getStringFromDateString(String source, String patternSource, String outputPattern) {
        simpleDateFormat.applyPattern(patternSource);
        Date result = null;
        try {
            result = simpleDateFormat.parse(source);
        } catch (ParseException e) {
            Timber.e(e);
        }
        return getStringFromDate(result, outputPattern);
    }
    //endregion

    public String getDateFromToday() {
        Calendar c = Calendar.getInstance();
        return getIsoStringFromDate(c.getTime());
    }

    public String getDateFromTodayDiff(int day) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, day);
        return getIsoStringFromDate(c.getTime());
    }

}

package com.example.user.demovizuo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


/**
 * The CLASS <br>
 * COPRY FROM ICONFIG PROJECT <br>
 * last date : 10/11/2015
 *
 */

/**
 * @author <a href="mailto:thitranthanh@gmail.com">thitranthanh</a>
 * @date Apr 26, 2013
 * @copyright Dai Viet Controls & Instrumentation Company
 */
public class DateTime {
    private static final String TAG = DateTime.class.getName();
    public static final String TIME_ZONE_SERVER_CLOUD = "GMT+0";
    public static final String TIME_ZONE_DEFAULT = String.valueOf(Calendar.getInstance().getTimeZone().getID());

    public static final String TODAY = "TODAY";
    public static final String YESTERDAY = "YESTERDAY";
    public static final String LAST_3_DAYS = "LAST_3_DAYS";
    public static final String THIS_WEEK = "THIS_WEEK";
    public static final String LAST_WEEK = "LAST_WEEK";
    public static final String THIS_MONTH = "THIS_MONTH";
    public static final String LAST_MONTH = "LAST_MONTH";
    public static final String THIS_YEAR = "THIS_YEAR";

    private DateTime() {
    }

    private static class Loader {
        private static final DateTime INSTANCE = new DateTime();
    }

    public static DateTime getInstance() {
        return Loader.INSTANCE;
    }

    public String getDateTime(String format, String timezone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return simpleDateFormat.format(new Date());
    }

    public String getDateTime(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_DEFAULT));
        return simpleDateFormat.format(new Date());
    }

    public String getDateTime(long timestamp, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_DEFAULT));
        return simpleDateFormat.format(timestamp);
    }

    public String getTime(long timestamp, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_DEFAULT));
        return simpleDateFormat.format(timestamp);
    }

    public String getDate(long timestamp, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_DEFAULT));
        return simpleDateFormat.format(timestamp);
    }

    public String getEpochTimestamp(String format) {
        return getDateTime(System.currentTimeMillis(), format);
    }

    public long getTimestamp(String timestamp, String formatDateTime) {
        try {
            SimpleDateFormat sdf =
                    new SimpleDateFormat(formatDateTime);
            sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_DEFAULT));
            return sdf.parse(timestamp).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }
    
    public String convertDateToDate(String date, String oldFDate,
            String newFdate) {
        long time = getTimestamp(date, oldFDate);
        String ret = getDateTime(time, newFdate);
        return ret;
    }

    public Date convertStringToDate(String date, String format)
            throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_DEFAULT));
        try {
            return df.parse(date);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public int getDayNumberOfWeek(String dateTime, String format)
            throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date date = df.parse(dateTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK)) {
            return 7;
        }
        return (cal.get(Calendar.DAY_OF_WEEK) - 1);
    }
    
    public String convertDateToString(Date date, String outFormatDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outFormatDate);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_DEFAULT));
        return simpleDateFormat.format(date);
    }

    public Date getFirstDayOfWeek() {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return c.getTime();
    }

    public int getWeekOfYear() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public Date getStartDateOfMonth() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public Date getStartDateOfYear() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }
    
    public Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
    
    public Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }
    
    public Date subtractDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }
    
    public Date subtractMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -months);
        return cal.getTime();
    }
    
    public Date subtractSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, -seconds);
        return cal.getTime();
    }
    
    public Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    public Date getToday() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        return calendar.getTime();
    }

    public List<String> getAllDateByRange(String startDate, String endDate,
            String inFormatDate, String outFormatDate, 
            int stepUnit, int stepValue) throws ParseException {

        List<String> ret = new ArrayList<String>();

        SimpleDateFormat df = new SimpleDateFormat(inFormatDate);
        try {
            Date _startDate = df.parse(startDate);
            Date _endDate = df.parse(endDate);

            Calendar _calStartDate = Calendar.getInstance();
            Calendar _calEndDate = Calendar.getInstance();

            _calStartDate.setTime(_startDate);
            _calEndDate.setTime(_endDate);

            long n = _calEndDate.getTimeInMillis() - _calStartDate.getTimeInMillis();
            if (n > 0) {
                do {
                    ret.add(getDateTime(_calStartDate.getTimeInMillis(), outFormatDate));
                    _calStartDate.add(stepUnit, stepValue);
                } while (_calStartDate.getTimeInMillis() < _calEndDate
                        .getTimeInMillis());

                ret.add(getDateTime(_calEndDate.getTimeInMillis(), outFormatDate));
            } else if (n == 0) {
                ret.add(getDateTime(_calStartDate.getTimeInMillis(), outFormatDate));
            } else {
            }
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        return ret;
    }
    
    public int calculateNumberOfDaysBetween(Date startDate, Date endDate) {
	    if (startDate.after(endDate)) {
	        throw new IllegalArgumentException("End date should be grater or equals to start date");
	    }

	    long startDateTime = startDate.getTime();
	    long endDateTime = endDate.getTime();
	    long milPerDay = 1000*60*60*24; 

	    int numOfDays = (int) ((endDateTime - startDateTime) / milPerDay);

	    return ( numOfDays + 1);
	}
    //thanhuy.nguyen validate mail ADD START
    public Date addSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }
    
    public int compareDate(Date date1,Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int result = 0;
        if(cal1.compareTo(cal2)<0)
        {
            // return -1 if date1 is less than date2
            result = -1;
        }
        else if(cal1.compareTo(cal2)>0)
        {
            // return 1 if date1 is greater than date2
            result = 1;
        }
        else
        {
            // return 0 if date1 is equal to date2
            result = 0;
        }
        return result;
    }
    //thanhuy.nguyen validate mail ADD END
    
    // for iconfig start
    public long getTimestampByTimeZone(String timestamp,String formatDateTime, String timeZone) {
        try {
            SimpleDateFormat sdf =
                    new SimpleDateFormat(formatDateTime);
            sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
            return sdf.parse(timestamp).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }
    public long getTimestampByTimeZoneDevice(String timestamp,String formatDateTime) {
        try {
            SimpleDateFormat sdf =
                    new SimpleDateFormat(formatDateTime);
            sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_SERVER_CLOUD));
            return sdf.parse(timestamp).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }
    
    public String getDateByTimeZone(long timestamp, String format,String timeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return simpleDateFormat.format(timestamp);
    }
    
    public Date convertTimeToGMT(long timestamp) throws ParseException {
        return new Date(timestamp);
    }
    
    public long convertTime(long timestamp, String toTimeZone) {
        try {
            Date date = new Date(timestamp);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
            sdf.setTimeZone(TimeZone.getTimeZone(toTimeZone));
            String toTimeZoneVal = sdf.format(date);
//        System.out.println("timezone value:: " + toTimeZoneVal);
            sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_SERVER_CLOUD));
            return sdf.parse(toTimeZoneVal).getTime();
        } catch (ParseException e) {
            return timestamp;
        }

    }
    
    // minhnhut.pham new start
    public String convertDateToString(Date date, String outFormatDate, String timezone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outFormatDate);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return simpleDateFormat.format(date);
    }
    
    public Date convertStringToDate(String date, String inFormatDate, String timezone)
            throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(inFormatDate);
        df.setTimeZone(TimeZone.getTimeZone(timezone));
        try {
            return df.parse(date);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public String convertDateToDate(String date, String oldFDate,
            String newFdate, String timezone) {
        long time = getTimestamp(date, oldFDate, timezone);
        String ret = getDateTime(time, newFdate, timezone);
        return ret;
    }
    
    public long getTimestamp(String timestamp, String formatDateTime, String timezone) {
        try {
            SimpleDateFormat sdf =
                    new SimpleDateFormat(formatDateTime);
            sdf.setTimeZone(TimeZone.getTimeZone(timezone));
            return sdf.parse(timestamp).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }
    
    public String getDateTime(long timestamp, String format, String timezone) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
      simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
      return simpleDateFormat.format(timestamp);
    }

    public String getDateTime(Date date, String format, String timeZone) {
        String ret = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
            ret = simpleDateFormat.format(date);
        } catch(Exception ex) {
//            Logger.log(LoggingBehavior.DEVELOPER_DEBUG,
//                    Log.WARN,
//                    TAG,
//                    Logger.LOGGER_APPLICATION_WARNING,
//                    "getDateTime",
//                    "Exception",
//                    Utility.convertExceprionToString(ex));
        }
        return ret;
    }
    
    public String[] convertToLiveStamp(long timestamp, String userTimeZone, String liveStampFormat) {
        String[] ret = null;
        try {
            long userTimeZoneTimestamp = this.convertTime(timestamp, userTimeZone);
            String liveStampStr = this.getDateByTimeZone(userTimeZoneTimestamp,
                    liveStampFormat, TIME_ZONE_SERVER_CLOUD);
            String liveStamp = userTimeZoneTimestamp + "";
            String[] temp = {liveStampStr, liveStamp};
            ret = temp;
        } catch(Exception ex) {
//            Logger.log(LoggingBehavior.DEVELOPER_DEBUG,
//                    Log.WARN,
//                    TAG,
//                    Logger.LOGGER_APPLICATION_WARNING,
//                    "convertToLiveStamp",
//                    "Exception",
//                    Utility.convertExceprionToString(ex));
        }
        return ret;
    }
    // minhnhut.pham new end
    
    // for iconfig end
    public int getNumOfDayInMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public List<String> getDashboardTimeRange(String dateRange, String timeZone, String timeToGet) {
        List<String> ret = new ArrayList<String>();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String fullDateFormat = "yyyyMMddHHmmss";
            String dateFormat = "dd/MM/yyyy";
            String dateTimeFormat = dateFormat + " HH:mm:ss";
//            String fullDateFormat = dateTimeFormat + ":SSS";
            String startDateClient = this.getDateByTimeZone(this.convertTime(currentTimeMillis, timeZone),
                    dateFormat, TIME_ZONE_SERVER_CLOUD) + (" " + timeToGet + ":00");

            String currentClientDateTimeClient = this.getDateByTimeZone(this.convertTime(currentTimeMillis, timeZone),
                    dateTimeFormat, TIME_ZONE_SERVER_CLOUD);

            Date startDate = this.convertStringToDate(startDateClient,
                    dateTimeFormat, TIME_ZONE_SERVER_CLOUD);

            if(currentClientDateTimeClient.compareTo(startDateClient) < 0) {
                startDate = this.subtractDays(startDate, 1);
            }

            Date endDate = null;

            if(TODAY.equals(dateRange)) {
                endDate = this.addDays(startDate, 1);
                endDate = this.subtractSeconds(endDate, 1);
            } else if(YESTERDAY.equals(dateRange)) {
                endDate = startDate;
                endDate = this.subtractSeconds(endDate, 1);
                startDate = this.subtractDays(endDate, 1);
            } else if(LAST_3_DAYS.equals(dateRange)) {
                endDate = startDate;
                endDate = this.subtractSeconds(endDate, 1);
                startDate = this.subtractDays(endDate, 3);
            } else if(THIS_WEEK.equals(dateRange) || LAST_WEEK.equals(dateRange)) {
                Date currentDate = this.convertStringToDate(currentClientDateTimeClient,
                        dateTimeFormat, TIME_ZONE_SERVER_CLOUD);
                Calendar cal = Calendar.getInstance();
                cal.setFirstDayOfWeek(Calendar.MONDAY);
                cal.setTime(currentDate);
                int numberOfWeek = cal.get(Calendar.WEEK_OF_YEAR);
//                int year = cal.getWeekYear();
                int year = cal.get(Calendar.YEAR);

                cal.clear();
                cal.setFirstDayOfWeek(Calendar.MONDAY);
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.WEEK_OF_YEAR, numberOfWeek);

                String startDateClientWeekly = this.convertDateToString(cal.getTime(), "dd/MM/yyyy", TIME_ZONE_SERVER_CLOUD)
                        + " " + timeToGet + ":00";
                // minhnhut.pham modify start
//                if(currentClientDateTimeClient.compareTo(startDateClientWeekly) < 0) {
//                    numberOfWeek--;
//                }
                if(this.convertDateToDate(currentClientDateTimeClient, dateTimeFormat, fullDateFormat, TIME_ZONE_SERVER_CLOUD).compareTo(
                        this.convertDateToDate(startDateClientWeekly, dateTimeFormat, fullDateFormat, TIME_ZONE_SERVER_CLOUD)) < 0) {
                    numberOfWeek--;
                }
                // minhnhut.pham modify end
                if(LAST_WEEK.equals(dateRange)) {
                    numberOfWeek--;
                }
                ret.add(numberOfWeek + "");
                ret.add(year + "");
            } else if(THIS_MONTH.equals(dateRange) || LAST_MONTH.equals(dateRange)) {
                String startDateClientMonthly = "01" + "/" + this.getDateByTimeZone(this.convertTime(currentTimeMillis, timeZone),
                        "MM/yyyy", TIME_ZONE_SERVER_CLOUD) + (" " + timeToGet + ":00");

                Date startDateMonthly = this.convertStringToDate(startDateClientMonthly,
                        dateTimeFormat, TIME_ZONE_SERVER_CLOUD);
                if(currentClientDateTimeClient.compareTo(startDateClientMonthly) < 0) {
                    startDateMonthly = this.subtractDays(startDateMonthly, 1);
                }
                if(LAST_MONTH.equals(dateRange)) {
                    startDateMonthly = this.subtractMonths(startDateMonthly, 1);
                }
                String month = this.getDateTime(startDateMonthly, "MM/yyyy", TIME_ZONE_SERVER_CLOUD);
                ret.add(month);
            } else if(THIS_YEAR.equals(dateRange)) {
//                calendar.set(Calendar.DAY_OF_MONTH, 1);
//                calendar.set(Calendar.MONTH, 0);
//                startDate = calendar.getTime();
//                endDate = this.addMonths(startDate, 12);
            } else {
                int timeOffsetInSeconds = Integer.parseInt(dateRange) * 60;
                String endDateClientTemp = this.getDateByTimeZone(
                        this.convertTime(currentTimeMillis, timeZone),
                        dateTimeFormat, TIME_ZONE_SERVER_CLOUD);
                endDate = this.convertStringToDate(endDateClientTemp,
                        dateTimeFormat, TIME_ZONE_SERVER_CLOUD);
                startDate = this.subtractSeconds(endDate, timeOffsetInSeconds);
            }
            if(startDate != null && endDate != null) {
                String startDateRet = this.convertDateToString(startDate, dateTimeFormat, TIME_ZONE_SERVER_CLOUD);
                String endDateRet = this.convertDateToString(endDate, dateTimeFormat, TIME_ZONE_SERVER_CLOUD);
//                Long startTimestamp = startDate.getTime();
//                Long endTimestamp = this.subtractSeconds(endDate, 1).getTime() + 999;
                ret.add(startDateRet);
                ret.add(endDateRet);
            }
        } catch(Exception ex) {
//            Logger.log(LoggingBehavior.DEVELOPER_DEBUG,
//                    Log.WARN,
//                    TAG,
//                    Logger.LOGGER_APPLICATION_WARNING,
//                    "getDashboardTimeRange",
//                    "Exception",
//                    Utility.convertExceprionToString(ex));
        }
        return ret;
    }

    // thanhuy.nguyen vizou mobile app add start
    public String getTimer(String timezoneStr) {
        String currentTime = "00:00:00";
        TimeZone timeZone = TimeZone.getTimeZone(timezoneStr);
        Date today = new Date();

        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(today);
        currentTime = String.format("%02d:%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND));
        return currentTime;
    }

    // thanhuy.nguyen vizou mobile app add end
}

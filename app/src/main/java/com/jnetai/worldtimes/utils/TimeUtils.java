package com.jnetai.worldtimes.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utility methods for time calculations, mirroring the web app's JavaScript logic.
 */
public class TimeUtils {

    /**
     * Get current time in the given IANA timezone as a formatted string (locale en-US style).
     */
    public static String getTimeInZone(String timezoneId) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy, h:mm:ss a", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone(timezoneId));
        return sdf.format(new Date());
    }

    /**
     * Get the time difference in hours between local device time and the given timezone.
     */
    public static float getTimeDifferenceHours(String timezoneId) {
        TimeZone localTz = TimeZone.getDefault();
        TimeZone targetTz = TimeZone.getTimeZone(timezoneId);

        long now = System.currentTimeMillis();
        int localOffset = localTz.getOffset(now);
        int targetOffset = targetTz.getOffset(now);

        return (float) (targetOffset - localOffset) / (60 * 60 * 1000);
    }

    /**
     * Get the UTC offset for a timezone in hours (e.g., -5, 1, 8).
     */
    public static float getUtcOffsetHours(String timezoneId) {
        TimeZone tz = TimeZone.getTimeZone(timezoneId);
        int offset = tz.getOffset(System.currentTimeMillis());
        return (float) offset / (60 * 60 * 1000);
    }

    /**
     * Format time difference as a string, matching the web app's style.
     */
    public static String formatTimeDifference(float hours) {
        if (hours == 0) return "0 hours";
        return (hours > 0 ? "+" : "") + String.format(Locale.US, "%.0f", hours) + " hours";
    }

    /**
     * Get current time as HH:MM:SS string.
     */
    public static String getCurrentTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.US);
        return sdf.format(new Date());
    }

    /**
     * Get current date string with day name.
     */
    public static String getCurrentDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.US);
        return sdf.format(new Date());
    }

    /**
     * Get the current UTC hour (0-23) plus minutes as decimal for UTC blocks.
     */
    public static float getCurrentUtcHour() {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        java.util.Calendar cal = java.util.Calendar.getInstance(utc);
        return cal.get(java.util.Calendar.HOUR_OF_DAY) + cal.get(java.util.Calendar.MINUTE) / 60f;
    }
}

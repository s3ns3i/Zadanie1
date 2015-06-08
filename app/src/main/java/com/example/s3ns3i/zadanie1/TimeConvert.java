package com.example.s3ns3i.zadanie1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by s3ns3i on 2015-04-15.
 */
public class TimeConvert {

    /**
     *
     * @param hours - Integer number of hours
     * @param minutes - Integer number of minutes
     * @param seconds - Integer number of seconds
     * @return - returns time in format "hh:mm:ss"
     */
    static public String convert(Integer hours, Integer minutes, Integer seconds){
        String time = "";
        if(hours != 0)
            time = String.valueOf(hours) + ":";
        if(minutes < 10)
            time += "0" + String.valueOf(minutes) + ":";
        else
            time += String.valueOf(minutes) + ":";
        if(seconds < 10)
            time += "0" + String.valueOf(seconds);
        else
            time += String.valueOf(seconds);
        return time;
//        return String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds);
    }

    /**
     *
     * @param hours - Integer number of hours
     * @param minutes - Integer number of minutes
     * @param seconds - Integer number of seconds
     * @param milliseconds - Integer number of milliseconds
     * @return - returns time in format "hh:mm:ss"
     */
    static public String convert(Integer hours, Integer minutes, Integer seconds, Integer milliseconds){
        String time = "";
        if(hours != 0)
            time = String.valueOf(hours) + ":";
        if(minutes < 10)
            time += "0" + String.valueOf(minutes) + ":";
        else
            time += String.valueOf(minutes) + ":";
        if(seconds < 10)
            time += "0" + String.valueOf(seconds);
        else
            time += String.valueOf(seconds) + ":";
        time += String.valueOf(milliseconds);
        return time;
//        return String.valueOf(hours) + ":" + String.valueOf(minutes)
//                + ":" + String.valueOf(seconds) + ":" + String.valueOf(milliseconds);
    }

    /**
     *
     * @param seconds - Integer number of seconds
     * @return - returns time in format "hh:mm:ss"
     */
    static public String convertFromSeconds(Integer seconds){
        Integer hours = 0;
        Integer minutes = 0;
        Integer seconds_ = seconds;
        // If there are more seconds than 59...
        if(seconds_ >= 60){
            // Divide it and get number of minutes
            minutes = seconds_ / 60;
            // Get number of remaining seconds.
            seconds_ = seconds_ - minutes * 60;
            // If there are more minutes than 59...
            if(minutes >= 60){
                // Divide it and get number of hours
                hours = minutes / 60;
                // Get number of remaining minutes.
                minutes = minutes - hours * 60;
            }
        }
        // Return converted time.
        String time = String.valueOf(hours) + ":";
        if(minutes < 10)
            time += "0" + String.valueOf(minutes) + ":";
        else
            time += String.valueOf(minutes) + ":";
        if(seconds < 10)
            time += "0" + String.valueOf(seconds_);
        else
            time += String.valueOf(seconds_);
        return time;
//        return String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds_);
    }

    /**
     *
     * @param milliseconds - Integer number of milliseconds
     * @return - returns time in format "hh:mm:ss:mm"
     */
    static public String convertFromMilliseconds(Integer milliseconds){
        Integer hours = 0;
        Integer minutes = 0;
        Integer seconds = 0;
        Integer milliseconds_ = milliseconds;
        // If there are more milliseconds than 99...
        if(milliseconds_ >= 1000) {
            // Divide it and get number of seconds
            seconds = milliseconds_ / 1000;
            // Get number of remaining milliseconds.
            milliseconds_ = milliseconds_ - seconds * 1000;
            // If there are more seconds than 59...
            if (seconds >= 60) {
                // Divide it and get number of minutes
                minutes = seconds / 60;
                // Get number of remaining seconds.
                seconds = seconds - minutes * 60;
                // If there are more minutes than 59...
                if (minutes >= 60) {
                    // Divide it and get number of hours
                    hours = minutes / 60;
                    // Get number of remaining minutes.
                    minutes = minutes - hours * 60;
                }
            }
        }
        // Return converted time.
        String time = "";
        if(hours != 0)
            time = String.valueOf(hours) + ":";
        if(minutes < 10)
            time += "0" + String.valueOf(minutes) + ":";
        else
            time += String.valueOf(minutes) + ":";
        if(seconds < 10)
            time += "0" + String.valueOf(seconds);
        else
            time += String.valueOf(seconds);
        return time;
//        if(hours == 0)
//            return String.valueOf(minutes) + ":" + String.valueOf(seconds);
//        else
//            return String.valueOf(hours) + ":" + String.valueOf(minutes)
//                + ":" + String.valueOf(seconds);
    }

    /**
     *
     * @param time - String value of time in format "hh:mm:ss"
     * @return - String value of seconds
     */
    static public String getSecondsStr(String time){
        Pattern p = Pattern.compile("(\\d*):(\\d*):(\\d*)");
        Matcher m = p.matcher(time);
        while (m.find()) { // Find each match in turn.
            Integer hours = Integer.valueOf(m.group(1)); // Access a submatch group.
            Integer minutes = Integer.valueOf(m.group(2));
            Integer seconds = Integer.valueOf(m.group(3));
            return String.valueOf(hours * 3600 + minutes * 60 + seconds);
        }
        return "0";
    }

    /**
     *
     * @param time - String value of time in format "hh:mm:ss:mm"
     * @return - String value of seconds
     */
    static public String getMillisecondsStr(String time){
        Pattern p = Pattern.compile("(\\d*):(\\d*):(\\d*):(\\d*)");
        Matcher m = p.matcher(time);
        while (m.find()) { // Find each match in turn.
            Integer hours = Integer.valueOf(m.group(1)); // Access a submatch group.
            Integer minutes = Integer.valueOf(m.group(2));
            Integer seconds = Integer.valueOf(m.group(3));
            Integer milliseconds = Integer.valueOf(m.group(4));
            return String.valueOf(hours * 360000 + minutes * 6000 + seconds * 100 + milliseconds);
        }
        return "0";
    }

    /**
     *
     * @param time - String value of time in format "hh:mm:ss"
     * @return - Integer value of seconds.
     */
    static public Integer getSecondsInt(String time){
        Pattern p = Pattern.compile("(\\d*):(\\d*):(\\d*)");
        Matcher m = p.matcher(time);
        while (m.find()) { // Find each match in turn.
            Integer hours = Integer.valueOf(m.group(1)); // Access a submatch group.
            Integer minutes = Integer.valueOf(m.group(2));
            Integer seconds = Integer.valueOf(m.group(3));
            return hours * 3600 + minutes * 60 + seconds;
        }
        return 0;
    }

    /**
     *
     * @param time - String value of time in format "hh:mm:ss:mm"
     * @return - String value of seconds
     */
    static public Integer getMillisecondsInt(String time){
        Pattern p = Pattern.compile("(\\d*):(\\d*):(\\d*):(\\d*)");
        Matcher m = p.matcher(time);
        while (m.find()) { // Find each match in turn.
            Integer hours = Integer.valueOf(m.group(1)); // Access a submatch group.
            Integer minutes = Integer.valueOf(m.group(2));
            Integer seconds = Integer.valueOf(m.group(3));
            Integer milliseconds = Integer.valueOf(m.group(4));
            return hours * 360000 + minutes * 6000 + seconds * 100 + milliseconds;
        }
        return 0;
    }
}

package com.easycolor.Matchers;

/**
 * Created by Joshua on 18/08/2014.
 */
public class RainmeterMatcher extends Matcher {

    public static final String TYPE_LABEL = "Rainmeter";
    public static final String EXPECTED_EXTENSION = ".ini";

    public boolean matchTypeLabel(String in){
        return TYPE_LABEL.compareTo(in) == 0;
    }

    public boolean matchesExpectedFileType(String in){
        boolean out = false;
        int extStart = in.lastIndexOf('.');
        if(extStart >= 0){
            String ext = in.substring(extStart);
            out = EXPECTED_EXTENSION.compareTo(ext) == 0;
        }
        return out;
    }

}

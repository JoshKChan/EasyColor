package com.easycolor.Matchers.HeaderMatchers;

import com.easycolor.Writer.RainmeterWriter;
import com.easycolor.Writer.Writer;

/**
 * Created by Joshua on 23/08/2014.
 */
public class RainmeterHeaderMatcher extends HeaderMatcher{

    public static final String TYPE_LABEL = "Rainmeter";
    public static final String EXPECTED_EXTENSION = "ini";

    public Writer suggestWriter(){
        return new RainmeterWriter();
    }

    public boolean matchesLabel(String in){
        return TYPE_LABEL.compareTo(in) == 0;
    }

    public boolean matchesExtension(String in){
        return EXPECTED_EXTENSION.compareTo(in) == 0;
    }

    public String toString(){
        return "RainmeterMatcher";
    }

}

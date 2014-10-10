package com.easycolor.Matchers.HeaderMatchers;

import com.easycolor.Writer.BBWriter;
import com.easycolor.Writer.Writer;

/**
 * Created by Joshua on 23/08/2014.
 */
public class BBHeaderMatcher extends HeaderMatcher {

    public static final String TYPE_LABEL = "bbLean";

    public Writer suggestWriter(){
        return new BBWriter();
    }

    public boolean matchesLabel(String in){
        return TYPE_LABEL.compareTo(in) == 0;
    }

    public boolean matchesExtension(String in){
        return true;
    }

    public String toString(){
        return "BBHeaderMatcher";
    }

}

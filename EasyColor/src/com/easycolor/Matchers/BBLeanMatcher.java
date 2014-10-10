package com.easycolor.Matchers;

/**
 * Created by Joshua on 18/08/2014.
 */
public class BBLeanMatcher extends Matcher {

    public static final String TYPE_LABEL = "bbLean";

    public boolean matchTypeLabel(String in){
        return TYPE_LABEL.compareTo(in) == 0;
    }

    public boolean matchesExpectedFileType(String in){
        boolean out = false;
        int extStart = in.lastIndexOf('.');
        if(extStart == -1) { //bbLean styles have no file extension, so we have to assume any file without one is possibly valid.
            out = true;
        }
        return out;
    }

}

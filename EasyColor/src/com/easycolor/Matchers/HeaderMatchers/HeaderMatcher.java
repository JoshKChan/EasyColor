package com.easycolor.Matchers.HeaderMatchers;

import com.easycolor.Writer.Writer;

import java.util.regex.Pattern;

/**
 * Created by Joshua on 22/08/2014.
 * HeaderMatcher is used to identify section headers on scheme files.
 */
public abstract class HeaderMatcher{

    private static final String regex = "\\[(.*)\\|(.*[\\\\\\.](.*))\\]";

    abstract boolean matchesLabel(String in);
    abstract boolean matchesExtension(String in);
    public abstract Writer suggestWriter();

    public boolean matches(String in){
        boolean output = false;
        Pattern p = Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(in);
        if(m.matches()){
            if(matchesLabel(m.group(1)) && matchesExtension(m.group(3))){
                output = true;
            }
        }
        return output;
    }

    public String getPath(String in){
        String out = null;
        Pattern p = Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(in);
        if(m.matches()){
            out = m.group(2);
        }
        return out;
    }

}

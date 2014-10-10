package com.easycolor.Writer;

import com.easycolor.ColorLabel;
import com.easycolor.Matchers.Matcher;
import com.easycolor.Matchers.RainmeterMatcher;

import java.util.regex.Pattern;

/**
 * Created by Joshua on 23/08/2014.
 */
public class RainmeterWriter extends Writer{

    private static final String regex = "(.*)(\\s*\\=\\s*)(?:\\d{1,3}\\,*\\s*){3}(?:\\d{1,3})?";

    public Matcher getMatcher(){
        return new RainmeterMatcher();
    }

    public String formatColorLabel(ColorLabel color){
        String out = "";
        out = out.concat(color.getRed()).concat(",");
        out = out.concat(color.getGreen()).concat(",");
        out = out.concat(color.getBlue());
        String alpha = color.getAlpha();
        if(alpha != null){
            out = out.concat(",".concat(alpha));
        }
        return out;
    }

    public java.util.regex.Matcher getRegexMatcher(String in){
        Pattern p = Pattern.compile(regex);
        return p.matcher(in);
    }

}

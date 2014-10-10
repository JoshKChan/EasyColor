package com.easycolor.Writer;

import com.easycolor.ColorLabel;
import com.easycolor.Matchers.BBLeanMatcher;
import com.easycolor.Matchers.Matcher;

import java.util.regex.Pattern;

/**
 * Created by Joshua on 23/08/2014.
 */
public class BBWriter extends Writer {

    private static final String regex = "(.*)(\\s*\\:\\s*)(\\#[\\w\\d]{6})";

    public Matcher getMatcher(){
        return new BBLeanMatcher();
    }

    public String formatColorLabel(ColorLabel color){
        return "#".concat(color.getHexRed()).concat(color.getHexGreen()).concat(color.getHexBlue());
    }

    public java.util.regex.Matcher getRegexMatcher(String in){
        Pattern p = Pattern.compile(regex);
        return p.matcher(in);
    }

}

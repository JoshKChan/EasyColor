package com.easycolor.ColorScanners;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joshua on 19/08/2014.
 */
public class BBColorScanner extends ColorScanner {

    private static final String regex = "([^\\s]*):\\s*(#[0-9a-fA-F]{6})";

    //TODO Candidate for refactoring. Exact same implementation in RainmeterScanner. Could move up...
    //to ColorScanner and have each subclass supply a regex string instead.
    /*
        Uses the class-defined regex to try and pull a BlackBox formatted label from a String.
        The first group returns the label while the second group can be used to pull the hex value.
    */
    String extractLabel(String input){
        String output = "";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if(m.matches()){
            String label = m.group(1);
            if(label!=null){
                output = output+label;
            }
        }
        return output;
    }

    //Conveniently, bbLean uses the same hex RGB as we do, so no editing is needed.
    String extractAndFormatColor(String input){
        String output = "";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if(m.matches()){
            String value = m.group(2);
            if(value!=null){
                output = output+value;
            }
        }
        return output;
    }

    public String getFileType(){
        return "bbLean";
    }

}

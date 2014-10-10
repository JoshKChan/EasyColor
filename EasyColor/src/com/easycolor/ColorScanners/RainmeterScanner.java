package com.easycolor.ColorScanners;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joshua on 19/08/2014.
 */
public class RainmeterScanner extends ColorScanner{

    public static final String regex = "(.*)=([\\d]{1,3})[\\,\\s]+([\\d]{1,3})[\\,\\s]+([\\d]{1,3})[\\,\\s]*([\\d]{1,3})?";

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

    /*
         Notice that having a regex capable of isolating the colours makes it easy to reformat them.
         Note that we need to buffer any 0's produced by toHexString.
    */
    String extractAndFormatColor(String input){
        String output = "";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if(m.matches()){
            String red = Integer.toHexString(Integer.parseInt(m.group(2)));
            if(red.compareTo("0")==0)
                red = "00";
            String green = Integer.toHexString(Integer.parseInt(m.group(3)));
            if(green.compareTo("0")==0)
                green = "00";
            String blue = Integer.toHexString(Integer.parseInt(m.group(4)));
            if(blue.compareTo("0")==0)
                blue = "00";
            String alpha = m.group(5);
            if(alpha != null && alpha.length() > 0){
                alpha = Integer.toHexString(Integer.parseInt(alpha));
                if(alpha.compareTo("0")==0){
                    alpha = "00";
                }
            }else{
                alpha = "";
            }
            output = "#".concat(red).concat(green).concat(blue).concat(alpha);
        }
        return output;
    }

    public String getFileType(){
        return "Rainmeter";
    }

}
package com.easycolor;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joshua on 21/08/2014.
 *
 * Example header
 * [COLOR_GREEN=#123456]
 *
 *  Regex to return name and colour
 *      \[(.*)=(#[abcdefABCDEF\d]{6})\]
 *  Regex to return only colour
 *      \[.*=(#[abcdefABCDEF\d]{6})\]
 */
public class ColorLabel {

    //TODO Refactor - Could only store the decimal values for colors and return Hex colors programmatically

    private static final String regex = "([^\\s]*)\\s*=\\s*(#([abcdefABCDEF\\d]{2})([abcdefABCDEF\\d]{2})([abcdefABCDEF\\d]{2})([abcdefABCDEF\\d]{2})?)";
    private static final String regex_internal = "([^\\s=]*)\\s*=\\s*([^\\s=]*)"; //For reference

    private String label;
    private String red;
    private String green;
    private String blue;
    private String alpha;
    private String hexRed;
    private String hexGreen;
    private String hexBlue;
    private String hexAlpha;

    public static ColorLabel newColorLabel(String in){
        ColorLabel out = null;
        if(isValid(in)){
            out = new ColorLabel(in);
        }
        return out;
    }

    //First attempts to create color label normally, if not able to
    //then attempts to do so by reference
    public static ColorLabel newColorLabelByRef(String in,ArrayList<ColorLabel> colorList){
        ColorLabel out = null;
        if(isValid(in)){
            out = new ColorLabel(in);
        } else if(isValidInternal(in) && colorList != null){
            Pattern p = Pattern.compile(regex_internal);
            Matcher m = p.matcher(in);
            if(m.matches()){
                String label = m.group(1);
                String value = m.group(2);
                for(ColorLabel color: colorList){
                    int comparisonResult = color.getLabel().compareTo(value);
                    if(comparisonResult==0){
                        out = new ColorLabel(label,color);
                    }
                }
            }
        }
        return out;
    }

    private ColorLabel(String in) throws RuntimeException{
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(in);
        if(m.matches()){
            label = m.group(1);
            hexRed = m.group(3);
            hexGreen = m.group(4);
            hexBlue = m.group(5);
            hexAlpha = m.group(6);
            red = Integer.decode("#".concat(hexRed)).toString();
            green = Integer.decode("#".concat(hexGreen)).toString();
            blue = Integer.decode("#".concat(hexBlue)).toString();
            if(hexAlpha != null && hexAlpha.length() > 0){
                alpha = Integer.decode("#".concat(hexAlpha)).toString();
            }
        }else{
            throw new RuntimeException("ColorLabel constructor called on non-matching string");
        }
    }

    private ColorLabel(String label,ColorLabel source){
        this.label = label;
        red = source.getRed();
        green = source.getGreen();
        blue = source.getBlue();
        alpha = source.getAlpha();
        hexRed = source.getHexRed();
        hexGreen = source.getHexGreen();
        hexBlue = source.getHexBlue();
        hexAlpha = source.getHexAlpha();

    }


    public static boolean isValid(String in){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(in);
        return m.matches();
    }

    public static boolean isValidInternal(String in){
        Pattern p = Pattern.compile(regex_internal);
        Matcher m = p.matcher(in);
        return m.matches();
    }

    public String getLabel(){return label;}
    public String getRed(){return red;}
    public String getGreen(){return green;}
    public String getBlue(){return blue;}
    public String getAlpha(){return alpha;}
    public String getHexRed(){return hexRed;}
    public String getHexGreen(){return hexGreen;}
    public String getHexBlue(){return hexBlue;}
    public String getHexAlpha(){return hexAlpha;}

    @Override
    public String toString(){
        String alphaHex = hexAlpha;
        if(alphaHex == null){
            alphaHex = "";
        }
        return label.concat(" = #").concat(hexRed).concat(hexGreen).concat(hexBlue).concat(alphaHex);
    }

}

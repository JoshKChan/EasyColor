package com.easycolor.Reader;

import com.easycolor.ColorScanners.RainmeterScanner;
import com.easycolor.Matchers.RainmeterMatcher;

/**
 * Created by Joshua on 19/08/2014.
 */
public class RainmeterReader extends Reader{

    private static final RainmeterMatcher matcher = new RainmeterMatcher();
    private static final RainmeterScanner scanner = new RainmeterScanner();

    public boolean matches(String in){
        return matcher.matches(in);
    }

    public String readFile(String inputLine){
        String output = "";
        if(matcher.matches(inputLine)) {
            output = scanner.readFile(matcher.getPath(inputLine));
        }
        return output;
    }

    public String readFileConservative(String inputLine){
        String output = "";
        if(matcher.matches(inputLine)) {
            output = scanner.readFileConserveValues(matcher.getPath(inputLine));
        }
        return output;
    }

}

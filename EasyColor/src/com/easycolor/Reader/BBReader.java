package com.easycolor.Reader;

import com.easycolor.ColorScanners.BBColorScanner;
import com.easycolor.Matchers.BBLeanMatcher;

/**
 * Created by Joshua on 19/08/2014.
 */
public class BBReader extends Reader {

    private static final BBLeanMatcher matcher = new BBLeanMatcher();
    private static final BBColorScanner scanner = new BBColorScanner();

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

package com.easycolor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joshua on 23/08/2014.
 *  InternalMatcher is not truly a matcher; it does not share all the required functions.
 *
 *  The extent to which it shares its name is that is has similar usage.
 */
public class InternalMatcher {

    private static final String regex = "([^\\s=]*)\\s*=\\s*([^\\s=]*)";

    public static boolean matches(String in){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(in);
        return m.matches();
    }

}

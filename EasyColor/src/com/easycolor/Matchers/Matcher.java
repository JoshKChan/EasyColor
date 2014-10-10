package com.easycolor.Matchers;

/**
 * Created by Joshua on 18/08/2014.
 *
 */
public abstract class Matcher {


    //Returns whether the input string matches the desired TypeLabel
    abstract boolean matchTypeLabel(String in);

    //Returns whether the input string ends in the desired file extension
    public abstract boolean matchesExpectedFileType(String in);

    /*
        Declared in order to standardize the separation token, since polymorphism
        will update all subclasses
     */
    public String[] tokenize(String in){
        return in.split("=");
    }

    public String getPath(String in){
        String out = null;
        if(matches(in)) {
            String[] tokens = tokenize(in);
            out = tokens[1];
        }
        return out;
    }

    /*
        Returns whether or not the input string represents a
        file of a particular type and a path to that file.

        TODO - Watch - Moved this up from identical implementations in subclasses.
        Previously both BBLeanMatcher and Rainmeter Matcher has the exact same code.
        Seemed better to move it into their parent, Matcher.
     */
    public boolean matches(String in){
        boolean out = false;
        String[] tokens = tokenize(in);
        if(tokens != null && tokens.length >= 2){
            if(matchTypeLabel(tokens[0]) && matchesExpectedFileType(tokens[1])){
                out = true;
            }
        }
        return out;
    }

}

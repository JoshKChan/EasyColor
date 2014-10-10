package com.easycolor.Reader;

/**
 * Created by Joshua on 19/08/2014.
 *
 * Readers bundle together ColorMatchers and ColorScanners
 */
public abstract class Reader{

    abstract public boolean matches(String in);

    /*
        TODO - Refactor - Identical implementations in subclasses.
        The only difference is that they reference different local variables.
        Could instead move this method up to Reader then make subclasses provide methods
        to produce the appropriate variables. Keeping the two separate for now.
        If ever a Reader, ColorMatcher, and ColorScanner is written to parse HTML, that might require a
        different implementation than usual.
     */
    abstract public String readFile(String filePath);

    abstract public String readFileConservative(String filePath);

}

package com.easycolor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by Joshua on 18/08/2014.
 *
 * Subclass of BufferedReader tooled specifically for parsing internal files.
 * That is, it will ignore lines starting off with the indicated SKIP_PREFIX
 * which ought to be standard through all internal files.
 */
public class InternalReader extends BufferedReader{

    public static final String SKIP_PREFIX = ";";

    public InternalReader(Reader in){
        super(in);
    }

    public InternalReader(Reader in, int sz){
        super(in,sz);
    }

    @Override
    public String readLine(){
        String out = null;
        try{
            String line = super.readLine();
            boolean skip = true;
            if(line != null) {
                //In case for some reason SKIP_PREFIX is made to be multiple characters.
                for (int i = 0; i < SKIP_PREFIX.length() && i < line.length(); i++) {
                    if (line.charAt(i) != SKIP_PREFIX.charAt(i)) {
                        skip = false;
                    }
                }
                if (skip) {
                    out = "";
                } else {
                    out = line;
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return out;
    }
}

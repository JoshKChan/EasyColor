package com.easycolor.ColorScanners;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Joshua on 19/08/2014.
 *
 * Scanners prepare an empty scheme section of the corresponding filetype.
 * Each should produce a header formatted like
 *          [FILE_TYPE|FILE_PATH]
 *          [bbLean|C:\bblean\whatever]
 * That header should then be followed by a line-separated labels with an '=' sign and a blank
 * field after each one. Blank fields should be ignored while processing in Scheme.
 */
public abstract class ColorScanner {

    abstract String extractLabel(String in);
    abstract String extractAndFormatColor(String in);
    abstract String getFileType(); //2.0

    public String getFileName(String filePath){
        String out = null;
        int start = filePath.lastIndexOf("\\");
        if(start >= 0){
            out = filePath.substring(start+1);
        }
        return out;
    }

    public String readFile(String filePath){
        String output = "";
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(filePath));
            if(reader != null){
                output = "["+getFileType()+"|"+filePath+"]\n"; //2.0 edit
                String line = null;
                while( (line = reader.readLine()) !=null){
                    String extract = extractLabel(line);
                    if(extract.length() > 0){
                        output = output + extract+"= \n";
                    }
                }
            }
        }catch(IOException e){
            System.out.println(e);
        }
        if(output.length() == 0){
            output = null;
        }
        return output;
    }

    public String readFileConserveValues(String filePath){
        String output = "";
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(filePath));
            if(reader != null){
                output = "["+getFileType()+"|"+filePath+"]\n"; //2.0 edit
                String line = null;
                while( (line = reader.readLine()) !=null){
                    String extract = extractLabel(line);
                    String color = extractAndFormatColor(line);
                    if(extract.length() > 0){
                        output = output + extract+"="+color+"\n";
                    }
                }
            }
        }catch(IOException e){
            System.out.println(e);
        }
        if(output.length() == 0){
            output = null;
        }
        return output;
    }

}

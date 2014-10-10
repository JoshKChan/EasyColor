package com.easycolor.Writer;

import com.easycolor.ColorLabel;
import com.easycolor.IO;
import com.easycolor.Matchers.Matcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Joshua on 22/08/2014.
 */
public abstract class Writer {

    abstract Matcher getMatcher();
    abstract String formatColorLabel(ColorLabel color);

    //get a regex to split the line such that group(1) is always the label and group(2) is the space
    //in between the label and the colour-value.
    // labelName = 213,21,5
    //      group(1) = "labelName"
    //      group(2) = " = "
    abstract java.util.regex.Matcher getRegexMatcher(String in);

    //todo refactor
    public void applyColorList(ArrayList<ColorLabel> colorList, String path){
        if(getMatcher().matchesExpectedFileType(path)){
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
                String line;
                String output = "";
                while( (line = bufferedReader.readLine()) != null){
                    java.util.regex.Matcher m = getRegexMatcher(line);
                    if(m.matches()){
                        String label = m.group(1);
                        String assignmentToken = m.group(2);
                        String colorValue = null;
                        for(ColorLabel colorLabel: colorList){
                            if(label.compareTo(colorLabel.getLabel())==0){
                                colorValue = formatColorLabel(colorLabel);
                                //todo break;?
                            }
                        }
                        if(colorValue != null){
                            output = output.concat(label).concat(assignmentToken).concat(colorValue).concat("\n");
                        }else{
                            output = output.concat(line).concat("\n");
                        }
                    }else{
                        output = output.concat(line).concat("\n");
                    }
                }//while
            //Main.println(" === PRODUCTION ===");
            //Main.println(output);
            //Main.println("==== /PRODUCTION ====");
            //Todo ~ Toggle line to enable/disable actual file editing
            IO.backup(path);
            IO.writeToFile(output,path);
            }catch(IOException e){
                //do nothing
            }
        }
    }

}

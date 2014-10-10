package com.easycolor;

import com.easycolor.Matchers.HeaderMatchers.BBHeaderMatcher;
import com.easycolor.Matchers.HeaderMatchers.HeaderMatcher;
import com.easycolor.Matchers.HeaderMatchers.RainmeterHeaderMatcher;
import com.easycolor.Reader.BBReader;
import com.easycolor.Reader.RainmeterReader;
import com.easycolor.Reader.Reader;

import java.io.*;
import java.util.ArrayList;


public class Main {

    /*
            Todo ~ Glitch ~ Local bbLean files
                bbHeaderMatcher does not match immediately local files

            Todo ~  In Progress, Ongoing ~Feature ~ Text commands
            Todo ~ Feature ~ Support transparency
                Keep an eye on this.
                Seems to be working. Transparency support has had changes made in
                in ColorLabel most prominently. The regex now has an optional alpha field which
                is ALWAYS attempted to be pulled, and if null is stored as such.

                However this should not be a problem, since the alpha should only ever be tried to pull
                on Writers that actually use it. Even then, those Writers usually also support
                colours without transparency, so they should have a check to see if alpha is null or not.
                See RainmeterWriter for example.

                Note that the Writer abstract itself did not have to be changed since it assumes that its
                subclasses can properly formatColorLabels. The regex match is not affected since
                that is only concerned with identifying relevant input lines, not formatting the output.
     */

    private static String SCHEME_PATH = "new_scheme.sch";
    private static String PATH_FILE = "path.txt";

    private static String SCHEME_DEFAULT_TEXT = ";Welcome to EasyColor!\n" +
            ";Lines beginning with a semicolon ';' are ignored\n" +
            ";Supported filetypes: \n\tbbLean\n;\tRainmeter\n" +
            ";An example of a path:\n;\tRainmeter=C:\\Users\\You\\Documents\\Rainmeter\\Skins\\mySkin\\widget.ini";

    public static void main(String[] args) {
        run();
    }

    public static void run(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        System.out.println("EasyColor by Joshua Chan 2014\n" +
                "====================================\n" +
                "Commands\n" +
                "    exit - Exit the program\n" +
                "    read - Reads the files specified in \"path.txt\" to make a scheme file.\n" +
                "           If there is no \"path.txt\" file, an example will be generated.\n" +
                "    read c - Same as read, but keeps the original colors in the scheme file.\n" +
                "    apply   - Uses \"scheme.schm\" to apply colors to the appropriate files.\n" +
                "              Automatically makes backups of the originals before editing.\n");
        try {
            while(!exit) {

                String input = null;
                input = br.readLine();
                if(input == null){
                    System.out.println("Null line. Ending program.");
                    exit = true;
                }else if(input.compareTo("read")==0){
                    File f = new File(PATH_FILE);
                    if(f.exists()){
                        System.out.println("Creating scheme...");
                        generateScheme();
                    }else{
                        IO.writeToFile(SCHEME_DEFAULT_TEXT,PATH_FILE);
                        System.out.println("No path file found. Created template file.");
                    }
                }else if(input.compareTo("read c")==0){
                    File f = new File(PATH_FILE);
                    if(f.exists()){
                        System.out.println("Creating scheme while conserving colors...");
                        generateConservativeScheme();
                    }else{
                        IO.writeToFile(SCHEME_DEFAULT_TEXT,PATH_FILE);
                        System.out.println("No path file found. Created template file: path.txt");
                    }
                }else if(input.compareTo("exit")==0){
                    exit = true;
                }else if(input.compareTo("apply")==0){
                    System.out.println("Applying scheme...");
                    applyScheme();
                }else{
                    System.out.println("Command not recognized");
                }
            }
            br.close();
        } catch (IOException e) {

        }

    }

    /*
        TODO ~ Consider adding print-outs here to allow user to know when specific headers are applied.
     */
    public static void applyScheme(){
        try {
            ArrayList<HeaderMatcher> headerMatchers = new ArrayList<HeaderMatcher>();
                headerMatchers.add(new BBHeaderMatcher());
                headerMatchers.add(new RainmeterHeaderMatcher());

            InternalReader reader = new InternalReader(new FileReader(SCHEME_PATH));
            String path = null;
            String line;
            com.easycolor.Writer.Writer writer = null;
            ArrayList<ColorLabel> colors = new ArrayList<ColorLabel>();
            ArrayList<ColorLabel> writerColors = new ArrayList<ColorLabel>();
                while((line = reader.readLine()) !=null){
                for(HeaderMatcher hMatcher : headerMatchers){
                    if(hMatcher.matches(line)){
                        if(writer != null && path != null) {
                            writer.applyColorList(writerColors, path);
                        }
                        //Whenever we encounter a header we always reset the writer, path, and writerColors
                        writer = hMatcher.suggestWriter();
                        path = hMatcher.getPath(line);
                        writerColors = new ArrayList<ColorLabel>();
                    }
                }
                if(writer == null){
                    //Always try and find new internal colours first
                    ColorLabel colorLabel = ColorLabel.newColorLabel(line);
                    if(colorLabel != null){
                        colors.add(colorLabel);
                    }
                }else{
                    if(InternalMatcher.matches(line)){
                        ColorLabel color = ColorLabel.newColorLabelByRef(line,colors);
                        if(color != null) {
                            writerColors.add(color);
                        }
                    }
                }
            }//while
            //For EOF
            if(writer != null && path != null) {
                writer.applyColorList(writerColors, path);
            }
            reader.close();
            System.out.println("Theme application complete");
        }catch (IOException e){
            if(e instanceof FileNotFoundException){
                System.out.println("scheme.sch not found. Could not apply theme.");
            }
        }
    }

    public static void generateScheme(){
        Reader readerArray[] = {
                new BBReader(),
                new RainmeterReader()
        };
        String output = "";

        try{
            InternalReader iReader = new InternalReader(new FileReader(PATH_FILE));
            String s;
            while((s = iReader.readLine())!=null){
                if(s.length() > 0) {
                    for(int i=0;i<readerArray.length;i++){
                        output = output.concat(readerArray[i].readFile(s));
                    }
                }
            }
            iReader.close();
            System.out.println("Scheme created");
        }catch (Exception e){
            System.out.println(e.toString());
            System.out.println("Error creating scheme");
        }
        //println(output);
        IO.writeToFile(output,SCHEME_PATH);
    }//generateScheme

    public static void generateConservativeScheme(){
        Reader readerArray[] = {
                new BBReader(),
                new RainmeterReader()
        };
        String output = "";

        try{
            InternalReader iReader = new InternalReader(new FileReader(PATH_FILE));
            String s;
            while((s = iReader.readLine())!=null){
                if(s.length() > 0) {
                    for(int i=0;i<readerArray.length;i++){
                        output = output.concat(readerArray[i].readFileConservative(s));
                    }
                }
            }
            iReader.close();
            System.out.println("Scheme created");
        }catch (Exception e){
            System.out.println(e.toString());
            System.out.println("Error creating scheme");
        }
        //println(output);
        IO.writeToFile(output,SCHEME_PATH);
    }//generateScheme

}

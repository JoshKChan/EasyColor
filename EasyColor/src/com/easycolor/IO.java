package com.easycolor;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Joshua on 20/08/2014.
 */
public class IO {

    private static final String BACKUP_LOCATION = "backups";

    public static void writeToFile(String input,String path){
        try{
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(input);
            bw.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static void backup(String path){
        try{
            File backupFolder = new File(BACKUP_LOCATION);
            if(!backupFolder.exists()){
                backupFolder.mkdir();
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
            Date date = new Date();
            String timeStamp = dateFormat.format(date);
            String dateFolderName = BACKUP_LOCATION.concat("\\").concat(timeStamp);
            File dateFolder = new File(dateFolderName);
            dateFolder.mkdir();

            String originalName = extractFileName(path);

            File original = new File(path);
            File copy = new File(dateFolderName.concat("\\").concat(originalName));
            FileInputStream inStream = new FileInputStream(original);
            FileOutputStream outStream = new FileOutputStream(copy);
            byte[] buffer = new byte[1024];
            int length;
            while((length = inStream.read(buffer))>0){
                outStream.write(buffer,0,length);
            }
            inStream.close();
            outStream.close();
            //Path original = Paths.get(path);
            //Path target = Paths.get(targetName);
            //Files.copy(original,target);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    private static String extractFileName(String path){
        String out = path;
        int extStart = path.lastIndexOf('\\');
        if(extStart != -1) {
            out = path.substring(extStart+1);
        }
        return out;
    }

}

package uk.co.cartaxchecker.Utils;

import uk.co.cartaxchecker.constants.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadInputFiles {
    public List<String> getAllRegistrationNumbers(){
        File dir = new File(constants.inputDir);
        File[] directoryListing = dir.listFiles();
        Pattern regexp = Pattern.compile("\\b([A-Z]{3}\\s?(\\d{3}|\\d{2}|d{1})\\s?[A-Z])|([A-Z]\\s?(\\d{3}|\\d{2}|\\d{1})\\s?[A-Z]{3})|(([A-HK-PRSVWY][A-HJ-PR-Y])\\s?([0][2-9]|[1-9][0-9])\\s?[A-HJ-PR-Z]{3})\\b");
        List<String> registrationNumbers = new ArrayList<>();
        if(directoryListing!=null){
            for (File file : directoryListing) {
                System.out.println(file.getName());

                try {
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line = br.readLine()) != null) {
                        Matcher m = regexp.matcher(line);
                        while(m.find())
                        {
                            int start = m.start(0);
                            int end = m.end(0);
                            registrationNumbers.add(line.substring(start,end).replaceAll("\\s",""));
                        }
                    }

                }catch (IOException err){
                    err.printStackTrace();
                }

                }
            } else{
            System.out.println("Directory is empty");
        }
        return registrationNumbers;
    }

}

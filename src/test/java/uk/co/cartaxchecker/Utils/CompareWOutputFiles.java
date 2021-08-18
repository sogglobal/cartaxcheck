package uk.co.cartaxchecker.Utils;

import uk.co.cartaxchecker.CarDetails;
import uk.co.cartaxchecker.constants.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.SQLOutput;
import java.util.List;

public class CompareWOutputFiles {
    public static void compareResults(List<CarDetails> carDetails) {
        int passCount = 0;

        try{
            FileReader fr = new FileReader(constants.outputFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int i=0;
            line = br.readLine();
            while((line = br.readLine())!=null){
                String[] values = new String[5];
                values = line.split(",");

                for (int j = 0; j < carDetails.size(); j++) {
                    if(carDetails.get(j).registrationNumber.equals(values[0])){
                        System.out.println("Comparing for registration Number ==>"+values[0]);
                        if (values[0].equals(carDetails.get(j).registrationNumber)) {
                            System.out.println("\tRegistration Number Matched for: "+carDetails.get(j).registrationNumber);
                        }else{
                            System.out.println("Registration number did not match");
                        }
                        if (values[1].equals(carDetails.get(j).make)) {
                            System.out.println("\tMake of the car matched for: "+carDetails.get(j).make);
                        }else{
                            System.out.println("Make did not match");
                        }
                        if (values[2].equals(carDetails.get(j).model)) {
                            System.out.println("\tModel of the car matched for: "+carDetails.get(j).model);
                        }else{
                            System.out.println("Model did not match");
                        }
                        if (values[3].equals(carDetails.get(j).colour)) {
                            System.out.println("\tColour of the car matched for: "+carDetails.get(j).colour);
                        }else{
                            System.out.println("Colour did not match");
                        }
                        if (values[4].equals(carDetails.get(j).year)) {
                            System.out.println("\tYear of the car matched for: "+carDetails.get(j).year);
                            passCount++;
                        }else{
                            System.out.println("Year did not match");
                        }
                    }
                    else{
                        continue;
                    }
                }


                i++;
            }
        }catch (IOException err){
            err.printStackTrace();
        }
        System.out.println(passCount+" tests passed out of Total "+carDetails.size());
    }
}

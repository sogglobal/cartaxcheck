package uk.co.cartaxchecker;

import uk.co.cartaxchecker.Utils.CompareWOutputFiles;
import uk.co.cartaxchecker.Utils.ReadInputFiles;

import java.util.List;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException{
        //These steps reads the input file and extracts all the valid UK Registration Numbers.
        ReadInputFiles rif = new ReadInputFiles();
        List<CarDetails> outputFromCarTaxCheck = CarTaxCheckUIAutomation.getCarDetailsFromWebsite(rif.getAllRegistrationNumbers());
        //This step compares the extracted results from cartaxcheck website to the output file.
        CompareWOutputFiles.compareResults(outputFromCarTaxCheck);
    }
}

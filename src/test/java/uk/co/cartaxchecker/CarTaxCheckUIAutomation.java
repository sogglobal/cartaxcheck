package uk.co.cartaxchecker;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CarTaxCheckUIAutomation {
    public static List<CarDetails> getCarDetailsFromWebsite(List<String> registrationNumbers) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<CarDetails> carDetails = new ArrayList<>();
        for (String registrationNumber : registrationNumbers) {
            driver.get("http://cartaxcheck.co.uk");
            driver.findElement(By.cssSelector("#vrm-input")).sendKeys(registrationNumber);
            driver.findElement(By.xpath("//button[text()='Free Car Check']")).click();
            Thread.sleep(1500);

            String regNum = driver.findElement(By.xpath("//dt[text()='Registration']/following-sibling::dd")).getText();
            if (regNum.isEmpty()) {
                regNum = registrationNumber;
            }
            String make = driver.findElement(By.xpath("//dt[text()='Make']/following-sibling::dd")).getText();
            String model = driver.findElement(By.xpath("//dt[text()='Model']/following-sibling::dd")).getText();
            String colour = driver.findElement(By.xpath("//dt[text()='Colour']/following-sibling::dd")).getText();
            String year = driver.findElement(By.xpath("//dt[text()='Year']/following-sibling::dd")).getText();

            carDetails.add(new CarDetails(regNum,make,model,colour,year));

        }
        System.out.println("Following details fetched from cartaxcheck.co.uk website:");
        for (CarDetails carDetails1 : carDetails) {
            System.out.println(carDetails1.registrationNumber+','+carDetails1.make+','+carDetails1.model+','+carDetails1.colour+','+carDetails1.year);
        }
        driver.quit();
        return carDetails;
    }
}

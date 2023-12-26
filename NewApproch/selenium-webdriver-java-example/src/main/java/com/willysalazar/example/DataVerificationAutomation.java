package com.willysalazar.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class DataVerificationAutomation {
    public static void main(String[] args)
    {
        // List of names and corresponding companies
        long startTime = System.currentTimeMillis();
        String[][] profiles = {
                {"Bryan Adams,Integrity Marketing Group", "Integrity Marketing Group"},
                {"Jane Adams,Piper Jaffray & Company", "Piper Jaffray & Company"},
                {"Mark Affolter,Ares Management LLC", "Ares Management LLC"},
        };
        System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\119_chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Navigate to LinkedIn login page
        driver.get("https://www.linkedin.com/login");

        // Enter username and password
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys("rohithbolla97@gmail.com");
        passwordInput.sendKeys("Encoress@123");

        // Click on the login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        for (String[] profile : profiles) {
            String name = profile[0];
            String company = profile[1];

            // Step 2: Search for the name and press Enter
            WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search']"));

            // Clear the search input before entering a new name
            searchBox.clear();

            searchBox.sendKeys(name);
            searchBox.sendKeys(Keys.RETURN);

            try {
                WebElement viewFullProfileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='View full profile']")));
                viewFullProfileButton.click();
            }catch (TimeoutException e){
                try {
                    String[] parts = name.split(",");
                    name = parts[0];
                    WebElement nameLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + name + "']/ancestor::a")));
                    nameLink.click();
                } catch (TimeoutException ex) {
                    System.out.println("No records for the user name"+ name);
                }
            }

            try{

            }
            catch (Exception e)
            {

            }
        }
    }
}

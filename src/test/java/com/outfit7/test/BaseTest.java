package com.outfit7.test;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NotFoundException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import com.outfit7.constants.IDElements;
import com.outfit7.driver.Driver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BaseTest {

    AppiumDriver<MobileElement> driver;

    @BeforeAll
    public void initTest() throws Exception {
        startAppiumConnection();
        prepareState();
    }

    @AfterAll
    public void cleanUpTest() {
        // quit driver
        driver.quit();
    }

    private void startAppiumConnection() throws Exception {
        // get driver
        driver = Driver.getAndroidDriver();
    }

    private void prepareState() throws InterruptedException, IOException, NotFoundException {
        Dimension windowSize = driver.manage().window().getSize();

        // Wait for first element so we can continue
        driver.findElement(By.id(IDElements.AGE_SCREENING_BUTTON_OK));

        // Swipe to get to some age
        String command = String.format("adb shell input swipe %1$s %2$s %3$s %4$s %5$s",
                windowSize.width * 0.5, windowSize.height * 0.5, windowSize.width * 0.5, windowSize.height * 0.7, 1000);
        Runtime.getRuntime().exec(command);

        // Wait for animation to end
        Thread.sleep(2000);

        // Click button ok to continue
        driver.findElement(By.id(IDElements.AGE_SCREENING_BUTTON_OK)).click();

        // Sometimes we get asked for special permission
        MobileElement allowPermissionButton = driver.findElement(By.id(IDElements.PERMISSION_ALLOW_BUTTON));
        if (allowPermissionButton != null) {
            allowPermissionButton.click();
        }

        // Pass gdpr screen
        // If there is internet connection on android device than gdpr screen will be shown,
        // otherwise it will not be shown.
        try{
            driver.findElement(By.id(IDElements.GDPR_YES)).click();
        }catch (NotFoundException e){
            System.out.println("GDPR screen was not found. Maybe there is no internet connection on device.");
        }

        // Check if we are in main room
        driver.findElement(By.id(IDElements.FOOD_BUTTON));
        System.out.println("###We are in main room###");
    }
}

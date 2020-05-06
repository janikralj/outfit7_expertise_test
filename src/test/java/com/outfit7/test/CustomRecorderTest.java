package com.outfit7.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;

import com.outfit7.constants.IDElements;
import com.outfit7.driver.Driver;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomRecorderTest extends BaseTest {

    @Test
    void recorderTest() throws InterruptedException, IOException {
        // start recording
        driver.findElement(By.id(IDElements.RECORDER_BUTTON)).click();
        // Wait to start recording
        Thread.sleep(200);

        // tap on middle of screen to hurt Tom
        Driver.tapOnMiddleOfScreen(driver);
        Driver.takeScreenshot(driver, "tom_recorder/tap/recording");

        // click on pawn, while recording take screenshot
        driver.findElement(By.id(IDElements.PAWN_BUTTON)).click();
        Thread.sleep(2000);
        Driver.takeScreenshot(driver, "tom_recorder/pawn/recording");
        Thread.sleep(2000);

        // stop recording
        driver.findElement(By.id(IDElements.RECORDER_BUTTON)).click();

        // play recorded video and take screenshots.
        driver.findElement(By.id(IDElements.RECORDER_MENU_PLAY_BUTTON)).click();
        Thread.sleep(1200);
        Driver.takeScreenshot(driver,"tom_recorder/tap/playing_recorded");
        Thread.sleep(2500);
        Driver.takeScreenshot(driver, "tom_recorder/pawn/playing_recorded");
        Thread.sleep(1300);

        // close recorded video menu
        driver.findElement(By.id(IDElements.RECORDER_MENU_CLOSE_BUTTON)).click();

        // check we are back to Tom where it resides
        driver.findElement(By.id(IDElements.FOOD_BUTTON));

        System.out.println("Tap and pawn actions with recorder functionality done.");
    }
}

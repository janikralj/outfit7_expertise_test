package com.outfit7.test;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import com.outfit7.constants.IDElements;
import com.outfit7.constants.XPaths;
import com.outfit7.driver.Driver;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TomTests extends BaseTest {

    /**
     * Go to how to play info and validate first paragraph of list
     */
    @Order(1)
    @Test
    void testHowToPlay() {
        // Click correct buttons to go on How To Play info
        driver.findElement(By.id(IDElements.INFO_BUTTON)).click();
        driver.findElement(By.id(IDElements.INFO_HOW_TO_PLAY_BUTTON)).click();

        List<MobileElement> elements = driver.findElements(By.xpath(XPaths.LIST_OF_INFO_HOW_TO_PLAY));
        // validate expected string with actual of first paragraph.
        assertEquals("Talk to Tom and he repeats with a funny voice", elements.get(0).getText());
        // clean up after testing
        driver.findElement(By.id(IDElements.INFO_BUTTON_CLOSE)).click();
        driver.findElement(By.id(IDElements.FOOD_BUTTON));

        System.out.println("###Back to Tom after reading how to play.###");
    }

    /**
     * Feed Tom with cake, this will be tested that we take screenshot and save it.
     * Also we will validate that food number has been decreased by 1 when feeding Tom.
     */
    @Order(2)
    @Test
    void feedTomWithCake() throws InterruptedException, IOException {
        driver.findElement(By.id(IDElements.FOOD_BUTTON)).click();
        // Get current food number
        int foodNumber = Integer.parseInt(driver.findElement(By.id(IDElements.FOOD_NUMBER)).getText());
        // feed Tom with cake
        driver.findElement(By.id(IDElements.FOOD_ITEM_CAKE)).click();
        // wait some time for animation
        Thread.sleep(4000);
        // Take screenshot
        Driver.takeScreenshot(driver, "tom_feed_cake");
        // wait fot animation to finish
        Thread.sleep(5000);

        // check that we are back to Tom where he resides
        driver.findElement(By.id(IDElements.FOOD_BUTTON)).click();
        // number of food after cake has been eaten
        int foodNumberAfterEating = Integer.parseInt(driver.findElement(By.id(IDElements.FOOD_NUMBER)).getText());
        // validate that number of available food has been decreased by 1
        assertEquals(foodNumber - 1, foodNumberAfterEating);

        System.out.println("###Tom ate cake and number of available food resources has been decreased by 1.###");
    }

    /**
     * Testing functionality of tap (hurting Tom) and pawn action with recording.
     * @throws InterruptedException
     * @throws IOException
     */
    @Order(3)
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

        System.out.println("###Tap and pawn actions with recorder functionality done.###");
    }
}

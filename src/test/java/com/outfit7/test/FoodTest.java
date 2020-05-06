package com.outfit7.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;

import com.outfit7.constants.IDElements;
import com.outfit7.driver.Driver;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FoodTest extends BaseTest {

    /**
     * Feed Tom with cake, this will be tested that we take screenshot and save it.
     * Also we will validate that food number has been decreased by 1 when feeding Tom.
     */
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

        System.out.println("Tom ate cake and number of available food resources has been decreased by 1.");
    }
}

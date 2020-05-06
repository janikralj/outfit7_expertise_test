package com.outfit7.test;

import io.appium.java_client.MobileElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import java.util.List;

import com.outfit7.constants.IDElements;
import com.outfit7.constants.XPaths;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InfoTest extends BaseTest {

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

        System.out.println("Back to Tom after reading how to play.");
    }
}

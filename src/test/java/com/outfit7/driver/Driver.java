package com.outfit7.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Driver {
    /**
     * Setup and start appium driver with desired capabilities.
     * @return driver
     * @throws Exception
     */
    public static AndroidDriver<MobileElement> getAndroidDriver() throws Exception {
        // Set driver capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("fullReset", "true");
        capabilities.setCapability("deviceName", "Android Device");
        capabilities.setCapability("platformName", "Android");
        File file = new File("src/main/resources/base.apk");
        String absolutePath = file.getAbsolutePath();
        capabilities.setCapability("app", absolutePath);

        // Create new android driver, the default port for Appium is 4723
        AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(
                new URL("http://127.0.0.1:4723/wd/hub"), capabilities
        );

        // Default appium timeout for searching elements
        Driver.changeDriversIimplicitlyWait(driver, 8);
        return driver;
    }

    /**
     * Change search timeout of driver in seconds.
     * @param driver AndroidDriver
     * @param seconds Seconds in int
     */
    public static void changeDriversIimplicitlyWait(AndroidDriver<MobileElement> driver, int seconds){
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /**
     * Take screenshot and save it to src/test/images/screenshots/ folder.
     * @param driver driver in which it will take screenshot
     * @param fileName name of the file which will me appended to the end of the folder
     * @throws IOException
     */
    public static void takeScreenshot(AppiumDriver<MobileElement> driver, String fileName) throws IOException {
        String folderName = "src/test/images/screenshots/" + fileName;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String newFileName = df.format(new Date()) + ".png";
        // Create folders if they don't exists
        new File(folderName).mkdirs();
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(folderName + "/" + newFileName));
    }

    /**
     * Get coordinates of middle point on screen.
     * @param driver
     * @return array of middle coordiates of screen
     */
    public static int[] getMiddleCoordinates(AppiumDriver<MobileElement> driver) {
        Dimension windowSize = driver.manage().window().getSize();
        return new int[]{(int)(windowSize.width * 0.5), (int)(windowSize.height * 0.5)};
    }

    /**
     * Tap on middle of screen.
     * @param driver
     */
    public static void tapOnMiddleOfScreen(AppiumDriver<MobileElement> driver) {
        TouchAction touchAction=new TouchAction(driver);
        int[] middleCoordinates = Driver.getMiddleCoordinates(driver);
        touchAction.tap(PointOption.point(middleCoordinates[0], middleCoordinates[1])).perform();
    }
}
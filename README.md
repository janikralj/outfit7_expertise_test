# Junior Test Automation Engineer expertise test for Outfit7

## **Documentation about expertise test**

I changed already existing code in provided sample project for easier usage. I created driver and constants classes.
In driver class, which should be used to work with driver, there are methods like: create driver, takeScreenshot, tap on middle of the screen, ...
Under constants there are 2 classes, one is for id_elements and one for xpaths.
The purpose of those 2 classes is that all constants are stored in one place and are not hardcoded all over the project.

Also added @AfterAll Junit method which is executed after all tests. With this we properly quit driver.

When executing each test we expect that we are on first activity where Tom resides, and as last step (in each test) we clean what we have done during testing (meaning we go back to first activity where Tom resides).

All taken screenshots are saved in a folder -> `src/test/images/screenshots/` with additional subfolders.
Each image is saved as `yyyy-MM-dd_HH_mm_ss.png`

## *Test scenarios for all 3 tests.*

### **1. Check how to play information.**

Test steps:
* Search and click on Info button.
* Search and click on How to play button.
* Assert that text of first paragraph should be ***Talk to Tom and he repeats with a funny voice***.
* Click on close info button

### **2. Check for Tom eating some food (cake).**

Feed Tome with cake. Before feeding Tom get number of available food.
During feeding take screenshot of Tom how he eats. After animation is done assert that food number has been reduced by 1.

Test steps:
* Search and click on Food button.
* Get food number before eating.
* Search and click on food item Cake.
* Wait some time and then take screenshot (saved to: `src/test/images/screenshots/tom_feed_cake/` ).
* Wait for animation to stop.
* Assert that food number after eating was reduced by 1.

### **3. Check for tap on Tom and pawn action with recorder functionality.**

Testing recorder will be done with 2 actions: tap on Tom and pawn.
During recording, we will tap on Tom and take a screenshot, so we can check that Tom has been tapped.
Then we will also perform Pawn action and take a screenshot of Tom's animation.
After that we will stop recording and play video. During playing, we will take a screenshots of those 2 performed actions.

Test steps:
* Search and click on Recorder button to start recording.
* Tap on Tom and take a screenshot (saved to: `src/test/images/screenshots/tom_recorder/tap/recording/`).
* Click on Pawn action, wait for 2 seconds and take a screenshot (saved to: `src/test/images/screenshots/tom_recorder/pawn/recording/`).
* Wait another 2 seconds to finish the animation.
* Click on Recorder button to stop recording.
* Click on Play button to play recorded video.
* Wait 1.2 seconds and take a screenshot of animation of Tom being taped (saved to: `src/test/images/screenshots/tom_recorder/tap/playing_recorded/`).
* Wait 2.5 seconds and take a screenshot of animation of Tom scratching (saved to: `src/test/images/screenshots/tom_recorder/pawn/playing_recorded/`).
* Wait for the rest of video to end and click on close recorder menu button.

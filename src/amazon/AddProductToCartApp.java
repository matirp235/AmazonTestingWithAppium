package amazon;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AddProductToCartApp {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub

		DesiredCapabilities capabilities = new DesiredCapabilities();
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/src");
		File app = new File(appDir, "Amazon Shopping_v24.4.0.100_apkpure.com.apk");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "testAutomation");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
		capabilities.setCapability("noReset", "true");
		// If you won't like to un-install the apk before each run
		// So, yo can able to setup one-time username and password for login and will be
		// persisted for all the sessions.
		capabilities.setCapability("fullReset", "false");
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// find search box and enter keys

//		driver.findElementByXPath("//android.widget.TextView[@text='Search Amazon']").click();
//		driver.findElementByXPath("//android.widget.TextView[@text='Search Amazon']").sendKeys("Mobile");

		driver.findElementById("com.amazon.mShop.android.shopping:id/chrome_search_hint_view").click();
		driver.findElementById("com.amazon.mShop.android.shopping:id/rs_search_src_text").sendKeys("Mobile" + "\n");
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));

		driver.hideKeyboard();

//		List<AndroidElement> elementsOne = (List<AndroidElement>) driver.findElementsByAccessibilityId("SomeAccessibilityID");
		List<AndroidElement> elementsOne = driver.findElementsByClassName("android.view.View");

		WebElement elem = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiSelector().resourceId(\"com.google.android.apps.maps:id/recycler_view\").childSelector(new UiSelector().className(\"android.widget.LinearLayout\").index(0))"));

	}

}

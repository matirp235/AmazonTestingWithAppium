package amazon;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;

public class AddProductToCartBrowser {

	public static AndroidDriver driver;

	public static <WebElement> void main(String[] args) throws MalformedURLException, InterruptedException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "testAutomation");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

//		Visit Amazon URL
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//		Find the search box and type the search keyword
		driver.findElement(By.xpath("//input[@placeholder='Search Amazon.in']")).sendKeys("mobile phone");
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.hideKeyboard();
		Thread.sleep(5000);

		// div[contains(@class,'SEARCH_RESULTS')]
		// div[contains(@class,'SEARCH_RESULTS')]/following::*[@title='product-detail']/following::span[contains(@class,'a-text-normal')]

//		Listing all search results in one element to select the desired answer
		List<AndroidElement> elementsOne = driver.findElements(By.xpath("//div[contains(@class,'SEARCH_RESULTS')]"));
		elementsOne.get(0).click();
		Thread.sleep(5000);

//		capture product name in product details page
		String productDescriptionAtDetailsPage = driver
				.findElement(By.xpath("//div[contains(@id, 'title_feature_div')]")).getText();
		System.out.println(productDescriptionAtDetailsPage);

//		capture product price in product details page
		String productPriceAtDetailsPage = driver.findElement(By.xpath("//span[contains(@class, 'a-price-whole')]"))
				.getText();
		System.out.println(productPriceAtDetailsPage);

//		click on add to cart button
		driver.findElement(By.xpath("//input[contains(@id, 'add-to-cart-button')]")).click();

//		capture product price in cart page
		// span[@class='a-truncate-cut']
		String productDescriptionCheckOut = driver.findElement(By.xpath("//span[contains(@class,'a-truncate-cut')]"))
				.getText();
		System.out.println(productDescriptionCheckOut);

//		capture product price in cart page
		// span[@class='a-price-whole']
		String productPriceCheckOut = driver.findElement(By.xpath("//span[contains(@class,'a-price-whole')]"))
				.getText();
		System.out.println(productPriceCheckOut);

//		Validating the Name and Price are matching or not
//		Assert.assertTrue("The product description text matched", productDescriptionCheckOut.contains(
//				productDescriptionAtDetailsPage.substring(0, productDescriptionAtDetailsPage.indexOf("..."))));
		Assert.assertTrue("The price matched", productPriceAtDetailsPage.contains(productPriceCheckOut));
		Assert.assertTrue("The product description text matched",
				productDescriptionAtDetailsPage.contains(productDescriptionCheckOut));

	}

}

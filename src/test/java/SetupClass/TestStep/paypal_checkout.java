
package SetupClass.TestStep;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SetupClass.SetUPClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class paypal_checkout extends SetUPClass {

	WebDriverWait wait = new WebDriverWait(driver, 50);
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@Given("^user is already on Website Home Page pp$")
	public void user_is_already_on_Website_Home_Page_pp() throws Throwable {
		driver.get(AppURL);
		Thread.sleep(2000);
		ClearBrowserCache();
		Thread.sleep(2000);
	}

	@Then("^user navigates to sign up page pp$")
	public void user_navigates_to_sign_up_page_pp() throws Throwable {
		Thread.sleep(2000);
		try {
			driver.findElement(By.cssSelector("ul.header > li:nth-child(1) > a:nth-child(1)")).click();
			// Thread.sleep(2000);
			log.info("It's opening the website URL and redirect user to sign up page");
		} catch (NoSuchElementException popup) {
		}
	}

	@Then("^user create a new ac count pp$")
	public void user_create_a_new_ac_count_pp() throws Throwable {

		// create new email for sign up
		Thread.sleep(5000);
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		System.out.println(generatedString);

		String signup_email = generatedString;
		String full_email = "selenium.testing." + generatedString + "@gmail.com";
		System.out.println(full_email);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(1000);
		Thread.sleep(2000);
		WebElement new_email_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email_address']")));
		Thread.sleep(2000);
		new_email_signup.sendKeys(full_email);
		Thread.sleep(2000);

		// enter name

		WebElement new_fname_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='firstname']")));
		Thread.sleep(2000);// input[@id='firstname']
		new_fname_signup.sendKeys("Selenium");
		Thread.sleep(2000);

		WebElement new_lname_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='lastname']")));
		Thread.sleep(2000);
		new_lname_signup.sendKeys("Testing");
		Thread.sleep(2000);

		// enter password
		WebElement new_pwd_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));
		Thread.sleep(2000);
		new_pwd_signup.sendKeys("selenium@123");
		Thread.sleep(2000);

		WebElement new_pwd1_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password-confirmation']")));
		Thread.sleep(2000);
		new_pwd1_signup.sendKeys("selenium@123");
		Thread.sleep(2000);
		WebElement new_btn_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Sign Up']")));
		Thread.sleep(2000);
		new_btn_signup.click();
	}

	@Then("^user is redirected to pricing page and choose a plan to pay pp$")
	public void user_is_redirected_to_pricing_page_and_choose_a_plan_to_pay_pp() throws Throwable {
		// choose a plan
		WebElement join_now_btn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[3]//div[3]//span[1]//form[1]//button[1]//span[1]")));
		js.executeScript("arguments[0].scrollIntoView();", join_now_btn);
		// Thread.sleep(2000);
		join_now_btn.click();
		Thread.sleep(5000);

	}

	@Then("^user is redirected to checkout page pp$")
	public void user_is_redirected_to_checkout_page_pp() throws Throwable {
		Thread.sleep(1000);
		try {
			WebElement cp_btn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='paypal_express']")));

			cp_btn.click();
		} catch (Exception e) {

		}
	}

	@Then("^user proceed to pay with paypal pp$")
	public void user_proceed_to_pay_with_paypal_pp() throws Throwable {
		Thread.sleep(5000);
		SetUPClass.Chat_window_handle();
		// place order button
		try {
			WebElement place_order_btn = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//button[@id='place-order-trigger']//span[contains(text(),'Place Order')] ")));
			js.executeScript("arguments[0].scrollIntoView();", place_order_btn);

			place_order_btn.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^paypal popup appears and user navigates back to my account pp$")
	public void paypal_popup_appears_and_user_navigates_back_to_my_account_pp() throws Throwable {

		// Maximize Window
		driver.manage().window().maximize();

		// Store the CurrentWindow for future reference
		// String handle = " ";
		String currentWindow = driver.getWindowHandle();
		String popupWindowHandle = null;

		// Switch To Popup Window

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(currentWindow)) {

				popupWindowHandle = handle;
				driver.switchTo().window(popupWindowHandle);
				driver.manage().window().maximize();
			}
		}

		// page title
		String pp_page_title = driver.getTitle();
		// Thread.sleep(3000);
		System.out.println("Title of the Page is --> " + pp_page_title);
		// Switch To Default Window

		driver.switchTo().window(currentWindow);
		Thread.sleep(2000);

	}

	@Then("^user deleted the account pp$")
	public void user_deleted_the_account_pp() throws Throwable {

		Thread.sleep(2000);
		WebElement account = driver.findElement(By.xpath("//a[contains(.,'My Account')]"));
		js.executeScript("arguments[0].click();", account);
		Thread.sleep(3000);

		// handling the chat window here
		// SetUPClass.Chat_window_handle();

		WebElement Delete_Account = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Delete Account']")));
		Thread.sleep(2000);
		Delete_Account.click();
		Thread.sleep(1000);
		WebElement radio_button = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='option1']")));
		Thread.sleep(2000);
		radio_button.click();
		Thread.sleep(1000);
		WebElement delete_Profile = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Delete Profile']")));
		js.executeScript("arguments[0].scrollIntoView();", delete_Profile);
		delete_Profile.click();
		Thread.sleep(2000);
		WebElement continue_delete = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'No, delete my')]")));
		js.executeScript("arguments[0].scrollIntoView();", continue_delete);
		continue_delete.click();
		Thread.sleep(4000);
		String verifyDeleteAccount = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//span[@x-html='message.text']"))).getText();
		Thread.sleep(3000);
		Assert.assertTrue("Account is not deleted",
				verifyDeleteAccount.contains("Your account has been deleted successfully."));
		System.out.println("your account delete successfully");
		// done
	}

}

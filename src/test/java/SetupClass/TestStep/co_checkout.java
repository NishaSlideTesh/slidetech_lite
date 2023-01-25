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

public class co_checkout extends SetUPClass {

	WebDriverWait wait = new WebDriverWait(driver, 50);
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@Given("^user is already on Website Home Page (\\d+)CO$")
	public void user_is_already_on_Website_Home_Page_CO(int arg1) throws Throwable {
		driver.get(AppURL);
		Thread.sleep(2000);
		ClearBrowserCache();
		Thread.sleep(2000);
		log.info("It's opening the website URL");

	}

	@Then("^user navigates to sign up page (\\d+)CO$")
	public void user_navigates_to_sign_up_page_CO(int arg1) throws Throwable {
		try {
			// driver.get("https://www.slideteam.net/customer/account/create/");
			WebElement Sign_Up = driver.findElement(By.cssSelector("ul.header > li:nth-child(1) > a:nth-child(1)"));
			Thread.sleep(1000);
			Sign_Up.click();
			log.info("It's opening the website URL and redirect user to sign up page");
		} catch (NoSuchElementException popup) {
		}
	}

	@Then("^user create a new ac count (\\d+)CO$")
	public void user_create_a_new_ac_count_CO(int arg1) throws Throwable {
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

		Thread.sleep(2000);
		WebElement new_email_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email_address']")));
		Thread.sleep(2000);
		new_email_signup.sendKeys(full_email);
		Thread.sleep(2000);

		// enter name

		WebElement new_fname_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='firstname']")));
		Thread.sleep(2000);
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
		Thread.sleep(3000);
		new_btn_signup.click();

		Thread.sleep(2000);
	}

	@Then("^user is redirected to pricing page and choose a plan to pay (\\d+)CO$")
	public void user_is_redirected_to_pricing_page_and_choose_a_plan_to_pay_CO(int arg1) throws Throwable {
		// choose a plan

		Thread.sleep(2000);
		WebElement join_now_btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(.,'Join now')])[8]")));

		join_now_btn.click();

	}

	@Then("^user is redirected to checkout page (\\d+)CO$")
	public void user_is_redirected_to_checkout_page_CO(int arg1) throws Throwable {
		Thread.sleep(6000);
	}

	@Then("^user proceed to pay with (\\d+)CO (\\d+)CO$")
	public void user_proceed_to_pay_with_CO_CO(int arg1, int arg2) throws InterruptedException {
		try {
			// select 2co option
			WebElement co_btn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='amasty_stripe']")));
			// Thread.sleep(2000);
			co_btn.click();

			SetUPClass.Chat_window_handle();
			// place order button

			WebElement place_order_btn = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//button[@id='place-order-trigger']//span[contains(text(),'Place Order')] ")));
			Thread.sleep(2000);
			js.executeScript("arguments[0].scrollIntoView();", place_order_btn);

			place_order_btn.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("^paypal popup appears and user navigates back to my account (\\d+)CO$")
	public void paypal_popup_appears_and_user_navigates_back_to_my_account_CO(int arg1) throws Throwable {
		String co_page_title = driver.getTitle();
		Thread.sleep(3000);
		System.out.println("Title of the Page is --> " + co_page_title);

		String page_title = "Checkout";

		if (page_title.equalsIgnoreCase(co_page_title)) {
			System.out.println(" user is on the 2checkout page");
			log.info("USER IS ON THE 2CHECKOUT PAGE");
		} else {
			System.out.println("user is on the wrong page");
			log.info("USER IS ON THE WRONG PAGE");
		}
	}

	@Then("^user deleted the account (\\d+)CO$")
	public void user_deleted_the_account_CO(int arg1) throws Throwable {
		Thread.sleep(2000);
		WebElement account = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'My Account')]")));
		js.executeScript("arguments[0].click();", account);
		Thread.sleep(3000);

		WebElement delete_account = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='clicking']")));
		Thread.sleep(3000);
		// js.executeScript("arguments[0].scrollIntoView();", delete_account);
		js.executeScript("arguments[0].click();", delete_account);

		Thread.sleep(3000);
		WebElement delete_reason = driver.findElement(By.cssSelector("input#exampleRadios1"));
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", delete_reason);
		Thread.sleep(3000);

		WebElement delete_profile = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#delete-final")));
		js.executeScript("arguments[0].click();", delete_profile);
		Thread.sleep(3000);

		WebElement delete_profile_coupon = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[@class = 'btn btn-default button_2']")));
		delete_profile_coupon.click();
		Thread.sleep(30000);
		String verifyDeleteAccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@x-html='message.text']"))).getText();
		Thread.sleep(3000);
		Assert.assertTrue("Account is not deleted",
				verifyDeleteAccount.contains("Your account has been deleted successfully."));
		System.out.println("your account delete successfully");
	}

}

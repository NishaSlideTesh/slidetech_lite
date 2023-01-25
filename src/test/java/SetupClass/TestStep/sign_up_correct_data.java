package SetupClass.TestStep;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SetupClass.SetUPClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class sign_up_correct_data extends SetUPClass {
	WebDriverWait wait = new WebDriverWait(driver, 50);
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@Given("^user is already on sign up page cd$")
	public void user_is_already_on_sign_up_page_cd() throws Throwable {

		driver.get(AppURL);
		Thread.sleep(2000);
		ClearBrowserCache();
		Thread.sleep(2000);
		try {
			WebElement Signup = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign up")));
			Signup.click();
			// Thread.sleep(1000);
			log.info("It's opening the website URL and redirect user to sign up page");
		} catch (NoSuchElementException popup) {
		}
	}

	@Then("^enter new email to sign up cd$")
	public void enter_new_email_to_sign_up_cd() throws InterruptedException {

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
		// driver.findElement(By.id("email_address")).sendKeys(full_email);

		// Thread.sleep(2000);
		WebElement new_email_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email_address']")));
		// Thread.sleep(2000);
		new_email_signup.sendKeys(full_email);
		// Thread.sleep(2000);
	}

	@Then("^User enter firstname and lastname to sign up cd$")
	public void user_enter_firstname_and_lastname_to_sign_up_cd() throws InterruptedException {

		WebElement new_fname_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='firstname']")));
		// Thread.sleep(1000);
		new_fname_signup.sendKeys("Selenium");
		// Thread.sleep(1000);

		WebElement new_lname_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='lastname']")));
		// Thread.sleep(1000);
		new_lname_signup.sendKeys("Testing");
		// Thread.sleep(1000);
	}

	@Then("^user enter password and confirm password to sign up cd$")
	public void user_enter_password_and_confirm_password_to_sign_up_cd() throws InterruptedException {

		WebElement new_pwd_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));
		// Thread.sleep(1000);
		new_pwd_signup.sendKeys("selenium@123");
		Thread.sleep(1000);

		WebElement new_pwd1_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password-confirmation']")));
		// Thread.sleep(1000);
		new_pwd1_signup.sendKeys("selenium@123");
		Thread.sleep(1000);
	}

	@Then("^user enter captcha to sign up cd$")
	public void user_enter_captcha_to_sign_up_cd() throws InterruptedException {

		/*
		 * WebElement new_captcha_signup = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.id("captcha_user_create")))
		 * ; // Thread.sleep(1000); new_captcha_signup.sendKeys("Aj7W2mtf9namwf55");
		 */

	}

	@Then("^check checkbox to sign up cd$")
	public void check_checkbox_to_sign_up_cd() throws InterruptedException {

		/*
		 * WebElement new_checkbox_signup =
		 * wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
		 * ".iCheck-helper"))); Thread.sleep(2000); new_checkbox_signup.click();
		 */
		// Thread.sleep(1000);
	}

	@Then("^user click on sign up button to sign up cd$")
	public void user_click_on_sign_up_button_to_sign_up_cd() throws InterruptedException {

		WebElement new_btn_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Sign Up']")));
		Thread.sleep(4000);
		new_btn_signup.click();

		Thread.sleep(3000);
		// Chat_window_handle();
	}

	@Then("^user lands on pricing page and then user go to free ppts page cd$")
	public void user_lands_on_pricing_page_and_then_user_go_to_free_ppts_page_cd() throws InterruptedException {
		try {
			WebElement free_Stuff = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//li[@class='menu-item has-sub-class']//a[@title = 'Free Stuff']")));
			Actions action = new Actions(driver);
			action.moveToElement(free_Stuff).perform();
			Thread.sleep(3000);
			WebElement free_ppt = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Free Samples']")));
			action.moveToElement(free_ppt).click().perform();
		} catch (NoSuchElementException e) {

		}

	}

	@Then("^user download a free product cd$")
	public void user_download_a_free_product_cd() throws InterruptedException {

		try {
			WebElement select_ppt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//img[@title='3d man education and technology concept free ppt templates graphics icons']")));
			js.executeScript("arguments[0].scrollIntoView();", select_ppt);
			select_ppt.click();
			Thread.sleep(3000);
			WebElement download_ppt = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//button[normalize-space()='Download this presentation']")));
			js.executeScript("arguments[0].scrollIntoView();", download_ppt);
			download_ppt.click();
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {

		}
		// "Enjoy this Product" pop will come
		if (!driver.findElements(By.xpath("//div[@class='checkout_custom']")).isEmpty()) {
			WebElement selectRadioButton = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//label[normalize-space()='Human Resources']//span[@class='checkmark']")));
			selectRadioButton.click();
			Thread.sleep(1000);
			WebElement submitButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Submit']")));
			submitButton.click();
			Thread.sleep(2000);

			WebElement msg = driver.findElement(By.xpath("//span[text() = 'Interest has been saved.']"));
			Assert.assertTrue(msg.isDisplayed());
		}
	}

	@Then("^user delete the new account created cd$")
	public void user_delete_the_new_account_created_cd() throws InterruptedException {
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

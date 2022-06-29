package SetupClass.TestStep;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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
	public void user_is_already_on_sign_up_page_cd() throws InterruptedException {

		driver.get(AppURL);
		Thread.sleep(2000);
		driver.manage().deleteAllCookies();
		Thread.sleep(2000);

		log.info("It's opening the website URL");
		try {
			WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]"));
			if (logout.isEnabled()) {
				logout.click();
				// Thread.sleep(2000);
				driver.navigate().refresh();
				// Thread.sleep(2000);
			}
		} catch (NoSuchElementException Ext) {

		}
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
		WebElement new_email_signup = wait.until(ExpectedConditions.elementToBeClickable(By.id("email_address")));
		// Thread.sleep(2000);
		new_email_signup.sendKeys(full_email);
		// Thread.sleep(2000);
	}

	@Then("^User enter firstname and lastname to sign up cd$")
	public void user_enter_firstname_and_lastname_to_sign_up_cd() throws InterruptedException {

		WebElement new_fname_signup = wait.until(ExpectedConditions.elementToBeClickable(By.id("firstname")));
		// Thread.sleep(1000);
		new_fname_signup.sendKeys("Selenium");
		// Thread.sleep(1000);

		WebElement new_lname_signup = wait.until(ExpectedConditions.elementToBeClickable(By.id("lastname")));
		// Thread.sleep(1000);
		new_lname_signup.sendKeys("Testing");
		// Thread.sleep(1000);
	}

	@Then("^user enter password and confirm password to sign up cd$")
	public void user_enter_password_and_confirm_password_to_sign_up_cd() throws InterruptedException {

		WebElement new_pwd_signup = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
		// Thread.sleep(1000);
		new_pwd_signup.sendKeys("selenium@123");
		Thread.sleep(1000);

		WebElement new_pwd1_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("password-confirmation")));
		// Thread.sleep(1000);
		new_pwd1_signup.sendKeys("selenium@123");
		Thread.sleep(1000);
	}

	@Then("^user enter captcha to sign up cd$")
	public void user_enter_captcha_to_sign_up_cd() throws InterruptedException {

		WebElement new_captcha_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("captcha_user_create")));
		// Thread.sleep(1000);
		new_captcha_signup.sendKeys("Aj7W2mtf9namwf55");

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

		WebElement new_btn_signup = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".submit")));
		// Thread.sleep(2000);
		new_btn_signup.click();
		// Thread.sleep(2000);

	}

	@Then("^user lands on pricing page and then user go to free ppts page cd$")
	public void user_lands_on_pricing_page_and_then_user_go_to_free_ppts_page_cd() throws InterruptedException {

		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		WebElement free_ppt_btn1 = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Free Stuff")));
		actions.moveToElement(free_ppt_btn1)
				.moveToElement(driver.findElement(By.xpath("//a[@title='Free Business PPTs']"))).click().build()
				.perform();

	}

	@Then("^user download a free product cd$")
	public void user_download_a_free_product_cd() throws InterruptedException {

		/*
		 * WebElement select_ppt = wait.until(ExpectedConditions.elementToBeClickable(
		 * By.
		 * xpath("//img[@title='Circular Flow Of Process 4 Stages Free PowerPoint Templates Slides']"
		 * ))); js.executeScript("arguments[0].scrollIntoView();", select_ppt);
		 * select_ppt.click();
		 */
		Thread.sleep(1000);
		driver.get("https://www.slideteam.net/circular-flow-of-process-4-stages-powerpoint-slides-templates.html");
		Thread.sleep(1000);
		WebElement dwnd_btn = driver.findElement(By.cssSelector("#clicking"));
		js.executeScript("arguments[0].scrollIntoView();", dwnd_btn);
		dwnd_btn.click();
		Thread.sleep(1000);
		// "Enjoy this Product" pop will come
		WebElement close_pop_up = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='mfp-close roundlink']")));
		close_pop_up.click();
		Thread.sleep(2000);
	}

	@Then("^user delete the new account created cd$")
	public void user_delete_the_new_account_created_cd() throws InterruptedException {
		Thread.sleep(1000);
		WebElement My_Account = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("My Account")));
		js.executeScript("arguments[0].click();", My_Account);
		// My_Account.click();

		// handling the chat window here
		SetUPClass.Chat_window_handle();

		WebElement Delete_Account = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Delete Account']")));
		Thread.sleep(1000);
		Delete_Account.click();
		WebElement radio_button = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='option1']")));
		radio_button.click();
		Thread.sleep(1000);
		WebElement delete_Profile = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Delete Profile']")));
		js.executeScript("arguments[0].scrollIntoView();", delete_Profile);
		delete_Profile.click();
		Thread.sleep(1000);
		WebElement continue_delete = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'No, delete my')]")));
		js.executeScript("arguments[0].scrollIntoView();", continue_delete);
		continue_delete.click();
		Thread.sleep(2000);

	}

}

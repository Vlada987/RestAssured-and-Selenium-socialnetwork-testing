package frontendSelenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Keys;

public class PageActions { 

	WebDriver driver = WebDriverGet.getFirefoxDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	String signinXp = "/html/body/div/div/div/div[2]/main/div/div/div[1]/div/div/div[3]/div[4]/a/div";
	String nextButtonXp = "/html/body/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/button[2]/div";
	String loginButtonXp = "/html/body/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/button/div";
	String xButtonXp = "//button[@class='ddsdfsdfyi16 sr5416eg1ny4l3l']";
	String unameXp = "//input[@class='-1ya077834djqy7']";
	String xButtonInXp = "/html/body/div[1]/div/div/div[1]/div/div[1]/div/div/div/button";
	String messagesXp = "//div[@class='ceo r-1ttztb7 r-wqim']/span";

	public List<String> getAppData() throws InterruptedException, AWTException {

		openPage();
		Thread.sleep(1000);
		login();
		openProfileInfo();
		Thread.sleep(7000);
		List<String> data = getAllMessages();
		driver.quit();
		return data;

	}

	public void openPage() {

		driver.get("xxxxxx");
	}

	public void login() throws InterruptedException, AWTException {

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xButtonXp))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(signinXp))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='txt']"))).sendKeys(Keys.email);
		driver.findElement(By.xpath(nextButtonXp)).click();

		try {
			driver.findElement(By.xpath(unameXp)).sendKeys(Keys.username);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
		}

		driver.findElement(By.xpath("//input[@name='pass']")).sendKeys(Keys.password);
		driver.findElement(By.xpath(
				"/html/body/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/button/div"))
				.click();
		Thread.sleep(2000);

	}

	public void openProfileInfo() {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xButtonInXp))).click();
		} catch (Exception e) {
		}

		driver.findElement(By.xpath("//a[@data-id='prof23231Link']/div/div")).click();

	}

	public List<String> getAllMessages() {

		List<String> messages = driver.findElements(By.xpath(messagesXp)).stream().map(el -> el.getText())
				.collect(Collectors.toList());

		return messages;
	}

}

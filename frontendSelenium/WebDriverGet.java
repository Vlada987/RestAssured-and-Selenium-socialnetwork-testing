package frontendSelenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverGet {

	public static FirefoxOptions options() {

		FirefoxOptions option = new FirefoxOptions();
		option.addArguments("--headless");
		return option;
	}

	public static WebDriver getFirefoxDriver() {

		WebDriver driver = new FirefoxDriver(options());
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\zikaz\\OneDrive\\Desktop\\projects\\xxx\\geckodriver.exe");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return driver;
	}

}

package kata.automatizacionFront;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AppTest {

	WebDriver driver;

	@BeforeEach
	public void setup() {
		driver = new ChromeDriver();
	}

	@Test
	public void invalidCredentials() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		String title = driver.getTitle();
		assertEquals("OrangeHRM", title);

		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement submitButton = driver.findElement(By.cssSelector("button"));

		username.sendKeys("Selenium");
		password.sendKeys("wrongPass");
		submitButton.click();

		WebElement message = driver.findElement(By.xpath("//div[@class='oxd-alert-content oxd-alert-content--error']"));
		String value = message.getText();
		assertEquals("Invalid credentials", value);
		Thread.sleep(3000);
	}
	
	@Test
	public void emptyUsername() throws InterruptedException{

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		String title = driver.getTitle();
		assertEquals("OrangeHRM", title);

		WebElement password = driver.findElement(By.name("password"));
		WebElement submitButton = driver.findElement(By.cssSelector("button"));

		password.sendKeys("wrongPass");
		submitButton.click();

		WebElement message = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
		String value = message.getText();
		assertEquals("Required", value);
		Thread.sleep(3000);
	}
	
	@Test
	public void emptyUsernameAndPassword() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		String title = driver.getTitle();
		assertEquals("OrangeHRM", title);

		WebElement submitButton = driver.findElement(By.cssSelector("button"));

		submitButton.click();

		WebElement usernameMessage = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required'])[1]"));
		String value = usernameMessage.getText();
		assertEquals("Required", value);
		
		WebElement passwordMessage = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required'])[2]"));
		value = passwordMessage.getText();
		assertEquals("Required", value);
		Thread.sleep(3000);
	}
	


	@Test
	public void CRUDnewUser() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		String title = driver.getTitle();
		assertEquals("OrangeHRM", title);

		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement submitButton = driver.findElement(By.cssSelector("button"));

		username.sendKeys("Admin");
		password.sendKeys("admin123");
		submitButton.click();

		WebElement AdminMenu = driver.findElement(By.xpath("//span[normalize-space()='Admin']"));
		String value = AdminMenu.getText();
		assertEquals("Admin", value);
		AdminMenu.click();
		
		Thread.sleep(5000);
		
		WebElement addBtn = driver.findElement(By.xpath("//button[normalize-space()='Add']"));
		addBtn.click();

		Thread.sleep(5000);
		
		WebElement newUserRoleList = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]"));
		newUserRoleList.click();
		
		WebElement roleAdmin = driver.findElement(By.xpath("(//div[@role='listbox']//child::div)[2]"));
		roleAdmin.click();
		
		Thread.sleep(5000);
		
		WebElement newUserStatusList = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]"));
		newUserStatusList.click();
		
		WebElement statusEnabled = driver.findElement(By.xpath("(//div[@role='listbox']//child::div)[2]"));
		statusEnabled.click();

		Thread.sleep(5000);
		
		WebElement newUserPassword = driver.findElement(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']"));
		newUserPassword.sendKeys("Claudia123");	
		
		WebElement newEmployeeName = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
		newEmployeeName.sendKeys("c");	
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		WebElement employeeName = driver.findElement(By.xpath("(//div[@role='listbox']//child::div)[2]"));
		employeeName.click();

		Thread.sleep(5000);
		
		WebElement newUsername = driver.findElement(By.xpath("//div[@class='oxd-form-row']//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']"));
		newUsername.sendKeys("1Claudia1234567");
		
		WebElement newUserConfirmPassword = driver.findElement(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']"));
		newUserConfirmPassword.sendKeys("Claudia123");

		WebElement newUserSave = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
		newUserSave.click();
		Thread.sleep(5000);
		
		
	}
	

	@AfterEach
	public void teardown() {
		driver.quit();
	}

}

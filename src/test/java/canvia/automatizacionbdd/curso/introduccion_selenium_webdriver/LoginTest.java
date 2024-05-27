package canvia.automatizacionbdd.curso.introduccion_selenium_webdriver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    
    @BeforeEach
    public void setUp() {
    	String CHROME_DRIVER = System.getenv("CHROME_DRIVER");
    	String CHROME_BIN = System.getenv("CHROME_BIN");  
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
        
    	ChromeOptions options = new ChromeOptions();
    	options.setBinary(CHROME_BIN);
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
      
    	driver.manage().window().maximize();
        
    }
    
    @Test
    public void testInvalidLogin() {
        loginPage.enterEmail("invalid@example.com");
        loginPage.enterPassword("invalidpassword");
        loginPage.clickLoginButton();

        String expectedErrorMessage = "Your email or password is incorrect!";
        String actualErrorMessage = loginPage.getErrorMessage();

        assertEquals(expectedErrorMessage, actualErrorMessage, "Error message should be displayed for invalid credentials");
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}

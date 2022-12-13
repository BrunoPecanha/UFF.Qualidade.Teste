package test;



import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginSelenium {

   protected static WebDriver  driver;	
   
        public loginSelenium() {
            System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("https://www.google.com.br");  
        }	   

	public void nomeJaEmUso() {
		driver.navigate().to("https://accounts.google.com/signup/v2/webcreateaccount?biz=false&cc=BR&continue=http%3A%2F%2Fsupport.google.com%2Faccounts%2Fanswer%2F27441%3Fhl%3Den&dsh=S554669373%3A1669074553614322&flowEntry=SignUp&flowName=GlifWebSignIn&hl=en&ifkv=ARgdvAteB6-Sgv4Qbv6Kt53QtaHAEHYO2k_UFGEA-KmFdEI2Uf4Oow4K0gywRbXoBpbCZVenqZNSEw");

                WebElement primeiroNome = driver.findElement(By.name("firstName"));
                primeiroNome.sendKeys("Bruno");
                WebElement sobreNome = driver.findElement(By.name("lastName"));
                sobreNome.sendKeys("Peçanha");
                WebElement email = driver.findElement(By.name("Username"));
                email.sendKeys("brunopecanha");
                email.sendKeys(Keys.TAB);

                new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.className("jibhHc")));

                driver.quit();     
               
	}
	
	public void nomeCurtoDemais() {
		driver.navigate().to("https://accounts.google.com/signup/v2/webcreateaccount?biz=false&cc=BR&continue=http%3A%2F%2Fsupport.google.com%2Faccounts%2Fanswer%2F27441%3Fhl%3Den&dsh=S554669373%3A1669074553614322&flowEntry=SignUp&flowName=GlifWebSignIn&hl=en&ifkv=ARgdvAteB6-Sgv4Qbv6Kt53QtaHAEHYO2k_UFGEA-KmFdEI2Uf4Oow4K0gywRbXoBpbCZVenqZNSEw");
        
            WebElement primeiroNome = driver.findElement(By.name("firstName"));
            primeiroNome.sendKeys("Bruno");
            WebElement sobreNome = driver.findElement(By.name("lastName"));
            sobreNome.sendKeys("Peçanha");
            WebElement email = driver.findElement(By.name("Username"));
            email.sendKeys("mnv");
            email.sendKeys(Keys.TAB);

            new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.className("jibhHc")));
            driver.quit();
	
	}		
	
	public void includoCaracterInvalido() {
            driver.navigate().to("https://accounts.google.com/signup/v2/webcreateaccount?biz=false&cc=BR&continue=http%3A%2F%2Fsupport.google.com%2Faccounts%2Fanswer%2F27441%3Fhl%3Den&dsh=S554669373%3A1669074553614322&flowEntry=SignUp&flowName=GlifWebSignIn&hl=en&ifkv=ARgdvAteB6-Sgv4Qbv6Kt53QtaHAEHYO2k_UFGEA-KmFdEI2Uf4Oow4K0gywRbXoBpbCZVenqZNSEw");

            WebElement primeiroNome = driver.findElement(By.name("firstName"));
            primeiroNome.sendKeys("Bruno");
            WebElement sobreNome = driver.findElement(By.name("lastName"));
            sobreNome.sendKeys("Peçanha");
            WebElement email = driver.findElement(By.name("Username"));
            email.sendKeys("brudsdsd#");
            email.sendKeys(Keys.TAB);

            new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.className("jibhHc")));


            WebElement validar = driver.findElement(By.className(""));
            email.findElement(By.className("jibhHc")).getAttribute("class").contains("Sorry, only letters (a-z), numbers (0-9), and periods (.) are allowed.");

            driver.quit();	
        }
}

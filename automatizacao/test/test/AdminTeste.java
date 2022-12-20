package test;

import java.time.Duration;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminTeste {

    private WebDriver driver;
    private JavascriptExecutor jse;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/app/");  
        jse = (JavascriptExecutor) driver;
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void validarAcessoAdmin() { 
        login("11111111111", "1234");
        
        driver.findElement(By.id("gerenciar-acessos-link")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='collapseUtilities'][contains(@class, 'collapse show')]")));
        
        driver.findElement(By.id("admin-link")).click();

        assertEquals(driver.getTitle(), "Financeiro - UFF - Administradores");
    }
    
    @Test
    public void modalInclusaoAdmin() { 
        login("11111111111", "1234");
        
        driver.findElement(By.id("gerenciar-acessos-link")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='collapseUtilities'][contains(@class, 'collapse show')]")));
        
        driver.findElement(By.id("admin-link")).click();
        driver.findElement(By.cssSelector(".fa-4x")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='adminModal'][contains(@style, 'padding-right: 17px; display: block;')]")));
        
        assertEquals(driver.findElement(By.id("nomeAdmin")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("cpfInput")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("senhaAdmin")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("gravar")).isEnabled(), true);
        assertEquals(driver.findElement(By.cssSelector(".btn-secondary:nth-child(2)")).isEnabled(), true);
    }
    
    @Test
    public void adicionarAdmin() { 
        login("11111111111", "1234");
        
        driver.findElement(By.id("gerenciar-acessos-link")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='collapseUtilities'][contains(@class, 'collapse show')]")));
        
        driver.findElement(By.id("admin-link")).click();
        
        WebElement tbodyadmin = driver.findElement(By.id("t-body-admin"));
        Integer numberOfRows = Integer.parseInt(tbodyadmin.getAttribute("childElementCount"));
        
        driver.findElement(By.cssSelector(".fa-4x")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='adminModal'][contains(@style, 'padding-right: 17px; display: block;')]")));
        
        driver.findElement(By.id("nomeAdmin")).click();
        driver.findElement(By.id("nomeAdmin")).sendKeys("Teste");
        driver.findElement(By.id("cpfInput")).click();
        driver.findElement(By.id("cpfInput")).sendKeys("11111111113");
        driver.findElement(By.id("senhaAdmin")).click();
        driver.findElement(By.id("senhaAdmin")).sendKeys("1234");
        driver.findElement(By.id("gravar")).click();
        
        tbodyadmin = driver.findElement(By.id("t-body-admin"));
        
        if (driver.findElements(By.className("dataTables_empty")).size() > 0) 
            assertEquals(driver.findElement(By.className("dataTables_empty")).getText(), "Sem registros disponíveis");
        else
            assertEquals(numberOfRows + 1, Integer.parseInt(tbodyadmin.getAttribute("childElementCount")));
    }
    
    private void login(String cpf, String senha) {
        driver.findElement(By.id("acessar")).click();
        driver.findElement(By.id("cpf")).click();
        driver.findElement(By.id("cpf")).sendKeys(cpf);
        driver.findElement(By.id("senha")).click();
        driver.findElement(By.id("senha")).sendKeys(senha);
        driver.findElement(By.id("btnEntrar")).click();
    }
}

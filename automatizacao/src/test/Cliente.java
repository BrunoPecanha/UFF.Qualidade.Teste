package test;

import java.time.Duration;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cliente {

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
    public void realizarLoginSucesso() {
        login("12345678910", "1234");

        assertEquals(driver.getTitle(), "Financeiro - UFF - Principal");

        driver.quit();
    }
    
    @Test
    public void realizarLoginFalha() {
        login("12345678910", "1235");
        
        assertEquals(driver.findElement(By.id("retorno")).getText(), "Nome ou senha inválidos");
    }

    @Test
    public void realizarLoginSemNome() {
        login("", "1234");
        
        assertEquals(driver.findElement(By.id("btnEntrar")).isEnabled(), false);
    }
    
    @Test
    public void realizarLoginSemSenha() {
        login("12345678910", "");
        
        assertEquals(driver.findElement(By.id("btnEntrar")).isEnabled(), false);
    }
    
    @Test
    public void validarDadosUsuarioLogado() {
        String cpf = "12345678910"; //cpf do usuário [nome=Homem de Ferro, id=1]
        
        login(cpf, "1234");
        
        driver.findElement(By.id("userDropdown")).click();
        driver.findElement(By.id("perfil")).click();
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='perfilModal'][contains(@style, 'display: block')]")));
        
        assertEquals(driver.findElement(By.id("nomeValue")).getText(), "Homem de Ferro - Id: 1");
        assertEquals(driver.findElement(By.id("cpfValue")).getText(), cpf);
    }
    
    @Test
    public void encerrarSessão() {
        login("12345678910", "1234");
        
        driver.findElement(By.id("userDropdown")).click();
        driver.findElement(By.id("sair")).click();
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='logoutModal'][contains(@style, 'display: block')]")));
        driver.findElement(By.linkText("Sair")).click();
        
        assertEquals(driver.getTitle(), "Financeiro - UFF");
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

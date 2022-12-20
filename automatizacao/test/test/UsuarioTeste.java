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

public class UsuarioTeste {

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
    public void validarAcessoUsuario() { 
        login("11111111111", "1234");
        
        driver.findElement(By.id("gerenciar-acessos-link")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='collapseUtilities'][contains(@class, 'collapse show')]")));
        
        driver.findElement(By.id("usuario-link")).click();

        assertEquals(driver.getTitle(), "Financeiro - UFF - Usuários");
    }
    
    @Test
    public void modalInclusaoUsuario() { 
        login("11111111111", "1234");
        
        driver.findElement(By.id("gerenciar-acessos-link")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='collapseUtilities'][contains(@class, 'collapse show')]")));
        
        driver.findElement(By.id("usuario-link")).click();
        driver.findElement(By.cssSelector(".fa-4x")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usuarioModal'][contains(@style, 'display: block')]")));
        
        assertEquals(driver.findElement(By.id("nome")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("cpfInput")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("senha")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("suspenso")).isEnabled(), true);
    }
    
    @Test
    public void adicionarUsuario() { 
        login("11111111111", "1234");
        
        driver.findElement(By.id("gerenciar-acessos-link")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='collapseUtilities'][contains(@class, 'collapse show')]")));
        
        driver.findElement(By.id("usuario-link")).click();
        
        WebElement tbodyusuario = driver.findElement(By.id("t-body-usuario"));
        Integer numberOfRows = Integer.parseInt(tbodyusuario.getAttribute("childElementCount"));
        
        driver.findElement(By.cssSelector(".fa-4x")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usuarioModal'][contains(@style, 'display: block')]")));
        
        driver.findElement(By.id("nome")).click();
        driver.findElement(By.id("nome")).sendKeys("Teste");
        driver.findElement(By.id("cpfInput")).click();
        driver.findElement(By.id("cpfInput")).sendKeys("11111111112");
        driver.findElement(By.id("senha")).click();
        driver.findElement(By.id("senha")).sendKeys("1234");
        driver.findElement(By.id("gravar")).click();
        
        tbodyusuario = driver.findElement(By.id("t-body-usuario"));
        
        if (driver.findElements(By.className("dataTables_empty")).size() > 0) 
            assertEquals(driver.findElement(By.className("dataTables_empty")).getText(), "Sem registros disponíveis");
        else
            assertEquals(numberOfRows + 1, Integer.parseInt(tbodyusuario.getAttribute("childElementCount")));
    }
    
    @Test
    public void suspenderUsuario() { 
        login("11111111111", "1234");
        
        driver.findElement(By.id("gerenciar-acessos-link")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='collapseUtilities'][contains(@class, 'collapse show')]")));
        
        driver.findElement(By.id("usuario-link")).click();
        
        driver.findElement(By.cssSelector(".fa-1x")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usuarioModal'][contains(@style, 'display: block')]")));
        
        WebElement suspensoCheckbox = driver.findElement(By.id("suspenso"));
        if (!suspensoCheckbox.isSelected()) suspensoCheckbox.click();
        
        driver.findElement(By.id("gravar")).click();
        
        driver.findElement(By.id("userDropdown")).click();
        driver.findElement(By.id("sair")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='logoutModal'][contains(@style, 'display: block')]")));
        
        driver.findElement(By.id("confirm-sair")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.titleIs("Financeiro - UFF"));
        
        driver.findElement(By.id("cpf")).click();
        driver.findElement(By.id("cpf")).sendKeys("12345678910");
        driver.findElement(By.id("senha")).click();
        driver.findElement(By.id("senha")).sendKeys("1234");
        driver.findElement(By.id("btnEntrar")).click();
        
        assertEquals(driver.findElement(By.id("retorno")).getText(), "Usuário Suspenso. Contacte o admin");
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

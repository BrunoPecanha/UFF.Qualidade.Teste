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

public class LancamentoTeste {

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
    public void validarAcessoLancamentos() { 
        login("12345678910", "1234");
        
        driver.findElement(By.cssSelector("#menuusuario > .nav-item:nth-child(3) span")).click();
        
        assertEquals(driver.getTitle(), "Financeiro - UFF - Extrato");
    }
    
    @Test
    public void modalInclusaoLancamento() { 
        login("12345678910", "1234");
        
        driver.findElement(By.linkText("Lançamentos")).click();
        driver.findElement(By.cssSelector(".fa-4x")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='lancamentoModal'][contains(@style, 'display: block')]")));
        
        assertEquals(driver.findElement(By.id("valor")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("id_conta")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("id_categoria")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("debito")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("credito")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("descricao")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("gravar")).isEnabled(), true);
        assertEquals(driver.findElement(By.cssSelector(".btn-secondary:nth-child(2)")).isEnabled(), true);
    }
    
    @Test
    public void adicionarLancamentos() { 
        login("12345678910", "1234");
        
        driver.findElement(By.linkText("Lançamentos")).click();
        
        WebElement tbodylancamentos = driver.findElement(By.id("t-body-lancamentos"));
        Integer numberOfRows = Integer.parseInt(tbodylancamentos.getAttribute("childElementCount"));
        
        driver.findElement(By.cssSelector(".fa-4x")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='lancamentoModal'][contains(@style, 'display: block')]")));
        
        driver.findElement(By.id("valor")).click();
        driver.findElement(By.id("valor")).sendKeys("100");
        driver.findElement(By.id("id_conta")).findElement(By.xpath("//option[. = 'teste edicao']")).click();
        driver.findElement(By.id("id_categoria")).findElement(By.xpath("//option[. = 'Compras']")).click();
        driver.findElement(By.id("credito")).click();
        driver.findElement(By.id("descricao")).click();
        driver.findElement(By.id("descricao")).sendKeys("Teste");
        driver.findElement(By.id("gravar")).click();
        
        tbodylancamentos = driver.findElement(By.id("t-body-lancamentos"));
        
        if (driver.findElements(By.className("dataTables_empty")).size() > 0) 
            assertEquals(driver.findElement(By.className("dataTables_empty")).getText(), "Sem registros disponíveis");
        else
            assertEquals(numberOfRows + 1, Integer.parseInt(tbodylancamentos.getAttribute("childElementCount")));
    }
    
    @Test
    public void processarLancamentos() { 
        login("12345678910", "1234");
        
        driver.findElement(By.linkText("Lançamentos")).click();
        driver.findElement(By.cssSelector(".container-fluid .btn")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='processarModal'][contains(@style, 'display: block')]")));
        
        driver.findElement(By.id("processar")).click();
        
        assertEquals(driver.findElement(By.cssSelector(".odd > td:nth-child(8)")).getText(), "S");
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

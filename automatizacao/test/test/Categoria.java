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

public class Categoria {

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
    public void validarAcessoCategoria() { 
        login("11111111111", "1234");
        
        driver.findElement(By.cssSelector("#gerenciamentoadmin > .nav-item:nth-child(2) span")).click();

        assertEquals(driver.getTitle(), "Financeiro - UFF - Categorias");
    }
    
    @Test
    public void modalInclusaoCategoria() { 
        login("11111111111", "1234");
        
        driver.findElement(By.cssSelector("#gerenciamentoadmin > .nav-item:nth-child(2) span")).click();
        driver.findElement(By.cssSelector(".fa-4x")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='categoriaModal'][contains(@style, 'display: block')]")));
        
        assertEquals(driver.findElement(By.id("descricao")).isEnabled(), true);
    }
    
    @Test
    public void adicionarCategoria() { 
        login("11111111111", "1234");
        
        driver.findElement(By.cssSelector("#gerenciamentoadmin > .nav-item:nth-child(2) span")).click();
        
        WebElement tbodycategoria = driver.findElement(By.id("t-body-categoria"));
        Integer numberOfRows = Integer.parseInt(tbodycategoria.getAttribute("childElementCount"));
        
        driver.findElement(By.cssSelector(".fa-4x")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='categoriaModal'][contains(@style, 'display: block')]")));
        
        driver.findElement(By.id("descricao")).click();
        driver.findElement(By.cssSelector(".modal-footer:nth-child(2) > .btn-primary")).click();
        
        tbodycategoria = driver.findElement(By.id("t-body-categoria"));
        
//        if (driver.findElements(By.className("dataTables_empty")).size() > 0) 
//            assertEquals(driver.findElement(By.className("dataTables_empty")).getText(), "Sem registros disponíveis");
//        else
//            assertEquals(numberOfRows + 1, Integer.parseInt(tbodycategoria.getAttribute("childElementCount")));
    }
    
    @Test
    public void editarCategoria() { 
        login("11111111111", "1234");
        
        driver.findElement(By.cssSelector("#gerenciamentoadmin > .nav-item:nth-child(2) span")).click();
        driver.findElement(By.id("descricao")).click();
        driver.findElement(By.id("descricao")).sendKeys("Compras Teste");
        driver.findElement(By.cssSelector(".modal-footer:nth-child(2) > .btn-primary")).click();
        
        driver.findElement(By.cssSelector("#gerenciamentoadmin > .nav-item:nth-child(2) span")).click();
        
        driver.findElement(By.cssSelector(".odd:nth-child(1) .fa-edit")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='categoriaModal'][contains(@style, 'display: block')]")));
        
        driver.findElement(By.id("descricao")).click();
        driver.findElement(By.id("descricao")).sendKeys("Compras Teste");
        driver.findElement(By.cssSelector(".modal-footer:nth-child(2) > .btn-primary")).click();
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

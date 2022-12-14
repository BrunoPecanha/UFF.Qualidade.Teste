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

public class ContaCorrenteTeste {

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
    public void acessoContaCorrente() {
        login("12345678910", "1234");
        
        driver.findElement(By.id("btn-conta-corrente")).click();
        
        assertEquals(driver.getTitle(), "Financeiro - UFF - Conta Corrent");
    }
    
    @Test
    public void modalContaCorrente() {
        login("12345678910", "1234");
        
        driver.findElement(By.id("btn-conta-corrente")).click();
        
        driver.findElement(By.id("carregar-dados-edicao")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='contasModal'][contains(@style, 'display: block')]")));
        
        assertEquals(driver.findElement(By.id("bancos")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("agencia")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("cc")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("descricao")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("gravar")).isEnabled(), true);
        assertEquals(driver.findElement(By.id("cancelar-gravacao")).isEnabled(), true);
    }
    
    @Test
    public void adicionarContaCorrente() {
        login("12345678910", "1234");
        
        driver.findElement(By.id("btn-conta-corrente")).click();
        
        WebElement tbodyContas = driver.findElement(By.id("t-body-contas"));
        Integer numberOfRows = Integer.parseInt(tbodyContas.getAttribute("childElementCount"));
        
        driver.findElement(By.id("carregar-dados-edicao")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='contasModal'][contains(@style, 'display: block')]")));
        
        driver.findElement(By.id("bancos")).click();
        driver.findElement(By.id("bancos")).sendKeys("Ita??");
        
        driver.findElement(By.id("agencia")).click();
        driver.findElement(By.id("agencia")).sendKeys("0000");
        
        driver.findElement(By.id("cc")).click();
        driver.findElement(By.id("cc")).sendKeys("000000");
        
        driver.findElement(By.id("descricao")).click();
        driver.findElement(By.id("descricao")).sendKeys("Teste");
        
        driver.findElement(By.id("gravar")).click();
        
        tbodyContas = driver.findElement(By.id("t-body-contas"));
        
        if (driver.findElements(By.className("dataTables_empty")).size() > 0) 
            assertEquals(1, Integer.parseInt(tbodyContas.getAttribute("childElementCount")));
        else
            assertEquals(numberOfRows + 1, Integer.parseInt(tbodyContas.getAttribute("childElementCount")));
    }
    
    @Test
    public void editarContaCorrente() {
        login("12345678910", "1234");
        
        driver.findElement(By.id("btn-conta-corrente")).click();
        
        driver.findElement(By.id("edit-conta")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='contasModal'][contains(@style, 'display: block')]")));
        
        driver.findElement(By.id("descricao")).click();
        driver.findElement(By.id("descricao")).clear();
        driver.findElement(By.id("descricao")).sendKeys("teste edicao");
        
        driver.findElement(By.id("gravar")).click();
        
        assertEquals("teste edicao", driver.findElement(By.id("conta-descricao")).getText());
    }
    
    @Test
    public void deletarContaCorrenteComLancamento() {
        login("00011122233", "1234");   //CPF de conta que possui lan??amentos, portanto o bot??o excluir estar?? oculto
        
        driver.findElement(By.id("btn-conta-corrente")).click();
        
        assertEquals(driver.findElement(By.id("deletar")).getCssValue("display"), "none");
    }

    @Test
    public void deletarContaCorrenteSemLancamento() {
        login("12345678910", "1234");
        
        driver.findElement(By.id("btn-conta-corrente")).click();
        
        WebElement tbodyContas = driver.findElement(By.id("t-body-contas"));
        Integer numberOfRows = Integer.parseInt(tbodyContas.getAttribute("childElementCount"));
        
        driver.findElement(By.id("deletar")).click();
        
        new WebDriverWait(driver, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='exclusaoModal'][contains(@style, 'display: block')]")));
        
        driver.findElement(By.id("confirmar-delete")).click();
        
        tbodyContas = driver.findElement(By.id("t-body-contas"));
        
        if (driver.findElements(By.className("dataTables_empty")).size() > 0) 
            assertEquals(driver.findElement(By.className("dataTables_empty")).getText(), "Sem registros dispon??veis");
        else
            assertEquals(numberOfRows - 1, Integer.parseInt(tbodyContas.getAttribute("childElementCount")));
    }
    
    @Test
    public void paginacaoContaCorrente() { 
        login("09876543210", "1234");
        
        driver.findElement(By.id("btn-conta-corrente")).click();
        
        driver.findElement(By.linkText("Pr??xima")).click();
        
        WebElement tbodyContas = driver.findElement(By.id("t-body-contas"));
        Integer numberOfRows = Integer.parseInt(tbodyContas.getAttribute("childElementCount"));
        
        assert(numberOfRows > 0);
    }
    
    @Test
    public void mudarQuantidadeRegistrosContaCorrent() { 
        login("09876543210", "1234");
        
        driver.findElement(By.id("btn-conta-corrente")).click();
        
        driver.findElement(By.name("dataTable_length")).click();
        
        WebElement dropdown = driver.findElement(By.name("dataTable_length"));
        dropdown.findElement(By.xpath("//option[. = '25']")).click();
        
        WebElement tbodyContas = driver.findElement(By.id("t-body-contas"));
        Integer numberOfRows = Integer.parseInt(tbodyContas.getAttribute("childElementCount"));
        
        assertTrue(numberOfRows > 10 && numberOfRows <= 25);
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

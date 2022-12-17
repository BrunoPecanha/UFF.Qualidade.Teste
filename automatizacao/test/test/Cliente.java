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
        driver.findElement(By.id("bancos")).sendKeys("Itaú");
        
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
        
        assertEquals(driver.findElement(By.id("conta-descricao")).getText(), "teste edicao");
    }
    
    @Test
    public void deletarContaCorrenteComLancamento() {
        login("00011122233", "1234");   //CPF de conta que possui lançamentos, portanto o botão excluir estará oculto
        
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
            assertEquals(driver.findElement(By.className("dataTables_empty")).getText(), "Sem registros disponíveis");
        else
            assertEquals(numberOfRows - 1, Integer.parseInt(tbodyContas.getAttribute("childElementCount")));
    }
    
    @Test
    public void paginacaoContaCorrente() { 
        login("09876543210", "1234");
        
        driver.findElement(By.id("btn-conta-corrente")).click();
        
        driver.findElement(By.linkText("Próxima")).click();
        
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
        
        assert(numberOfRows > 10 && numberOfRows <= 25);
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

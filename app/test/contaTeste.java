
import mock.contaMock;
import model.Conta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class contaTeste {   
    
    private contaMock _contaMock;
    
    public contaTeste() {   
        _contaMock = new contaMock();
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("**--- Teste dos cenários executados  ---**");
    }
    
    @AfterClass
    public static void tearDownClass() {
         System.out.println("**--- Teste finalizados ---**");
    }
    
    @Before
    public void setUp() {       
    }
    
    @After
    public void tearDown() {
    }    
  
    @Test
    public void Alterar_Descricao_Para_Valor_Valido() {
        Conta conta = _contaMock.obterContaValida();
        conta.setDescricao("Bradesco");
        
        assertEquals(conta.getDescricao(), "Bradesco");
    }
    
    @Test
    public void Alterar_Descricao_Para_Valor_Invalido() {
        Conta conta = _contaMock.obterContaValida();
        conta.setDescricao("");
        
        assertNotEquals(conta.getDescricao(), "");
    }
}



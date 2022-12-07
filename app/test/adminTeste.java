import mock.adminMock;
import model.Administrador;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class adminTeste {   
    
    private adminMock _adminMock;
    
    public adminTeste() {   
        _adminMock = new adminMock();
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
    public void Alterar_CPF_Para_Valor_Invalido() {
        Administrador admin = _adminMock.obterAdminValido();
        admin.setCPF("123.123.12");
        
        assertNotEquals(admin.getCPF(), "123.123.12");
    }
    
    @Test
    public void Alterar_CPF_Para_Valor_Valido() {
       Administrador admin = _adminMock.obterAdminValido();
        admin.setCPF("853.383.897-20");
        
        assertEquals(admin.getCPF(), "853.383.897-20");
    }    
    
    @Test
    public void Alterar_Nome_Para_Valor_Valido() {
       Administrador admin = _adminMock.obterAdminValido();
       admin.setNome("Alterado");
        
        assertEquals(admin.getNome(), "Alterado");
    }
    
      @Test
    public void Alterar_Nome_Para_Valor_Invalido() {
       Administrador admin = _adminMock.obterAdminValido();
       admin.setNome("");
        
       assertEquals(admin.getNome(), "Peçanha");
    }
}



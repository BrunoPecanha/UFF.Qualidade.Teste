import mock.usuarioMock;
import model.Usuario;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class usuarioTeste {   
    
    private usuarioMock _usuarioMock;
    
    public usuarioTeste() {   
        _usuarioMock = new usuarioMock();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {        
        System.out.println("**--- Teste dos cenários executados  ---**");
    }
    
    @After
    public void tearDown() {
        System.out.println("**--- Teste finalizados ---**");
    }
    
    @Test
    public void Validar_Usuario_Suspenso() {
        Usuario usuario = _usuarioMock.obterUsuarioSuspenso();
        assertTrue(usuario.getSuspenso().equals("S"));
    }
    
    @Test
    public void Validar_Usuario_Nao_Suspenso() {
        Usuario usuario = _usuarioMock.obterUsuarioNaoSuspenso();
        assertTrue(usuario.getSuspenso().equals("N"));
    }
    
    @Test
    public void Alterar_CPF_Para_Valor_Invalido() {
        Usuario usuario = _usuarioMock.obterUsuarioValido();
        usuario.setCPF("123.123.12");
        
        assertNotEquals(usuario.getCPF(), "123.123.12");
    }
    
    @Test
    public void Alterar_CPF_Para_Valor_Valido() {
       Usuario usuario = _usuarioMock.obterUsuarioValido();
        usuario.setCPF("853.383.897-20");
        
        assertEquals(usuario.getCPF(), "853.383.897-20");
    }
}



import dto.LoginDto;
import mock.loginMock;
import model.Usuario;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class loginTeste {   
    
    private loginMock _loginMock  = null;
    
    public loginTeste() {   
        _loginMock = new loginMock();
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
    public void Login_Com_Senha_Em_Branco_E_Usuario_Preenchido() {
        LoginDto logDto = _loginMock.obterLoginComSenhaEmBrancoEUsuarioPreenchido();
        Boolean acessoValido = Usuario.ValidarParametros(logDto.getUsuario(), logDto.getSenha());
        
        assertFalse(acessoValido);
    }
    
    @Test
    public void Login_Com_Senha_Preechida_E_Usuario_Em_Branco() {
        LoginDto logDto = _loginMock.obterLoginComSenhaPreenchidaEUsuarioEmBranco();
        Boolean acessoValido = Usuario.ValidarParametros(logDto.getUsuario(), logDto.getSenha());
        
        assertFalse(acessoValido);
    }
    
    @Test
    public void Login_Com_Usuario_E_Senha_Validos(){
        LoginDto logDto = _loginMock.obterLoginESenhaValidos();
        Boolean acessoValido = Usuario.ValidarParametros(logDto.getUsuario(), logDto.getSenha());
        
        assertTrue(acessoValido);
    }
}



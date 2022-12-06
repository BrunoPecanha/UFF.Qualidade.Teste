package mock;

import dto.LoginDto;
import model.Usuario;

public class loginMock {
    
    public loginMock () {
    }    
    
    //Login
    
    public LoginDto obterLoginComSenhaEmBrancoEUsuarioPreenchido() {
        return new LoginDto("13676616766","");
    }
    
    public LoginDto obterLoginComSenhaPreenchidaEUsuarioEmBranco() {
        return new LoginDto("","1234");
    }
    
    public LoginDto obterLoginESenhaValidos() {
        return new LoginDto("13676616766","1234");
    }
    
    // Usuário
    
    public Usuario obterUsuarioSuspenso() {
        return new Usuario("Bruno", "13676616766", "1234", "S");
    }    
}

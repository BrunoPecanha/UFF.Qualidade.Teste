package mock;

import dto.LoginDto;

public class loginMock {
    
    public loginMock () {
    }    
    
    public LoginDto obterLoginComSenhaEmBrancoEUsuarioPreenchido() {
        return new LoginDto("13676616766","");
    }
    
    public LoginDto obterLoginComSenhaPreenchidaEUsuarioEmBranco() {
        return new LoginDto("","1234");
    }
}

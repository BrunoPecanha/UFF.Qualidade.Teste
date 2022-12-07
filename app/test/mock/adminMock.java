package mock;

import model.Administrador;

public class adminMock {
    
    public adminMock () {
    }    
        
    // Administrador
    
     public Administrador obterAdminValido() {
        return new Administrador("Peçanha", "136.766.167-66", "1234");
    }
}

package mock;

import model.Conta;

public class contaMock {
    
    public contaMock () {
    }    
        
    // Conta
    
    public Conta obterContaValida() {
        return new Conta(1, "Conta 1", "Bradesco", "0789", "145652");
    }
}

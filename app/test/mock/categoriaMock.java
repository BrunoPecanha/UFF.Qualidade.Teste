package mock;

import model.Categoria;

public class categoriaMock {
    
    public categoriaMock () {
    }    
        
    // Categoria
    
     public Categoria obterCategoriaValida() {
        return new Categoria("Mercado");
    }
}

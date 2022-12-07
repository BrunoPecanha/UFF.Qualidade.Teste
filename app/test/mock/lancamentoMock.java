package mock;

import java.util.ArrayList;
import java.util.List;
import model.Lancamento;

public class lancamentoMock {
    
    public lancamentoMock () {
    }    
        
    // Lançamento
    
    public List<Lancamento> obterLancamentosProcessados() {
        
        List<Lancamento> lancamentos = new ArrayList();  
        lancamentos.add(new Lancamento(123, 1, 12.00, "D", new java.sql.Date(System.currentTimeMillis()), "Gasolina"));
        lancamentos.add(new Lancamento(123, 2, 40.00, "D", new java.sql.Date(System.currentTimeMillis()), "Frutas"));
        lancamentos.add(new Lancamento(123, 3, 100.00, "D", new java.sql.Date(System.currentTimeMillis()), "Luz"));
        lancamentos.add(new Lancamento(123, 3, 80.00, "D", new java.sql.Date(System.currentTimeMillis()), "Água"));        
      
        return lancamentos;
    }
}

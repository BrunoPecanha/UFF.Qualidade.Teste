package mock;

import java.util.ArrayList;
import java.util.List;
import model.Lancamento;

public class lancamentoMock {
    
    public lancamentoMock () {
    }    
        
    // Lançamento    
    public List<Lancamento> obterLancamentos() {
        
        List<Lancamento> lancamentos = new ArrayList();  
        lancamentos.add(new Lancamento(123, 1, 12.00, "D", new java.sql.Date(System.currentTimeMillis()), "Gasolina"));
        lancamentos.add(new Lancamento(123, 2, 40.00, "D", new java.sql.Date(System.currentTimeMillis()), "Frutas"));
        lancamentos.add(new Lancamento(123, 3, 100.00, "D", new java.sql.Date(System.currentTimeMillis()), "Luz"));
        lancamentos.add(new Lancamento(123, 3, 80.00, "D", new java.sql.Date(System.currentTimeMillis()), "Água"));        
      
        return lancamentos;
    }
    
    // Lançamento    
    public List<Lancamento> obterLancamentosProcessados() {
        
        List<Lancamento> lancamentos = new ArrayList();  
        
        lancamentos.add(new Lancamento(123, 1, 1, 12.00, "C", new java.sql.Date(System.currentTimeMillis()), "Gasolina", "S"));
        lancamentos.add(new Lancamento(123, 2, 1, 40.00, "C", new java.sql.Date(System.currentTimeMillis()), "Frutas", "S"));
        lancamentos.add(new Lancamento(123, 3, 1, 100.00, "D", new java.sql.Date(System.currentTimeMillis()), "Luz", "S"));
        lancamentos.add(new Lancamento(123, 3, 1, 80.00, "D", new java.sql.Date(System.currentTimeMillis()), "Água", "S"));        
          lancamentos.add(new Lancamento(123, 3, 1, 52.00, "D", new java.sql.Date(System.currentTimeMillis()), "Comprss", "S"));    
      
        return lancamentos;
    }
}

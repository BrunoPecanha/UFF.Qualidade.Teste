
import dto.ResumoDto;
import java.util.List;
import mock.lancamentoMock;
import model.Lancamento;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import servicos.LancamentoService;
import org.junit.Test;

public class lancamentoTeste {   
    

    private LancamentoService _lancService;    
    private lancamentoMock _lancamentoMock;
    
    
    public lancamentoTeste() {   
        _lancamentoMock = new lancamentoMock();
        _lancService = new LancamentoService();
        
    }
    
    @BeforeClass
    public static void setUpClass() {        
        System.out.println("**--- Teste dos cenários executados  ---**");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {  
        
    }
    
    @After
    public void tearDown() {
    }    
      
    @Test
    public void Validar_Todos_Lancamentos_Processados() {
             List<Lancamento> lancamentos = _lancamentoMock.obterLancamentos();

             _lancService.GerarBaixaLancamento(lancamentos);
             
             for (int i = 0; i < lancamentos.size(); i++) {
                assertEquals("S", lancamentos.get(i).getProcessado());
            }
    }   
    
    @Test
    public void Validar_Resumo_Total() {
             List<Lancamento> lancamentos = _lancamentoMock.obterLancamentosProcessados();

             ResumoDto resumo = _lancService.ProcessarLancamentos(lancamentos);
               
             assertEquals(232.00, resumo.debitos, 0.01);
            assertEquals(52.00, resumo.creditos, 0.01);
    }      
}



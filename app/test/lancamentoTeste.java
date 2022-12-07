
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
import static org.mockito.Mockito.when;


public class lancamentoTeste {   
    
    private lancamentoMock _lancamentoMock;
    private LancamentoService _lancService;
    
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
         System.out.println("**--- Teste finalizados ---**");
    }
    
    @Before
    public void setUp() {       
    }
    
    @After
    public void tearDown() {
    }    
  
    @Test
    public void Processar_Lancamentos() {
        List<Lancamento> lancamentos = _lancamentoMock.obterLancamentosProcessados();
        _lancService.ProcessarLancamentos(lancamentos);
        
        for (int i = 0; i < lancamentos.size(); i++) {
            assertEquals("S", lancamentos.get(i).getProcessado());
        }
    }
    
    @Test
	void testCalc() {
		 List<Lancamento> lancamentos = _lancamentoMock.obterLancamentosProcessados();
                _lancService.ProcessarLancamentos(lancamentos);

	//	AddService addService;
	//	CalcService calcService;

		//addService = Mockito.mock(AddService.class);
		//calcService = new CalcService(addService);

		
		String expected = "S";

		when(lancamentos.get(1).getProcessado()).thenReturn("N");

		String actual = "S";

		assertEquals(expected, actual);

	}    
    
}



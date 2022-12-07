
import mock.categoriaMock;
import model.Categoria;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class categoriaTeste {   
    
    private categoriaMock _categoriaMock;
    
    public categoriaTeste() {   
        _categoriaMock = new categoriaMock();
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
    public void Alterar_Descricao_Para_Valor_Valido() {
        Categoria categoria = _categoriaMock.obterCategoriaValida();
        categoria.setDescricao("Combustível");
        
        assertEquals(categoria.getDescricao(), "Combustível");
    }
    
    @Test
    public void Alterar_Descricao_Para_Valor_Invalido() {
        Categoria categoria = _categoriaMock.obterCategoriaValida();
        categoria.setDescricao("");
        
        assertNotEquals(categoria.getDescricao(), "");
    }
}



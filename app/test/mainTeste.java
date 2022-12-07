import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class mainTeste {   
    
    public mainTeste() {           
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
}



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class loginTeste {   
    
    public loginTeste() {        
    }
    
    @BeforeClass
    public static void setUpClass() {
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
    
    //Método de teste base
    @Test
    public void soma() {
        System.out.println("**--- Teste de soma executed ---**");

        int num1 = 11;
        int num2 = 12;
        int expected = 23;
        
        int actual = num1 + num2;

        assertEquals(expected, actual);
    }
}

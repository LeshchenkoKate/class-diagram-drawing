package src;

import org.junit.*;

import static org.junit.Assert.*;

public class Tests {

	   private DBConection dBConection;
	   
	   @Before
	    public void initTest() throws ClassNotFoundException {
		   dBConection = new DBConection();
	    }
	   
	   @After
	    public void afterTest() {
		   dBConection = null;
	    }
	   
	   @Test
	    public void addAttribute() throws Exception {
		   dBConection = new DBConection();
	        assertEquals("private String mass;" , dBConection.addAttribute("mass", "String", "private"));
	    }
	   
	   @Test
	    public void addAttribute2() throws Exception {
	        assertEquals("private String Null;" , dBConection.addAttribute("Null", "String", "private"));
	    }
	   
	   @Test
	    public void addMethod1() throws Exception {
	        assertEquals("private Method1();" , dBConection.addMethod("Method1", "private"));
	    }
	   
	   @Test
	    public void addMethod2() throws Exception {
	        assertEquals("private Method2();" , dBConection.addMethod("Method2", "private"));
	    }
	
}

package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GeldbetragTest {
	
    @Test
    public void testeKonstruktoren()
    {
        Geldbetrag gb = new Geldbetrag(1054);
        Geldbetrag gb2 = new Geldbetrag(1000);
        
        Geldbetrag gbS = new Geldbetrag("10");
        Geldbetrag gbS2 = new Geldbetrag("1");
        Geldbetrag gbS3 = new Geldbetrag("1,5");
        Geldbetrag gbS4 = new Geldbetrag("13,54");
        Geldbetrag gbS5 = new Geldbetrag(-1524);
        
        assertEquals(-1524, gbS5.getEuroCentInt());
        
        
        assertEquals("10,54", gb.getEuroCentString());
        assertEquals(1054, gb.getEuroCentInt());
        
        assertEquals("10,00", gb2.getEuroCentString());
        assertEquals(1000, gb2.getEuroCentInt());
        
        assertEquals("10,00", gbS.getEuroCentString());
        assertEquals(1000, gbS.getEuroCentInt());  
        
        assertEquals("1,00", gbS2.getEuroCentString());
        assertEquals(100, gbS2.getEuroCentInt());  
        
        assertEquals("1,50", gbS3.getEuroCentString());
        assertEquals(150, gbS3.getEuroCentInt());  
        
        assertEquals("13,54", gbS4.getEuroCentString());
        assertEquals(1354, gbS4.getEuroCentInt());  
    }
    
    @Test
    public void testeSubtract() 
    {
    	Geldbetrag gb = new Geldbetrag(1034);
    	assertEquals(34, gb.subtract(1000).getEuroCentInt());
    	
    	Geldbetrag gb2 = new Geldbetrag("15,00");
    	assertEquals(466, gb2.subtract("10,34").getEuroCentInt());
    	
    	Geldbetrag gb3 = new Geldbetrag("2,34");
    	Geldbetrag gb4 = new Geldbetrag("5");
    	assertEquals(-266, gb3.subtract(gb4).getEuroCentInt());
    }
    
    @Test
    public void testeGetString() 
    {
        Geldbetrag gb = new Geldbetrag(-344);
        assertEquals("-3,44",gb.getEuroCentString());
    }
}

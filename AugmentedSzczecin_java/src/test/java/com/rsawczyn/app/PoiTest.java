package com.rsawczyn.app;

/*import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;*/
import org.junit.Assert;
import org.junit.Test;
/**
 * Unit test for simple App.
 */
public class PoiTest
{

    @Test
    public final void getIdandisEqualIdTest() {
    	final Poi p1 = new Poi(1, "Test");
    	final Poi p2 = new Poi(2, "Test2");
    	
    	Assert.assertEquals(1, p1.getId());
    	Assert.assertEquals(2, p2.getId());
    	
    }
    
    public final void getNameTest() {
    	final Poi p1 = new Poi(1, "Test");
    	final Poi p2 = new Poi(2, "Test2");
    	
    	Assert.assertEquals("Test", p1.getName());
    	Assert.assertEquals("Test2", p2.getName());
    }
    
    public final void setNameTest() {
    	Poi p1 = new Poi(1, "Test");
    	p1.setName("ChangedName");
    	Assert.assertEquals("ChangedName", p1.getName());
    }
 
}

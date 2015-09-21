package com.runtime.listener;
 
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
 
@Listeners({com.runtime.listener.AssertionListener.class})
public class TestAssert {  
    @Test
    public void testAssert1(){ 
        Assertion.assertEquals(3, 3, "比较两个数是否相等：");
        Assertion.assertEquals(2, 2, "比较两个数是否相等：");    
    }
     
    @Test
    public void testAssert2(){ 
        Assertion.assertEquals(4, 3, "比较两个数是否相等：");
        Assertion.assertEquals(2, 2, "比较两个数是否相等：");    
    }
 
}
package com.testng.assertion;
 
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.allinmd.util.Assertion;
 
@Listeners({com.allinmd.util.AssertionListener.class})
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
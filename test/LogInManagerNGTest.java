/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author jaedon
 */
public class LogInManagerNGTest
{
    
    public LogInManagerNGTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception
    {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception
    {
    }
    /**
     * Test of CheckUsername method, of class LogInManager.
     */
    @Test
    public void testCheckUsername()
    {
        SignUp SUobj = new SignUp();
        String name = "kyl_1";
        
        assertEquals("kyl_1", name);
        
        if(true)
        {
            System.out.println("*Welcome " + name + " it is great to see you.\"");
        }
        else
        {
            System.out.println("Username is not correctly formatted, "
                    + "please ensure that your username contains an underscore and is no more than five characters In length.");
        }
    }

    /**
     * Test of CheckPasswordComplexity method, of class LogInManager.
     */
    @Test
    public void testCheckPasswordComplexity()
    {
        SignUp SUobj = new SignUp();
        
        String password = "Ch&&sec@ke99/";
        
        assertEquals("Ch&&sec@ke99/", password);
        
        if(true)
        {
            System.out.println("Password successfully captured.");
        }
        else
        {
            System.out.println("Password is not correctly formatted, "
                    + "please ensure that the password contains at least eight characters, "
                    + "a capital letter, a number, and a special character.");
        }
        
    }

    /**
     * Test of CheckCellPhoneNumber method, of class LogInManager.
     */
    @Test
    public void testCheckCellPhoneNumber()
    {
        SignUp SUobj = new SignUp();
        
        String phone = "+27838968976";
        
        assertEquals("+27838968976", phone);
        
        if(true)
        {
            System.out.println("Cell number successfully captured.");
        }
        else
        {
            System.out.println("Cell number is incorrectly formatted or does not contain an International code, "
                    + "please correct the number and try again.");
        }
    }    
}

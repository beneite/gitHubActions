package testModule;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class SanityTestClass {

    @BeforeTest
    public void logTestStart() {
        System.out.println("************ Starting Sanity test Suite ************ ");
    }

    @AfterTest
    public void logTestStop() {
        System.out.println("************ Completing Sanity test Suite ************ ");
    }

    @BeforeMethod
    public void logTestMethodStart(Method method) {
        System.out.println("=====> Starting Sanity test case: " + method.getName());
    }

    @Test(groups = {"sanity"})
    public void sanityTest01(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"sanity"})
    public void sanity02(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"sanity"})
    public void sanityTest03(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

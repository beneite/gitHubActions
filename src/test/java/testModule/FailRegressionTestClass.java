package testModule;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class FailRegressionTestClass {

    @BeforeTest
    public void logTestStart() {
        System.out.println("************ Starting Regression test Suite ************ ");
    }

    @AfterTest
    public void logTestStop() {
        System.out.println("************ Completing Regression test Suite ************ ");
    }

    @BeforeMethod
    public void logTestMethodStart(Method method) {
        System.out.println("=====> Starting Regression test case: " + method.getName());
    }

    @Test(groups = {"regression"})
    public void regressionTest01(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"regression"})
    public void regressionTest02(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"regression"})
    public void regressionTest03(){
        Assert.assertEquals(1, 2);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"regression"})
    public void regressionTest04(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"regression"})
    public void regressionTest05(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"regression"})
    public void regressionTest06(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"regression"})
    public void regressionTest07(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

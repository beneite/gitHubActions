/**
 * this class has few sample/mock test methods which will run
 */
package testModule;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class SmokeTestClass {

    @BeforeTest
    public void logTestStart() {
        System.out.println("************ Starting Smoke test Suite ************ ");
    }

    @AfterTest
    public void logTestStop() {
        System.out.println("************ Completing Smoke test Suite ************ ");
    }

    @BeforeMethod
    public void logTestMethodStart(Method method) {
        System.out.println("=====> Starting Smoke test case: " + method.getName());
    }

    @Test(groups = {"smoke"})
    public void smokeTest01(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"smoke"})
    public void smokeTest02(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"smoke"})
    public void smokeTest03(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"smoke"})
    public void smokeTest04(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups = {"smoke"})
    public void smokeTest05(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

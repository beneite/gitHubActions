/**
 * this class has few sample/mock test methods which will run
 */
package testModule;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class SmokeTestClass {

    @BeforeMethod
    public void logTestStart(Method method) {
        System.out.println("=====> Starting test: " + method.getName());
    }

    @Test
    public void smokeTestOne(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void smokeTestTwo(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void smokeTestThree(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void smokeTestFour(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void smokeTestFive(){
        Assert.assertEquals(1, 1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

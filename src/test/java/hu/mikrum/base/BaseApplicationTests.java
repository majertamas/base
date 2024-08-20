package hu.mikrum.base;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
public class BaseApplicationTests {

    @Test
    public void testMyService() {
        Assert.assertEquals("expectedResult", "expectedResult");
    }

}

package hu.mikrum.base;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
class BaseApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("works");
		Assert.assertEquals(1,1);
	}

}

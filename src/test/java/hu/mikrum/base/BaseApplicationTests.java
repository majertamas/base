package hu.mikrum.base;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseApplicationTests extends AbstractTransactionalTestNGSpringContextTests {

    private AutoCloseable closeable;

    @Autowired
    private MockMvc mockMvc;

    @BeforeMethod
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void close() throws Exception {
        closeable.close();
    }

}

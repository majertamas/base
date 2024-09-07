package hu.mikrum.base;

import hu.mikrum.base.repository.AddressRepository;
import hu.mikrum.base.repository.CustomerRepository;
import hu.mikrum.base.repository.IncomeRepository;
import hu.mikrum.base.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@SpringBootTest
@AutoConfigureMockMvc
//@ActiveProfiles("test")
@Transactional
@Rollback(value = false)
public abstract class BaseApplicationTests extends AbstractTransactionalTestNGSpringContextTests {

    private AutoCloseable closeable;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected LoanRepository loanRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected AddressRepository addressRepository;

    @Autowired
    protected IncomeRepository incomeRepository;

    @BeforeMethod
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void close() throws Exception {
        loanRepository.deleteAll();
        customerRepository.deleteAll();
        addressRepository.deleteAll();
        incomeRepository.deleteAll();
        closeable.close();
    }

}

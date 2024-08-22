package hu.mikrum.base;

import hu.mikrum.base.model.entity.Address;
import hu.mikrum.base.model.entity.Customer;
import hu.mikrum.base.model.entity.Income;
import hu.mikrum.base.model.entity.Loan;
import hu.mikrum.base.repository.AddressRepository;
import hu.mikrum.base.repository.CustomerRepository;
import hu.mikrum.base.repository.IncomeRepository;
import hu.mikrum.base.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestOfTest extends BaseApplicationTests {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Test
    public void testLoanCreationAndCustomerAssociations() {

        // 1. Hitel mentése
        Loan loan = new Loan();
        loan.setAmount(10000.0);
        loan = loanRepository.save(loan);

        // 2. Ügyfél hozzáadása
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer = customerRepository.save(customer);

        // 3. Ügyfélhez két cím hozzáadása
        Address address1 = new Address();
        address1.setStreet("123 Main St");
        address1.setCity("Springfield");
        address1.setPostalCode("11111");
        address1.setCustomer(customer);

        Address address2 = new Address();
        address2.setStreet("456 Elm St");
        address2.setCity("Springfield");
        address2.setPostalCode("22222");
        address2.setCustomer(customer);

        address1 = addressRepository.save(address1);
        address2 = addressRepository.save(address2);

        Set<Address> addresses = new HashSet<>();
        addresses.add(address1);
        addresses.add(address2);
        customer.setAddresses(addresses);

        // 4. Ügyfélhez jövedelmi adatok hozzáadása
        Income income1 = new Income();
        income1.setSource("Job");
        income1.setAmount(3000.0);
        income1.setCustomer(customer);

        Income income2 = new Income();
        income2.setSource("Freelancing");
        income2.setAmount(1500.0);
        income2.setCustomer(customer);

        income1 = incomeRepository.save(income1);
        income2 = incomeRepository.save(income2);

        Set<Income> incomes = new HashSet<>();
        incomes.add(income1);
        incomes.add(income2);
        customer.setIncomes(incomes);

        // Frissítjük a kapcsolatokat az ügyfélnél
        customer = customerRepository.save(customer);

        // Ügyfél hozzárendelése a hitelhez
        loan.setCustomer(customer);
        loan = loanRepository.save(loan);

        // Ellenőrizzük, hogy minden helyesen lett-e mentve
        List<Address> savedAddresses = (List<Address>) addressRepository.findAll();
        List<Income> savedIncomes = (List<Income>) incomeRepository.findAll();

        Assert.assertTrue(loanRepository.findById(loan.getId()).isPresent());
        Assert.assertTrue(customerRepository.findById(customer.getId()).isPresent());
        Assert.assertEquals(savedAddresses.size(), 2);
        Assert.assertEquals(savedIncomes.size(), 2);
    }


}

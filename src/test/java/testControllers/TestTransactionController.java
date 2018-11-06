package testControllers;

import com.bank.honest.Application;
import com.bank.honest.model.entity.Account;
import com.bank.honest.model.entity.Transaction;
import com.bank.honest.model.entity.enums.Currency;
import com.bank.honest.model.service.AccountService;
import com.bank.honest.model.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 5/6/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@TestPropertySource("classpath:test-application.properties")
public class TestTransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Before
    public void init(){
        Account account1 = Account.builder()
                .number("1")
                .amount(100L)
                .currency(Currency.UAH)
                .build();
        System.out.println("--------------------------------------------------------");
        System.out.println(account1);
        accountService.createAccount(account1);

        Account account2 = Account.builder()
                .number("2")
                .amount(50L)
                .currency(Currency.UAH)
                .build();
        System.out.println("--------------------------------------------------------");
        System.out.println(account2);
        accountService.createAccount(account2);

    }

    @Test
    public void TestTransaction(){

        Transaction transaction = Transaction.builder()
                .date(new Date())
                .number("001")
                .sourceName("1")
                .destinationName("2")
                .sum(100L)
                .currency(Currency.UAH)
                .build();
        transactionService.createTransaction(transaction);
        System.out.println("--------------------------------------------------------");
        System.out.println(accountService.findByNumber("1"));
        System.out.println(accountService.findByNumber("2"));
        System.out.println("--------------------------------------------------------");
        Long sum1 = accountService.findByNumber("1").getAmount();
        Long sum2 = accountService.findByNumber("2").getAmount();

        assertEquals(0, sum1.longValue() );
        assertEquals(150, sum2.longValue());

    }
}

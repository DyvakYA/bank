package com.bank.honest;

import com.bank.honest.model.entity.*;
import com.bank.honest.model.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        log.debug("--Application Started--");
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*");
            }
        };
    }

    @Bean
    public CommandLineRunner demo(final UserService userService,
                                  final ProductService productService,
                                  final TransactionService transactionService,
                                  final AccountService accountService,
                                  final WalletService walletService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                userService.createUser("0938412040", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.ADMIN);
                userService.createUser("user", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.USER);

                productService.createProduct(Currency.USD, 1000, 1200, "");
                productService.createProduct(Currency.UAH, 1200, 1600, "");
                productService.createProduct(Currency.EUR, 2, 3, "");
                productService.createProduct(Currency.BITCOIN, 100500, 100501, "");

                for (int i = 0; i < 10; i++) {
                    Account account = Account.builder()
                            .number("number" + i)
                            .amount(100500 + i)
                            .currency(Currency.USD)
                            .customUser(userService.findUser("0938412040"))
                            .build();
                    accountService.createAccount(account);
                }

                List<Account> list = accountService.findAll();
                for(Account account: list){
                    Wallet wallet = Wallet.builder()
                            .number("1234567890"+list.indexOf(account))
                            .status(WalletStatus.TRUE)
                            .account(account)
                            .expired("2050")
                            .name("myCard" + list.indexOf(account))
                            .build();
                    walletService.createWallet(wallet);
                }


                Account account = Account.builder()
                        .number("Special Account")
                        .amount(1012)
                        .currency(Currency.BITCOIN)
                        .customUser(userService.findUser("user"))
                        .build();
                accountService.createAccount(account);

                for(int i = 0; i < 10; i ++){
                    Transaction transaction = Transaction.builder()
                            .date(new Date())
                            .number("1215G-" + (1*12))
                            .sourceName("123")
                            .destinationName("321")
                            .currency(Currency.BITCOIN)
                            .sum((long) 12000)
                            .status(TransactionStatus.GOOD)
                            .build();
                    transactionService.createTransaction(transaction);
                }

            }
        };
    }


//    @Bean
//    public UrlBasedViewResolver setupViewResolver() {
//        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//        resolver.setPrefix("/WEB-INF/pages/");
//        resolver.setSuffix(".jsp");
//        resolver.setContentType("text/html;charset=UTF-8");
//        resolver.setViewClass(JstlView.class);
//
//        return resolver;
//    }

//    @Bean
//    public ResourceBundleMessageSource setupMessageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.addBasenames("messages");
//        return messageSource;
//    }
}
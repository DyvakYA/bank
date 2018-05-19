package com.bank.honest;

import com.bank.honest.model.entity.*;
import com.bank.honest.model.entity.enums.Currency;
import com.bank.honest.model.entity.enums.UserRole;
import com.bank.honest.model.entity.enums.WalletStatus;
import com.bank.honest.model.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST","PUT", "DELETE");
            }
        };
    }




    @Bean
    public CommandLineRunner demo(final UserService userService,
                                  final ProductService productService,
                                  final TransactionService transactionService,
                                  final AccountService accountService,
                                  final WalletService walletService,
                                  final ProfileService profileService) {
        return new CommandLineRunner() {

            @Override
            public void run(String... strings) throws Exception {

                Currency[] currencies = Currency.values();
                for (Currency currency : currencies) {
                    Product product = Product.builder()
                            .currency(currency)
                            .sellCourse(1)
                            .buyCourse(1)
                            .description("Currency - " + currency.toString())
                            .build();
                    productService.createProduct(product);
                }

                Profile adminProfile = Profile.builder()
                        .email("email@gmail.com")
                        .firstName("Admin")
                        .lastName("Admin")
                        .build();
                CustomUser admin = CustomUser.builder()
                        .phone("admin")
                        .password("admin")
                        .role(UserRole.ADMIN)
                        .profile(adminProfile)
                        .build();
                userService.createUser(admin);

                for (Currency currency : currencies) {
                    Account account = Account.builder()
                            .number("000001" + Arrays.asList(currencies).indexOf(currency))
                            .amount(10000L)
                            .currency(currency)
                            .isBlocked(false)
                            .customUser(admin)
                            .build();
                    accountService.createAccount(account);
                }

                List<Account> accounts = accountService.findAll();
                System.out.println(accounts);
                for (Account account : accounts) {
                    Wallet wallet = Wallet.builder()
                            .name("Wallet" + account.getNumber())
                            .number(account.getNumber() + Arrays.asList(accounts).indexOf(account))
                            .expired("2100")
                            .status(WalletStatus.TRUE)
                            .limit(100000L)
                            .isBlocked(false)
                            .smsInform(true)
                            .account(account)
                            .build();
                    System.out.println(wallet);
                    walletService.createWallet(wallet);
                }


            }
        };
    }
}
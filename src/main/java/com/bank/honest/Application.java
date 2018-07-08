package com.bank.honest;

import com.bank.honest.model.entity.*;
import com.bank.honest.model.entity.enums.Currency;
import com.bank.honest.model.entity.enums.UserRole;
import com.bank.honest.model.entity.enums.WalletStatus;
import com.bank.honest.model.entity.generator.NumberGeberatorUtil;
import com.bank.honest.model.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class Application {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);

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
                            .sellCourse(100 * currency.getMask())
                            .buyCourse(120 * currency.getMask())
                            .description("Currency - " + currency.toString())
                            .build();
                    productService.createProduct(product);
                }

                Profile adminProfile = Profile.builder()
                        .email("email@gmail.com")
                        .firstName("John")
                        .lastName("Dou")
                        .build();
                CustomUser admin = CustomUser.builder()
                        .phone("+38099 999-99-99")
                        .password("Qwerty12345")
                        .role(UserRole.ADMIN)
                        .profile(adminProfile)
                        .build();
                userService.createUser(admin);

                Profile userProfile = Profile.builder()
                        .email("email@gmail.com")
                        .firstName("John")
                        .lastName("Dou")
                        .build();
                CustomUser user = CustomUser.builder()
                        .phone("+38011 111-11-11")
                        .password("Qwerty12345")
                        .role(UserRole.USER)
                        .profile(userProfile)
                        .build();
                userService.createUser(user);

                List<CustomUser> users = userService.findAllUsers();

                for (CustomUser item : users) {
                    for (Currency currency : currencies) {
                        Account account = Account.builder()
                                .number(NumberGeberatorUtil.accountNumberGenerator())
                                .amount(10000L)
                                .currency(currency)
                                .isBlocked(false)
                                .customUser(item)
                                .build();
                        accountService.createAccount(account);
                    }
                }

                List<Account> accounts = accountService.findAll();
                log.info(accounts.toString());
                for (Account account : accounts) {
                    Wallet wallet = Wallet.builder()
                            .name(account.getNumber() + userService.findUserByAccount(account.getId()).getId())
                            .number(NumberGeberatorUtil.cardNumberGenerator())
                            .expired("21/12")
                            .status(WalletStatus.TRUE)
                            .limit(100000L)
                            .isBlocked(false)
                            .smsInform(true)
                            .account(account)
                            .build();
                    log.info(wallet.toString());
                    walletService.createWallet(wallet);
                }
            }
        };
    }
}
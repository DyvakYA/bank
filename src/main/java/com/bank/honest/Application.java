package com.bank.honest;

import com.bank.honest.model.entity.Account;
import com.bank.honest.model.entity.UserRole;
import com.bank.honest.model.service.AccountService;
import com.bank.honest.model.service.ProductService;
import com.bank.honest.model.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final UserService userService,
                                  final ProductService productService,
                                  final AccountService accountService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                userService.addUser("0938412040", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.ADMIN);
                userService.addUser("user", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.USER);

                productService.addProduct("USD", 1000, 1200, "");
                productService.addProduct("EUR", 1200, 1600, "");
                productService.addProduct("RUB", 2, 3, "");
                productService.addProduct("Bitcoin", 100500, 100501, "");

                for (int i = 0; i < 10; i++) {
                    Account account = Account.builder()
                            .number("number" + i)
                            .amount(100500 + i)
                            .customUser(userService.findUser("0938412040"))
                            .build();
                    accountService.createAccount(account);
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
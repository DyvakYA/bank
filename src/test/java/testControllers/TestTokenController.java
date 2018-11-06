package testControllers;

import com.bank.honest.Application;
import com.bank.honest.controller.TokenController;
import com.bank.honest.controller.UserController;
import com.bank.honest.exception.UserNotFoundException;
import com.bank.honest.security.JWTAuthenticateDTO;
import com.bank.honest.model.dto.UserDTO;
import com.bank.honest.model.entity.enums.UserRole;
import com.bank.honest.security.JWTValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {Application.class}
)
@TestPropertySource({"classpath:test-application.properties"})
public class TestTokenController {
    JWTAuthenticateDTO userFromBase;
    JWTAuthenticateDTO userNotFromBase;
    UserDTO dto;
    String token;
    @Autowired
    private TokenController controller;
    @Autowired
    private JWTValidator validator;
    @Autowired
    private UserController userController;

    public TestTokenController() {
    }

    @Before
    public void init() {
        dto = UserDTO.builder().phone("admin").password("admin").build();
        userController.create(dto);
        userFromBase = JWTAuthenticateDTO.builder().phone("admin").password("admin").build();
        userNotFromBase = JWTAuthenticateDTO.builder().phone("admin1").password("admin1").build();
        token = controller.generate(userFromBase);
    }

    @After
    public void after() {
        //userController.delete(userController.userByPhone(dto.getPhone()).getId());
    }

    @Test(expected = UserNotFoundException.class)
    void testUserNotFind() {
        controller.generate(userNotFromBase);
    }

    @Test
    void testCheckReturnedTokenUserRole() {
        Assert.assertEquals(UserRole.USER, validator.validate(token).getRole());
    }

    @Test
    void testCheckReturnedTokenUserId() {
        Assert.assertEquals(Long.valueOf(1L), validator.validate(token).getId());
    }
}

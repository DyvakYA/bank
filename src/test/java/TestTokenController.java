import com.bank.honest.Application;
import com.bank.honest.controller.TokenController;
import com.bank.honest.controller.UserController;
import com.bank.honest.exception.UserNotFoundException;
import com.bank.honest.model.dto.AuthenticateDTO;
import com.bank.honest.model.dto.UserDTO;
import com.bank.honest.model.entity.enums.UserRole;
import com.bank.honest.security.JWTValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 4/1/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@TestPropertySource("classpath:test-application.properties")
public class TestTokenController {

    AuthenticateDTO userFromBase;
    AuthenticateDTO userNotFromBase;
    UserDTO dto;
    String token;

    @Autowired
    private TokenController controller;

    @Autowired
    private JWTValidator validator;

    @Autowired
    private UserController userController;

    @Before
    public void init() {
        //create user and add to the base
        dto = UserDTO.builder()
                .phone("admin")
                .password("admin")
                .build();
        userController.create(dto);

        userFromBase = AuthenticateDTO.builder()
                .phone("admin")
                .password("admin")
                .build();

        userNotFromBase = AuthenticateDTO.builder()
                .phone("admin1")
                .password("admin1")
                .build();
        token = controller.generate(userFromBase);
    }

    @After
    public void after(){
        userController.delete(userController.userByPhone(dto.getPhone()).getId());
    }

    @Test(expected = UserNotFoundException.class)
    void testUserNotFind(){
        controller.generate(userNotFromBase);
    }

    @Test
    void testCheckReturnedTokenUserRole(){
        assertEquals(UserRole.USER, validator.validate(token).getRole());
    }

    @Test
    void testCheckReturnedTokenUserId(){
        assertEquals(Long.valueOf(1), validator.validate(token).getId());
    }

}

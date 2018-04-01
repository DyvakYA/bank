import com.bank.honest.Application;
import com.bank.honest.controller.TokenController;
import com.bank.honest.controller.UserController;
import com.bank.honest.exception.UserNotFoundException;
import com.bank.honest.model.dto.AuthenticateDTO;
import com.bank.honest.model.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by User on 4/1/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@TestPropertySource("classpath:application.properties")
public class TestTokenController {

    @Autowired
    private TokenController controller;

    @Autowired
    private UserController userController;

    @Before
    public void init() {
        UserDTO dto = UserDTO.builder()
                .phone("admin")
                .password("admin")
                .build();
        userController.create(dto);
    }

    @Test(expected = UserNotFoundException.class)
    public void testUserNotFind() throws Exception {
        AuthenticateDTO auth = AuthenticateDTO.builder()
                .phone("admin1")
                .password("admin1")
                .build();

        controller.generate(auth);
    }
}

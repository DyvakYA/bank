package testControllers;

import com.bank.honest.Application;
import com.bank.honest.controller.RegistrationController;
import com.bank.honest.controller.TokenController;
import com.bank.honest.controller.UserController;
import com.bank.honest.exception.UserAlreadyExistException;
import com.bank.honest.model.dto.RegistrationDTO;
import com.bank.honest.model.dto.UserDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertThat;

/**
 * Created by User on 4/1/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@TestPropertySource("classpath:test-application.properties")
public class TestUserController {

    UserDTO dto;

    RestTemplate restTemplate;

    @Autowired
    private UserController userController;

    @Autowired
    private RegistrationController registrationController;

    @Before
    public void init() {
        dto = UserDTO.builder()
                .phone("admin")
                .password("admin")
                .build();
        userController.create(dto);
    }

    @After
    public void after(){
        //userController.delete(userController.userByPhone(dto.getPhone()).getId());
    }

    @Test
    public void testAllUsers(){
        assertNotNull(userController.users(0));
    }

    @Test(expected = UserAlreadyExistException.class)
    public void testUserAlreadyExist(){
        RegistrationDTO reg = RegistrationDTO.builder()
                .phone("admin")
                .password("admin")
                .build();

        registrationController.registration(reg);
    }

    @Test
    public void shouldCreateAccount() {
        ResponseEntity<Map> responseEntity = restTemplate
                .postForEntity("http://localhost:8081/users", null, Map.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
        HttpHeaders headers = responseEntity.getHeaders();
        URI location = headers.getLocation();
        assertThat(location.toString(), startsWith("http://localhost:8081/users/"));
    }
}

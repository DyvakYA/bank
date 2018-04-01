import com.bank.honest.controller.TokenController;
import com.bank.honest.model.dto.AuthenticateDTO;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by User on 4/1/2018.
 */
public class TestTokenController {

    TokenController controller;

    @Before
    public void setUp() throws Exception{
        controller = new TokenController();
    }


    @Test
    public void testUserNotFind() throws Exception{
        AuthenticateDTO auth = AuthenticateDTO.builder()
                .phone("admin")
                .password("admin")
                .build();
        controller.generate(auth);

    }
}

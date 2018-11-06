package testControllers;

import com.bank.honest.exception.UserNotFoundException;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;


/**
 * Created by User on 6/10/2018.
 */
public class TestException {

    @Test

    public void testSomeExceptionAction(HttpServletResponse response){
        throw new UserNotFoundException("User not found");

        //assertThat(response.getStatus()).;
    }
}

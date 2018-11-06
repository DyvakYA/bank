import com.bank.honest.Application;
import com.bank.honest.controller.WalletController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by User on 5/9/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {Application.class})
@TestPropertySource({"classpath:test-application.properties"})
public class TestJsonSend {

    @Autowired
    WalletController controller;

    MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void jsonSendReg() throws Exception {

//
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//                "/students/Student1/courses/Course1").accept(
//                MediaType.APPLICATION_JSON);
//
//        System.out.println(mockMvc.perform(requestBuilder).andReturn());
    }

    @Test
    public void createWallet() throws Exception {
        this.mockMvc.perform(get("/wallets"))
                .andExpect(status().isOk())
                .andDo(print());
    }


}

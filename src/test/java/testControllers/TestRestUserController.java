package testControllers;

import com.bank.honest.Application;
import com.bank.honest.controller.UserController;
import com.bank.honest.model.service.UserService;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by User on 5/13/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {Application.class})
@TestPropertySource({"classpath:test-application.properties"})
public class TestRestUserController {

    @Autowired
    UserController controller;

    @Autowired
    UserService userService;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getAllUsers() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().encoding("UTF-8"))
                .andDo(print())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat(content).contains("email");
    }

    @Test
    public void createNewUser() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/users")
                .contentType(APPLICATION_JSON)
                .content("{\"phone\": \"user\",\"password\": \"user12345\"}")
                .accept(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        System.out.println(userService.findAll());
        String content = result.getResponse().getContentAsString();

    }
}

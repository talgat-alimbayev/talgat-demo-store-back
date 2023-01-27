package talgat.demo.store.back;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import talgat.demo.store.back.controllers.OrderController;
import talgat.demo.store.back.controllers.UserController;
import talgat.demo.store.back.models.User;
import talgat.demo.store.back.services.OrderService;
import talgat.demo.store.back.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes = UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    UserService userService;

    User user = new User();

    public UserControllerTest(){
        user.setId(1L);
        user.setUsername("talgat");
        user.setPassword("qwerty");
        user.setFullName("Talgat Alimbayev");
        user.setAddress("БЦ Алатау Гранд");
        user.setEmail("example@example.com");
        user.setRole("USER");
    }

    @Test
    public void findUserById_success() throws Exception {
        Mockito.when(userService.findById(user.getId())).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/api/users/find-by-id")
                        .param("userId", user.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.password").value(user.getPassword()))
                .andExpect(jsonPath("$.fullName").value(user.getFullName()))
                .andExpect(jsonPath("$.address").value(user.getAddress()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.role").value(user.getRole()));
    }
}

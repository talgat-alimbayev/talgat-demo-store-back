package talgat.demo.store.back;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import talgat.demo.store.back.controllers.OrderController;
import talgat.demo.store.back.models.ItemOrderDTO;
import talgat.demo.store.back.models.OrderCompleteDTO;
import talgat.demo.store.back.models.OrderDTO;
import talgat.demo.store.back.services.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
@ContextConfiguration(classes = OrderController.class)
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    OrderService orderService;
    OrderDTO orderDto1 = new OrderDTO();
    OrderDTO orderDto2 = new OrderDTO();
    OrderDTO orderDto3 = new OrderDTO();
    ItemOrderDTO itemStore1 = new ItemOrderDTO("кетчуп", new BigDecimal(789));
    ItemOrderDTO itemStore2 = new ItemOrderDTO("шпроты", new BigDecimal(456));
    ItemOrderDTO itemStore3 = new ItemOrderDTO("макароны", new BigDecimal(123));
    List<ItemOrderDTO> items1 = Arrays.asList(itemStore1, itemStore2, itemStore3);
    OrderCompleteDTO orderCompleteDTO = new OrderCompleteDTO();
    public OrderControllerTest(){
        orderDto1.setId(1L);
        orderDto1.setDeliveryAddress("Алматы, БЦ Алатау Гранд");
        orderDto1.setDeliveryName("Талгат");
        orderDto1.setEmail("example@example.com");
        orderDto1.setComment("some comment");
        orderDto1.setUserId(1L);

        orderDto2.setId(2L);
        orderDto2.setDeliveryAddress("Астана, БЦ Москва");
        orderDto2.setDeliveryName("Талгат");
        orderDto2.setEmail("exampleexample@example.com");
        orderDto2.setComment("one comment");
        orderDto2.setUserId(2L);

        orderDto3.setId(3L);
        orderDto3.setDeliveryAddress("Алматы, БЦ Алатау Гранд");
        orderDto3.setDeliveryName("Талгат");
        orderDto3.setEmail("example@example.com");
        orderDto3.setComment("some comment");
        orderDto3.setUserId(1L);

        orderCompleteDTO.setId(1L);
        orderCompleteDTO.setDeliveryAddress("Алматы, БЦ Алатау Гранд");
        orderCompleteDTO.setDeliveryName("Талгат");
        orderCompleteDTO.setEmail("example@example.com");
        orderCompleteDTO.setItems(items1);
        orderCompleteDTO.setComment("some comment");
        orderCompleteDTO.setUserId(1L);
    }

    @Test
    public void saveOrder_success() throws Exception {

        OrderDTO orderDTO = new OrderDTO(orderCompleteDTO);
        Mockito.when(orderService.saveOrder(orderCompleteDTO)).
                thenReturn(new ResponseEntity<>(orderDTO, HttpStatus.CREATED));

        mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/orders/save")
                .content(objectMapper.writeValueAsString(orderCompleteDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(orderDTO.getId()))
                .andExpect(jsonPath("$.deliveryAddress").value(orderDTO.getDeliveryAddress()))
                .andExpect(jsonPath("$.deliveryName").value(orderDTO.getDeliveryName()))
                .andExpect(jsonPath("$.email").value(orderDTO.getEmail()))
                .andExpect(jsonPath("$.comment").value(orderDTO.getComment()))
                .andExpect(jsonPath("$.userId").value(orderDTO.getUserId()));
    }

    @Test
    public void findAllOrders_success() throws Exception {
        List<OrderDTO> orderDtos = new ArrayList<>();
        orderDtos.add(orderDto1);
        orderDtos.add(orderDto2);

        Mockito.when(orderService.findAllOrders()).thenReturn(orderDtos);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/api/orders/find?all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(orderDto1.getId()))
                .andExpect(jsonPath("$[0].deliveryAddress").value(orderDto1.getDeliveryAddress()))
                .andExpect(jsonPath("$[0].deliveryName").value(orderDto1.getDeliveryName()))
                .andExpect(jsonPath("$[0].email").value(orderDto1.getEmail()))
                .andExpect(jsonPath("$[0].comment").value(orderDto1.getComment()))
                .andExpect(jsonPath("$[0].userId").value(orderDto1.getUserId()))
                .andExpect(jsonPath("$[1].id").value(orderDto2.getId()))
                .andExpect(jsonPath("$[1].deliveryAddress").value(orderDto2.getDeliveryAddress()))
                .andExpect(jsonPath("$[1].deliveryName").value(orderDto2.getDeliveryName()))
                .andExpect(jsonPath("$[1].email").value(orderDto2.getEmail()))
                .andExpect(jsonPath("$[1].comment").value(orderDto2.getComment()))
                .andExpect(jsonPath("$[1].userId").value(orderDto2.getUserId()));
    }

    @Test
    public void findOrdersByUserid_success() throws Exception {

        Mockito.when(orderService.findByUserId(orderDto1.getUserId())).
                thenReturn(Arrays.asList(orderDto1, orderDto3));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/api/orders/find-by-userid")
                        .param("userid", orderDto1.getUserId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(orderDto1.getId()))
                .andExpect(jsonPath("$[0].deliveryAddress").value(orderDto1.getDeliveryAddress()))
                .andExpect(jsonPath("$[0].deliveryName").value(orderDto1.getDeliveryName()))
                .andExpect(jsonPath("$[0].email").value(orderDto1.getEmail()))
                .andExpect(jsonPath("$[0].comment").value(orderDto1.getComment()))
                .andExpect(jsonPath("$[0].userId").value(orderDto1.getUserId()))
                .andExpect(jsonPath("$[1].id").value(orderDto3.getId()))
                .andExpect(jsonPath("$[1].deliveryAddress").value(orderDto3.getDeliveryAddress()))
                .andExpect(jsonPath("$[1].deliveryName").value(orderDto3.getDeliveryName()))
                .andExpect(jsonPath("$[1].email").value(orderDto3.getEmail()))
                .andExpect(jsonPath("$[1].comment").value(orderDto3.getComment()))
                .andExpect(jsonPath("$[1].userId").value(orderDto3.getUserId()));
    }
}

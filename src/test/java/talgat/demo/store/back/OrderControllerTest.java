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
import talgat.demo.store.back.models.ItemOrderDto;
import talgat.demo.store.back.models.OrderDto;
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
    ItemOrderDto itemStore1 = new ItemOrderDto(1L, "кетчуп", new BigDecimal(789));
    ItemOrderDto itemStore2 = new ItemOrderDto(2L, "шпроты", new BigDecimal(456));
    ItemOrderDto itemStore3 = new ItemOrderDto(3L, "макароны", new BigDecimal(123));
    List<ItemOrderDto> items1 = Arrays.asList(itemStore1, itemStore2, itemStore3);
    OrderDto orderDto1 = new OrderDto();

    ItemOrderDto itemStore4 = new ItemOrderDto(4L, "хлеб", new BigDecimal(111));
    ItemOrderDto itemStore5 = new ItemOrderDto(5L, "молоко", new BigDecimal(222));
    ItemOrderDto itemStore6 = new ItemOrderDto(6L, "кефир", new BigDecimal(333));
    List<ItemOrderDto> items2 = Arrays.asList(itemStore4, itemStore5, itemStore6);
    OrderDto orderDto2 = new OrderDto();
    OrderDto orderDto3 = new OrderDto();
    public OrderControllerTest(){
        orderDto1.setId(1L);
        orderDto1.setDeliveryAddress("Алматы, БЦ Алатау Гранд");
        orderDto1.setDeliveryName("Талгат");
        orderDto1.setEmail("example@example.com");
        orderDto1.setItems(items1);
        orderDto1.setComment("some comment");
        orderDto1.setUserId(1L);

        orderDto2.setId(2L);
        orderDto2.setDeliveryAddress("Астана, БЦ Москва");
        orderDto2.setDeliveryName("Талгат");
        orderDto2.setEmail("exampleexample@example.com");
        orderDto2.setItems(items2);
        orderDto2.setComment("one comment");
        orderDto2.setUserId(2L);

        orderDto3.setId(1L);
        orderDto3.setDeliveryAddress("Алматы, БЦ Алатау Гранд");
        orderDto3.setDeliveryName("Талгат");
        orderDto3.setEmail("example@example.com");
        orderDto3.setItems(items2);
        orderDto3.setComment("some comment");
        orderDto3.setUserId(1L);
    }

    @Test
    public void saveOrder_success() throws Exception {

        Mockito.when(orderService.saveOrder(orderDto1)).
                thenReturn(new ResponseEntity<>(orderDto1, HttpStatus.CREATED));

        mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/orders/save")
                .content(objectMapper.writeValueAsString(orderDto1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(orderDto1.getId()))
                .andExpect(jsonPath("$.deliveryAddress").value(orderDto1.getDeliveryAddress()))
                .andExpect(jsonPath("$.deliveryName").value(orderDto1.getDeliveryName()))
                .andExpect(jsonPath("$.email").value(orderDto1.getEmail()))
                .andExpect(jsonPath("$.items[0].id").value(orderDto1.getItems().get(0).getId()))
                .andExpect(jsonPath("$.items[0].name").value(orderDto1.getItems().get(0).getName()))
                .andExpect(jsonPath("$.items[0].price").value(orderDto1.getItems().get(0).getPrice()))
                .andExpect(jsonPath("$.items[1].id").value(orderDto1.getItems().get(1).getId()))
                .andExpect(jsonPath("$.items[1].name").value(orderDto1.getItems().get(1).getName()))
                .andExpect(jsonPath("$.items[1].price").value(orderDto1.getItems().get(1).getPrice()))
                .andExpect(jsonPath("$.items[2].id").value(orderDto1.getItems().get(2).getId()))
                .andExpect(jsonPath("$.items[2].name").value(orderDto1.getItems().get(2).getName()))
                .andExpect(jsonPath("$.items[2].price").value(orderDto1.getItems().get(2).getPrice()))
                .andExpect(jsonPath("$.comment").value(orderDto1.getComment()))
                .andExpect(jsonPath("$.userId").value(orderDto1.getUserId()));
    }

    @Test
    public void findAllOrders_success() throws Exception {
        List<OrderDto> orderDtos = new ArrayList<>();
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
                .andExpect(jsonPath("$[0].items[0].id").value(orderDto1.getItems().get(0).getId()))
                .andExpect(jsonPath("$[0].items[0].name").value(orderDto1.getItems().get(0).getName()))
                .andExpect(jsonPath("$[0].items[0].price").value(orderDto1.getItems().get(0).getPrice()))
                .andExpect(jsonPath("$[0].items[1].id").value(orderDto1.getItems().get(1).getId()))
                .andExpect(jsonPath("$[0].items[1].name").value(orderDto1.getItems().get(1).getName()))
                .andExpect(jsonPath("$[0].items[1].price").value(orderDto1.getItems().get(1).getPrice()))
                .andExpect(jsonPath("$[0].items[2].id").value(orderDto1.getItems().get(2).getId()))
                .andExpect(jsonPath("$[0].items[2].name").value(orderDto1.getItems().get(2).getName()))
                .andExpect(jsonPath("$[0].items[2].price").value(orderDto1.getItems().get(2).getPrice()))
                .andExpect(jsonPath("$[0].comment").value(orderDto1.getComment()))
                .andExpect(jsonPath("$[0].userId").value(orderDto1.getUserId()))
                .andExpect(jsonPath("$[1].id").value(orderDto2.getId()))
                .andExpect(jsonPath("$[1].deliveryAddress").value(orderDto2.getDeliveryAddress()))
                .andExpect(jsonPath("$[1].deliveryName").value(orderDto2.getDeliveryName()))
                .andExpect(jsonPath("$[1].email").value(orderDto2.getEmail()))
                .andExpect(jsonPath("$[1].items[0].id").value(orderDto2.getItems().get(0).getId()))
                .andExpect(jsonPath("$[1].items[0].name").value(orderDto2.getItems().get(0).getName()))
                .andExpect(jsonPath("$[1].items[0].price").value(orderDto2.getItems().get(0).getPrice()))
                .andExpect(jsonPath("$[1].items[1].id").value(orderDto2.getItems().get(1).getId()))
                .andExpect(jsonPath("$[1].items[1].name").value(orderDto2.getItems().get(1).getName()))
                .andExpect(jsonPath("$[1].items[1].price").value(orderDto2.getItems().get(1).getPrice()))
                .andExpect(jsonPath("$[1].items[2].id").value(orderDto2.getItems().get(2).getId()))
                .andExpect(jsonPath("$[1].items[2].name").value(orderDto2.getItems().get(2).getName()))
                .andExpect(jsonPath("$[1].items[2].price").value(orderDto2.getItems().get(2).getPrice()))
                .andExpect(jsonPath("$[1].comment").value(orderDto2.getComment()))
                .andExpect(jsonPath("$[1].userId").value(orderDto2.getUserId()));
    }

    @Test
    public void findOrderByUserid_success() throws Exception {

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
                .andExpect(jsonPath("$[0].items[0].id").value(orderDto1.getItems().get(0).getId()))
                .andExpect(jsonPath("$[0].items[0].name").value(orderDto1.getItems().get(0).getName()))
                .andExpect(jsonPath("$[0].items[0].price").value(orderDto1.getItems().get(0).getPrice()))
                .andExpect(jsonPath("$[0].items[1].id").value(orderDto1.getItems().get(1).getId()))
                .andExpect(jsonPath("$[0].items[1].name").value(orderDto1.getItems().get(1).getName()))
                .andExpect(jsonPath("$[0].items[1].price").value(orderDto1.getItems().get(1).getPrice()))
                .andExpect(jsonPath("$[0].items[2].id").value(orderDto1.getItems().get(2).getId()))
                .andExpect(jsonPath("$[0].items[2].name").value(orderDto1.getItems().get(2).getName()))
                .andExpect(jsonPath("$[0].items[2].price").value(orderDto1.getItems().get(2).getPrice()))
                .andExpect(jsonPath("$[0].comment").value(orderDto1.getComment()))
                .andExpect(jsonPath("$[0].userId").value(orderDto1.getUserId()))
                .andExpect(jsonPath("$[1].id").value(orderDto3.getId()))
                .andExpect(jsonPath("$[1].deliveryAddress").value(orderDto3.getDeliveryAddress()))
                .andExpect(jsonPath("$[1].deliveryName").value(orderDto3.getDeliveryName()))
                .andExpect(jsonPath("$[1].email").value(orderDto3.getEmail()))
                .andExpect(jsonPath("$[1].items[0].id").value(orderDto3.getItems().get(0).getId()))
                .andExpect(jsonPath("$[1].items[0].name").value(orderDto3.getItems().get(0).getName()))
                .andExpect(jsonPath("$[1].items[0].price").value(orderDto3.getItems().get(0).getPrice()))
                .andExpect(jsonPath("$[1].items[1].id").value(orderDto3.getItems().get(1).getId()))
                .andExpect(jsonPath("$[1].items[1].name").value(orderDto3.getItems().get(1).getName()))
                .andExpect(jsonPath("$[1].items[1].price").value(orderDto3.getItems().get(1).getPrice()))
                .andExpect(jsonPath("$[1].items[2].id").value(orderDto3.getItems().get(2).getId()))
                .andExpect(jsonPath("$[1].items[2].name").value(orderDto3.getItems().get(2).getName()))
                .andExpect(jsonPath("$[1].items[2].price").value(orderDto3.getItems().get(2).getPrice()))
                .andExpect(jsonPath("$[1].comment").value(orderDto3.getComment()))
                .andExpect(jsonPath("$[1].userId").value(orderDto3.getUserId()));
    }
}

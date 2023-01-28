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
import talgat.demo.store.back.controllers.ItemOrderController;
import talgat.demo.store.back.controllers.ItemStoreController;
import talgat.demo.store.back.models.ItemOrderDto;
import talgat.demo.store.back.models.ItemStoreDto;
import talgat.demo.store.back.services.ItemOrderService;
import talgat.demo.store.back.services.ItemStoreService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ItemOrderController.class)
@ContextConfiguration(classes = ItemOrderController.class)
public class ItemOrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ItemOrderService itemOrderService;
    ItemOrderDto itemStore1 = new ItemOrderDto("кетчуп", new BigDecimal(789));
    ItemOrderDto itemStore2 = new ItemOrderDto("шпроты", new BigDecimal(456));
    ItemOrderDto itemStore3 = new ItemOrderDto("макароны", new BigDecimal(123));
    List<ItemOrderDto> items = Arrays.asList(itemStore1, itemStore2, itemStore3);
    Long orderId = 1L;
    @Test
    public void findAllItems_success() throws Exception {

        Mockito.when(itemOrderService.findItemsByOrder(orderId)).thenReturn(items);

        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/api/orders/items/find-by-orderid")
                .param("orderId", orderId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("кетчуп"))
                .andExpect(jsonPath("$[1].name").value("шпроты"))
                .andExpect(jsonPath("$[2].name").value("макароны"))
                .andExpect(jsonPath("$[0].price").value("789"))
                .andExpect(jsonPath("$[1].price").value("456"))
                .andExpect(jsonPath("$[2].price").value("123"));
    }
}

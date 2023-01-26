package talgat.demo.store.back;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import talgat.demo.store.back.controllers.ItemStoreController;
import talgat.demo.store.back.models.ItemStore;
import talgat.demo.store.back.models.ItemStoreDto;
import talgat.demo.store.back.repositories.ItemStoreRepository;
import talgat.demo.store.back.services.ItemStoreService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ItemStoreController.class)
@ContextConfiguration(classes = ItemStoreController.class)
public class ItemStoreControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    ItemStoreService itemStoreService;
    ItemStoreDto itemStore1 = new ItemStoreDto(1L, "кетчуп", new BigDecimal(789));
    ItemStoreDto itemStore2 = new ItemStoreDto(2L, "шпроты", new BigDecimal(456));
    ItemStoreDto itemStore3 = new ItemStoreDto(3L, "макароны", new BigDecimal(123));
    List<ItemStoreDto> items = Arrays.asList(itemStore1, itemStore2, itemStore3);
    @Test
    public void findAllItems_success() throws Exception {

        Mockito.when(itemStoreService.findAllItems()).thenReturn(items);

        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/api/store/items/find?all")
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

    @Test
    public void findItemsByIds_success() throws Exception {
        List<Long> ids = new ArrayList<>();
        items.forEach(itemStoreDto -> {
            ids.add(itemStoreDto.getId());
        });
        String ids_string = ids.stream().map(Objects::toString).collect(Collectors.joining(","));
        Mockito.when(itemStoreService.findItemsByIds(ids)).thenReturn(items);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/api/store/items/find-by-ids")
                        .param("ids",ids_string)
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

    @Test
    public void findItemByName_success() throws Exception {
        String itemName = itemStore1.getName();
        Mockito.when(itemStoreService.findItemByName(itemName)).thenReturn(new ResponseEntity<>(itemStore1, HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/api/store/items/find-by-name")
                        .param("name",itemName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("кетчуп"))
                .andExpect(jsonPath("$.price").value("789"));
    }

    @Test
    public void findItemById_success() throws Exception {
        Long itemId = itemStore2.getId();
        Mockito.when(itemStoreService.findItemById(itemId)).thenReturn(new ResponseEntity<>(itemStore2, HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/api/store/items/find-by-id")
                        .param("id",itemId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("шпроты"))
                .andExpect(jsonPath("$.price").value("456"));
    }

    @Test
    public void createItem_success() throws Exception {
        Mockito.when(itemStoreService.createItem(itemStore3)).thenReturn(itemStore3);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/api/store/items/create")
                        .content(objectMapper.writeValueAsString(itemStore3))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}

package talgat.demo.store.back;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import talgat.demo.store.back.models.*;
import talgat.demo.store.back.repositories.ItemOrderRepository;
import talgat.demo.store.back.repositories.UserRepository;
import talgat.demo.store.back.services.ItemStoreService;
import talgat.demo.store.back.services.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TalgatDemoStoreBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalgatDemoStoreBackApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(OrderService orderService, ItemStoreService itemStoreService,
										ItemOrderRepository itemOrderRepo, UserRepository userRepo){
		return new CommandLineRunner() {
			@Override
//			@Transactional
			public void run(String... args) throws Exception {
				User user = new User();
				user.setUsername("talimbayev");
				user.setPassword("dgnlkfdnglknfdg");
				user.setFullName("talgat alimbayev");
				user.setAddress("Almaty");
				user.setEmail("alimbayev.talgat@gmail.com");
				user.setRole("USER");

				userRepo.save(user);
//
//				ItemStoreDto itemStoreDto = new ItemStoreDto();
//				itemStoreDto.setName("кефир");
//				itemStoreDto.setPrice(new BigDecimal(350));
////				log.info(itemStoreDto.toString());
//				itemStoreService.createItem(itemStoreDto);
//
//				ItemStoreDto itemStoreDto1 = itemStoreService.findItemById(1L).getBody();
//				ItemStoreDto itemStoreDto2 = itemStoreService.findItemById(5L).getBody();
//				ItemOrder itemOrder1 = new ItemOrder(itemStoreDto1);
//				ItemOrder itemOrder2 = new ItemOrder(itemStoreDto2);
//
//				ItemOrderDto itemOrderDto1 = new ItemOrderDto(itemOrder1);
//				ItemOrderDto itemOrderDto2 = new ItemOrderDto(itemOrder2);
//
//				Order order = new Order();
//				order.setDeliveryAddress("Алматы");
//				order.setDeliveryName("Талгат");
//				order.setEmail("alimbayev.talgat@gmail.com");
//				order.setUser(user);
//
//				OrderDto orderDto = new OrderDto(order);
//				List<ItemOrderDto> items = new ArrayList<>();
//				items.add(itemOrderDto1);
//				items.add(itemOrderDto2);
//				orderDto.setItems(items);
//				System.out.println(orderDto.toString());
//				orderService.saveOrder(orderDto);
//
				User user2 = new User();
				user2.setUsername("new random user");
				user2.setPassword("qwerty");
				user2.setFullName("danial");
				user2.setAddress("Astana");
				user2.setEmail("blah123@blah.com");
				user2.setRole("USER");

				userRepo.save(user2);
//
//				ItemStoreDto itemStoreDto3 = itemStoreService.findItemById(2L).getBody();
//				ItemStoreDto itemStoreDto4 = itemStoreService.findItemById(7L).getBody();
//				ItemOrder itemOrder3 = new ItemOrder(itemStoreDto3);
//				ItemOrder itemOrder4 = new ItemOrder(itemStoreDto4);
//
//				ItemOrderDto itemOrderDto3 = new ItemOrderDto(itemOrder3);
//				ItemOrderDto itemOrderDto4 = new ItemOrderDto(itemOrder4);
//
//				Order order1 = new Order();
//				order1.setDeliveryAddress("Астана");
//				order1.setDeliveryName("Даниал");
//				order1.setEmail("alimbayev.talgat@gmail.com");
//				order1.setUser(user2);
//
//				OrderDto orderDto2 = new OrderDto(order1);
//				List<ItemOrderDto> items2 = new ArrayList<>();
//				items2.add(itemOrderDto3);
//				items2.add(itemOrderDto4);
//				orderDto2.setItems(items2);
//				System.out.println(orderDto2.toString());
//				orderService.saveOrder(orderDto2);

//
			}
		};
	}
}

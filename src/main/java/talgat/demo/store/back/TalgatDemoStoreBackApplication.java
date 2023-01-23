package talgat.demo.store.back;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import talgat.demo.store.back.models.*;
import talgat.demo.store.back.repositories.ItemOrderRepository;
import talgat.demo.store.back.repositories.UserRepository;
import talgat.demo.store.back.services.ItemStoreService;
import talgat.demo.store.back.services.OrderService;

import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
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
				user.setEmail("blah@blah.com");
				user.setRole("USER");

				userRepo.save(user);

				ItemStoreDto itemStore = new ItemStoreDto();
				itemStore.setName("кефир");
				itemStore.setPrice(new BigDecimal(350));
				log.info(itemStore.toString());
				itemStoreService.createItem(itemStore);

				ItemStoreDto itemStoreDto1 = itemStoreService.findItemById(1L).getBody();
				ItemStoreDto itemStoreDto2 = itemStoreService.findItemById(2L).getBody();
				ItemOrder itemOrder1 = new ItemOrder(itemStoreDto1);
				ItemOrder itemOrder2 = new ItemOrder(itemStoreDto2);

				Order order = new Order();
				order.setDeliveryAddress("Алматы");
				order.setDeliveryName("Талгат");
				order.setEmail("blah@blah.com");
				order.setUser(user);
				OrderDto orderDto = new OrderDto(order);

				orderService.saveOrder(orderDto);
				itemOrder1.setOrder(order);
				itemOrder2.setOrder(order);
				itemOrderRepo.save(itemOrder1);
				itemOrderRepo.save(itemOrder2);
//				order.addItem(itemOrder1);
//				order.addItem(itemOrder2);

//
			}
		};
	}
}

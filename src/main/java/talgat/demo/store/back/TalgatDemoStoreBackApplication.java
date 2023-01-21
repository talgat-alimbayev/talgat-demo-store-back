package talgat.demo.store.back;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import talgat.demo.store.back.models.*;
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
	public CommandLineRunner dataLoader(OrderService orderService, ItemStoreService itemStoreService){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				ItemStore itemStore = new ItemStore();
				itemStore.setName("кефир");
				itemStore.setPrice(new BigDecimal(350));
				log.info(itemStore.toString());
				itemStoreService.createItem(itemStore);
				ItemStore itemStore1 = itemStoreService.findItemByIds(1L).get();
				log.info(itemStore1.getId().toString() + " " + itemStore1.getName());
				ItemStore itemStore2 = itemStoreService.findItemByIds(2L).get();
				log.info(itemStore2.toString());
				ItemStoreDto itemStoreDto1 = new ItemStoreDto(itemStore1);
				ItemStoreDto itemStoreDto2 = new ItemStoreDto(itemStore2);
				ItemOrder itemOrder1 = new ItemOrder(itemStoreDto1);
				ItemOrder itemOrder2 = new ItemOrder(itemStoreDto2);

				Order order = new Order();
				order.setDeliveryAddress("Алматы");
				order.setDeliveryName("Талгат");
				order.setEmail("blah@blah.com");
				order.addItem(itemOrder1);
				order.addItem(itemOrder2);

//				itemOrder1.setOrder(order);
//				itemOrder2.setOrder(order);
				orderService.saveOrder(order);
			}
		};
	}
}

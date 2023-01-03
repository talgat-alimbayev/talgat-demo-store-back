package talgat.demo.store.back;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import talgat.demo.store.back.models.Item;
import talgat.demo.store.back.models.Order;
import talgat.demo.store.back.repositories.ItemRepository;
import talgat.demo.store.back.repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class TalgatDemoStoreBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalgatDemoStoreBackApplication.class, args);
	}
	@Bean
    public ApplicationRunner dataLoader(ItemRepository itemRepo, OrderRepository orderRepo){
        return (ApplicationArguments s) ->{
            Order order1 = new Order();
            order1.setDeliveryAddress("Алматы, БЦ Алатау Гранд");
            order1.setDeliveryName("Талгат");
            order1.setEmail("fjdnkjfdnkjndg");
            order1.setOrderTotal(BigDecimal.ZERO);
            order1.setComment("hello");
            itemRepo.findByNameIn(Arrays.asList("сметана", "молоко", "йогурт", "хлеб")).
                    doOnNext(item -> {
                        log.info("printing within lambda");
                        log.info(item.toString());
                        order1.addItem(item);
                    }).
                    blockLast();
//            Item sourCream = itemRepo.findByName("сметана").block();
//            order1.addItem(sourCream);
            log.info("printing order1");
            log.info(order1.toString());
            orderRepo.save(order1).subscribe();
//            log.info("printing items");
//            itemRepo.findByNameIn(Arrays.asList("сметана", "хлеб")).doOnNext(item -> System.out.println(item.getId())).subscribe();


//			itemRepo.findByName("сметана").doOnEach(item -> {return sourCream.setId(item.getId());});
//			Order order1 = new Order("Алматы");
//			order1.addItem(sourCream);
//			order1.setItemIds(new HashSet<Long>(Arrays.asList());
//			orderRepo.sa
        };
    }
}

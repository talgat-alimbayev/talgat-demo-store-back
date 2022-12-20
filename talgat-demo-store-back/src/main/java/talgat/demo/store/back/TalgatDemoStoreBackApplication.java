package talgat.demo.store.back;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import talgat.demo.store.back.models.Item;
import talgat.demo.store.back.repositories.ItemRepository;

import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
public class TalgatDemoStoreBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalgatDemoStoreBackApplication.class, args);
	}
	@Bean
    public ApplicationRunner dataLoader(ItemRepository itemRepo){
        return (ApplicationArguments s) ->{
			log.info("printing items in the repo");
            itemRepo.findAll().doOnNext(item -> System.out.println(item)).subscribe();
//			itemRepo.findByName("сметана").doOnEach(item -> {return sourCream.setId(item.getId());});
//			Order order1 = new Order("Алматы");
//			order1.addItem(sourCream);
//			order1.setItemIds(new HashSet<Long>(Arrays.asList());
//			orderRepo.sa
        };
    }
}

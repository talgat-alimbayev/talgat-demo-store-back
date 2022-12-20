package talgat.demo.store.back.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import talgat.demo.store.back.models.Item;

public interface ItemRepository extends ReactiveCrudRepository<Item, Long> {
    Mono<Item> findByName(String name);
}

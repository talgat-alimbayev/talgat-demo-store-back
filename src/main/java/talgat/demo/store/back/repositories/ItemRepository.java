package talgat.demo.store.back.repositories;

import org.springframework.data.repository.CrudRepository;
import talgat.demo.store.back.models.Item;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findByName(String name);
    List<Item> findByNameIn(List<String> names);

}

package talgat.demo.store.back.repositories;

import org.springframework.data.repository.CrudRepository;
import talgat.demo.store.back.models.ItemStore;

import java.util.List;
import java.util.Optional;

public interface ItemStoreRepository extends CrudRepository<ItemStore, Long> {
    Optional<ItemStore> findByName(String name);
    List<ItemStore> findByNameIn(List<String> names);

}

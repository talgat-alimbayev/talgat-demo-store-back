package talgat.demo.store.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import talgat.demo.store.back.models.ItemStore;

import java.util.List;

public interface ItemStoreRepository extends CrudRepository<ItemStore, Long> {
    ItemStore findByName(String name);
    List<ItemStore> findByNameIn(List<String> names);

}

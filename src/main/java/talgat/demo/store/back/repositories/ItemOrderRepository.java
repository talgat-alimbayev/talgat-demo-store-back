package talgat.demo.store.back.repositories;

import org.springframework.data.repository.CrudRepository;
import talgat.demo.store.back.models.ItemOrder;

import java.util.List;

public interface ItemOrderRepository extends CrudRepository<ItemOrder, Long> {
    List<ItemOrder> findByOrderId(Long id);
}

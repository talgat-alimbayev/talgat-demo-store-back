package talgat.demo.store.back.repositories;

import org.springframework.data.repository.CrudRepository;
import talgat.demo.store.back.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Iterable<Order> findByUser(Long id);
}
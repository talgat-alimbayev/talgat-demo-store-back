package talgat.demo.store.back.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import talgat.demo.store.back.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}

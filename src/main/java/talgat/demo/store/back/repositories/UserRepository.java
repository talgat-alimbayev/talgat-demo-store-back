package talgat.demo.store.back.repositories;

import org.springframework.data.repository.CrudRepository;
import talgat.demo.store.back.models.ItemStore;
import talgat.demo.store.back.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByName(String name);
}

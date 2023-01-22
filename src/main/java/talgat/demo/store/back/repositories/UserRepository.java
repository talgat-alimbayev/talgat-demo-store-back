package talgat.demo.store.back.repositories;

import org.springframework.data.repository.CrudRepository;
import talgat.demo.store.back.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
}

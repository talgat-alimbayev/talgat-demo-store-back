package talgat.demo.store.back.services;

import org.springframework.stereotype.Service;
import talgat.demo.store.back.models.User;
import talgat.demo.store.back.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findByName(String name){
        return userRepo.findByName(name);
    }
    public Optional<User> findById(Long id){
        return userRepo.findById(id);
    }
}

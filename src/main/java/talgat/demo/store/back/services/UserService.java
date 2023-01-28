package talgat.demo.store.back.services;

import org.springframework.stereotype.Service;
import talgat.demo.store.back.models.User;
import talgat.demo.store.back.models.UserDto;
import talgat.demo.store.back.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findByUsername(String username){
        return userRepo.findByUsername(username);
    }
    public Optional<User> findById(Long id){
        return userRepo.findById(id);
    }

    public User saveUser(UserDto userDto){
        User user = new User(userDto);
        return userRepo.save(user);
    }
}

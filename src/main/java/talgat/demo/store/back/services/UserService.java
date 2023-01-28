package talgat.demo.store.back.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<UserDto> findByUsername(String username){
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(new UserDto(user.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    public ResponseEntity<UserDto> findById(Long id){
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(new UserDto(user.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public User saveUser(UserDto userDto){
        User user = new User(userDto);
        return userRepo.save(user);
    }
}

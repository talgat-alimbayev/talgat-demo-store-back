package talgat.demo.store.back.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import talgat.demo.store.back.models.User;
import talgat.demo.store.back.models.UserDTO;
import talgat.demo.store.back.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<UserDTO> findByUsername(String username){
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(new UserDTO(user.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    public ResponseEntity<UserDTO> findById(Long id){
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(new UserDTO(user.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public User saveUser(UserDTO userDto){
        User user = new User(userDto);
        return userRepo.save(user);
    }
}

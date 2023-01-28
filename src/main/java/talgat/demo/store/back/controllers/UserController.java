package talgat.demo.store.back.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talgat.demo.store.back.models.ItemStoreDto;
import talgat.demo.store.back.models.OrderDto;
import talgat.demo.store.back.models.User;
import talgat.demo.store.back.models.UserDto;
import talgat.demo.store.back.services.UserService;

import java.util.Optional;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "api/users/find-by-id")
    public ResponseEntity<UserDto> findUserById(@RequestParam Long userId){
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(new UserDto(user.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "api/users/find-by-username")
    public ResponseEntity<UserDto> findUserByUsername(@RequestParam String username){
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(new UserDto(user.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(path = "api/users/new-user")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserDto userDto){
        userService.saveUser(userDto);
    }
}

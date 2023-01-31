package talgat.demo.store.back.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talgat.demo.store.back.models.UserDTO;
import talgat.demo.store.back.services.UserService;

@RestController
@RequestMapping(produces = "application/json")
@Slf4j
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "api/users/find-by-id")
    public ResponseEntity<UserDTO> findUserById(@RequestParam Long userId){
        log.info("fetching user by id=" + userId.toString());
        return userService.findById(userId);
    }

    @GetMapping(path = "api/users/find-by-username")
    public ResponseEntity<UserDTO> findUserByUsername(@RequestParam String username){
        log.info("fetching a user by username=" + username);
        return userService.findByUsername(username);
    }

    @PostMapping(path = "api/users/new-user")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody @Valid UserDTO userDto){
        log.info("saving a new user to the DB: " + userDto.toString());
        userService.saveUser(userDto);
    }
}

package br.com.projeto.moneyflow.controller;

import br.com.projeto.moneyflow.entity.User;
import br.com.projeto.moneyflow.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.find();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUsersById(@PathVariable Long id){
        return userService.findById(id).map(u-> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user){
        var users = userService.create(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(users);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updatedUser(@PathVariable Long id,@RequestBody User newUser){
        User user = userService.update(id, newUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
         userService.delete(id);
        return ResponseEntity.noContent().build();
    }




}

package br.com.projeto.moneyflow.controller;

import br.com.projeto.moneyflow.dto.UserDTO;
import br.com.projeto.moneyflow.entity.User;
import br.com.projeto.moneyflow.service.ExpenseService;
import br.com.projeto.moneyflow.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ExpenseService expenseService;
    public UserController(UserService userService, ExpenseService expenseService) {
        this.userService = userService;
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.find();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUsersById(@PathVariable Long id){
        Optional<UserDTO> userDTO = userService.findById(id);
        return userDTO
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());

    }
    @GetMapping("{id}/total")
    public ResponseEntity<Double> getTotal(@PathVariable Long id){
        return ResponseEntity.ok(expenseService.getTotalByUser(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> postUser(@RequestBody UserDTO userDto){
        var users = userService.create(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(users);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updatedUser(@PathVariable Long id,@RequestBody UserDTO newUser){
        UserDTO user = userService.update(id, newUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
         userService.delete(id);
        return ResponseEntity.noContent().build();
    }



}

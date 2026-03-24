package br.com.projeto.moneyflow.service;

import br.com.projeto.moneyflow.entity.User;
import br.com.projeto.moneyflow.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    //final indica que não muda depois de inicializar
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //service create
    public User create(User user){
        return userRepository.save(user);
    }

    //service find ---> só compila com Optional pois como é uma busca e tem uma resposta pode achar ou não
    //ai é Opcional um retorno do tipo User
    //orElseThrow
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    //service delete
    public Optional<User> delete(Long id){
        Optional<User> user = findById(id);
        userRepository.delete(user);

        return user;
    }

    //service update
    public User update(Long id, User newUser){
//        var optionalUser = findById(id);
//        if(optionalUser.isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
//        }
//        newUser.setId(id);
//        return userRepository.save(newUser);

        User user = findById(id).orElseThrow();

        user.setName(newUser.getName());
        user.setCpf(newUser.getCpf());
        return userRepository.save(user);

    }



}

package br.com.projeto.moneyflow.service.mapper;

import br.com.projeto.moneyflow.dto.UserDTO;
import br.com.projeto.moneyflow.entity.User;

public class UserMapper {

    //Método que transforma uma entidade User em um dto
    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getCpf()
        );
    }
    //Método que transforma Dto em uma entidade User
    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setAge(dto.age());
        user.setCpf(dto.cpf());
        return user;
    }
}

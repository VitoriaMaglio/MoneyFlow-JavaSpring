package br.com.projeto.moneyflow.repository;

import br.com.projeto.moneyflow.dto.UserDTO;
import br.com.projeto.moneyflow.entity.Expense;
import br.com.projeto.moneyflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}

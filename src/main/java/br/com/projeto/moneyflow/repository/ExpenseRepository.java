package br.com.projeto.moneyflow.repository;

import br.com.projeto.moneyflow.entity.Expense;
import br.com.projeto.moneyflow.entity.User;
import br.com.projeto.moneyflow.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Category> findAllByCategory();
    List<User> findAllByAmount();
}

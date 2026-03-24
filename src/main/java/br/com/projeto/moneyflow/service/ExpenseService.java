package br.com.projeto.moneyflow.service;

import br.com.projeto.moneyflow.entity.Expense;
import br.com.projeto.moneyflow.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    //create
    public Expense create(Expense expense){
        return expenseRepository.save(expense);
    }

    //find -> Toda busca no banco tem que ser um Optional pois tem um retorno e esse retorno pode ser diferente do obj que vc busca
    //updated
    public Expense updated(Long id, Expense newExpense){
        Expense expense = findById(id).orElseThrow();
        return expenseRepository.save(newExpense);
    }

    public Optional<Expense> findById(Long id){
        return expenseRepository.findById(id);
    }

    //delete ->....SOFT DELETE FAZER
    public Expense delete(Long id){
        Expense expense = findById(id).orElseThrow();
        expenseRepository.delete(expense);
        return expense;
        //->DELETE, retorna o objeto do banco não é padrão REST, porque o recurso já foi removido;
    }
}


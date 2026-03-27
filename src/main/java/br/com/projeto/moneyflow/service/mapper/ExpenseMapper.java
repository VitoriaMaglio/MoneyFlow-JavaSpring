package br.com.projeto.moneyflow.service.mapper;

import br.com.projeto.moneyflow.dto.ExpenseDTO;
import br.com.projeto.moneyflow.entity.Expense;
import br.com.projeto.moneyflow.entity.User;

public class ExpenseMapper {

    public static ExpenseDTO toDTO(Expense expense) {
        return new ExpenseDTO(
                expense.getId(),
                expense.getName(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getUser().getId(),
                expense.getUser().getName()
        );
    }

    public static Expense toEntity(ExpenseDTO dto, User user) {
        Expense expense = new Expense();
        expense.setName(dto.name());
        expense.setAmount(dto.amount());
        expense.setCategory(dto.category());
        expense.setUser(user);
        return expense;
    }
}

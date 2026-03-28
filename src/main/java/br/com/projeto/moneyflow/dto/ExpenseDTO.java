package br.com.projeto.moneyflow.dto;

import br.com.projeto.moneyflow.enums.Category;

public record ExpenseDTO(Long id,
                         String name,
                         Double amount,
                         Category category,
                         Long userId,
                         String userName) {
}

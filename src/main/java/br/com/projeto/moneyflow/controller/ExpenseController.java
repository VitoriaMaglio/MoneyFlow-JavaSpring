package br.com.projeto.moneyflow.controller;

import br.com.projeto.moneyflow.dto.ExpenseDTO;
import br.com.projeto.moneyflow.enums.Category;
import br.com.projeto.moneyflow.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;


    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    //requisição create -> post
    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO){
        var expenses = expenseService.create(expenseDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expenses);
    }

    //requisição find -> get
    @GetMapping
    public List<ExpenseDTO> getAllExpenses(){
        return expenseService.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<ExpenseDTO> getById(@PathVariable Long id){
        Optional<ExpenseDTO> expenseDTO = expenseService.findById(id);
        return expenseDTO
                .map( e -> ResponseEntity.ok(e))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/category")
    public ResponseEntity<List<ExpenseDTO>> getByCategory(@RequestParam Category category){
        return ResponseEntity.ok(expenseService.findByCategory(category));
    }

    //requisição updated ->
    @PutMapping("{id}")
    public ResponseEntity<ExpenseDTO> updatedExpense(@PathVariable Long id, @RequestBody ExpenseDTO newExpense){

        ExpenseDTO expense = expenseService.updated(id, newExpense);
        return ResponseEntity.ok(expense);
    }
    //requisição delete -> delete
    @DeleteMapping("{id}")
    public ResponseEntity<ExpenseDTO> deleteExpense(@PathVariable Long id){
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

package br.com.projeto.moneyflow.service;

import br.com.projeto.moneyflow.dto.ExpenseDTO;
import br.com.projeto.moneyflow.entity.Expense;
import br.com.projeto.moneyflow.entity.User;
import br.com.projeto.moneyflow.enums.Category;
import br.com.projeto.moneyflow.repository.ExpenseRepository;
import br.com.projeto.moneyflow.repository.UserRepository;
import br.com.projeto.moneyflow.service.mapper.ExpenseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private  final UserRepository userRepository;


    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;

        this.userRepository = userRepository;
    }

    //create -> primeiro busca user depois cria e seta o relacionamento; salvar no banco entidade pura
    // Busca se o usuário existe por conta do relacionamento, depois transforma dto recebdio para entidade
    //salva no banco e converte para dto o q foi salvo na banco para retorno
    public ExpenseDTO create(ExpenseDTO expenseDto) {
        User user = userRepository.findById(expenseDto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = ExpenseMapper.toEntity(expenseDto, user);
        Expense saved = expenseRepository.save(expense);
        return ExpenseMapper.toDTO(saved);
//        expense.setName(expenseDto.name());
//        expense.setAmount(expenseDto.amount());
//        expense.setCategory(expenseDto.category());
//        expense.setUser(user);         -> USAR MAPPER


    }

    //find
    public List<ExpenseDTO> findAll() {
        return expenseRepository.findAll()
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }


    //updated -> busca despesa e usuário, se achar, atualiza
    public ExpenseDTO updated(Long id, ExpenseDTO dto) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        expense.setName(dto.name());
        expense.setAmount(dto.amount());
        expense.setCategory(dto.category());
        expense.setUser(user);

        Expense updated = expenseRepository.save(expense);

        return ExpenseMapper.toDTO(updated);
    }

    //Optional é usado quando a busca pode retornar um único valor ou vazio
    public Optional<ExpenseDTO> findById(Long id) {

        return expenseRepository.findById(id)
                .map(ExpenseMapper::toDTO);
    }

    //delete ->....SOFT DELETE FAZER
    public Optional<Expense> delete(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        expense.ifPresent(expenseRepository::delete);

        return expense;
        //->DELETE, retorna o objeto do banco não é padrão REST, porque o recurso já foi removido;

        /*
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
        return user;
         */
    }

    public List<ExpenseDTO> findByCategory(Category category) {
        return expenseRepository.findAllByCategory(category)
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }

    public double getTotalByUser(Long id) {
        return expenseRepository.findAllByUserId(id)
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

}
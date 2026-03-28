package br.com.projeto.moneyflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    private String cpf;

    @OneToMany(mappedBy = "user")
    @JsonIgnore //tira o loop do relacionamento
    private List<Expense> expenses;


}

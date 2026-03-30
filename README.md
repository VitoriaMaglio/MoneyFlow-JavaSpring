# 💰  Money Flow API


Projeto Diamante : API REST desenvolvida com Spring Boot para gerenciamento de finanças pessoais, permitindo o controle de usuários e suas despesas.

---
 📌 **Funcionalidades**

 CRUD completo de usuários
 
 CRUD completo de despesas (expenses)
 
 Relacionamento entre usuário e despesas
 
 Cálculo do total de despesas por usuário
 
 Persistência com banco de dados relacional

---

🛠️ **Tecnologias utilizadas**

 Java
 
 Spring Boot
 
 Spring Data JPA
 
 PostgreSQL

 Flyway (controle de migrations)
 
 Maven
 
 Estrutura do Projeto

---

**Endpoints e json para teste:**

**POSTs**

/users

{
  "name": "Vitória",
  "age": 22,
  "cpf": "12345678900"
}

/expenses

{
  "name": "Almoço",
  "amount": 45.50,
  "category": "FOOD",
  "userId": 1,
  "userName": "Vitória"
}

**GETs**

/expenses

/expenses/1

/users

/users/1

/users/1/total  --> retorna valor total gasto do user

**PUTs**

/users/1

{
  "name": "Vitória Valentina",
  "age": 22,
  "cpf": "12345678900"
}

/expenses/1

{
  "name": "Almoço na faculdade",
  "amount": 45.50,
  "category": "FOOD",
  "userId": 1,
  "userName": "Vitória Valentina"
}


**DELETE**

/expenses/1 

/users/1 

---

⚠️ Observações importantes

O ID dos registros é gerado automaticamente pelo banco de dados

Não é recomendado assumir valores fixos de ID (como 1)

Sempre utilize o ID retornado pela API após a criação

---

**Integrantes:**

Vitória Valentina Maglio

Marina Tamagnini Magalhães

João Pedro 

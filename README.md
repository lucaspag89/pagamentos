# Pagamentos
Aplicação para gerenciamento do pagamento de funcionários.

### A documentação pode ser encontrada em <http://localhost:8080/swagger-ui.html>
Exemplos de registros de empresas e funcionários e manipulação de contas podem ser encontrados abaixo.

### Tecnologias utilizadas: 

- Java 11
- Spring Boot 2
- Maven
- Spring Data JPA
- Spring Security 
- Hibernate
- Flyway 
- PostgreSQL 11

**Antes de iniciar a aplicação, é necessário configurar o PostgreSQL**
- Host: localhost
- Port: 5432
- Database: payments
- Username: postgres
- Password: admin 
- </br>
As tabelas são criadas por migration quando a aplicação for iniciada.

## LOGIN
- Para acesso às funcionalidades da aplicação é necessário um token de autenticação.
- O token pode ser gerado pelo endpoint "/api/login" (POST) com o objeto do exemplo abaixo:
```
{
  "login": "admin",
  "senha": "password"
}
```
- O token deve ser enviado pelo 

## EMPRESAS
- Para o registro de empresas utilize o endpoint "/api/empresas" (POST) com o objeto do exemplo abaixo:
```
{
    "razaosocial": "Solar Refrescos S.A.",
    "nomefantasia": "Solar Coca-Cola",
    "cnpj": "66308730016200",
    "fundacao": "23/04/2013",
    "endereco": "Av. Washington Soares, 55 - 9 andar",
    "cidade": "Fortaleza",
    "estado": "CE",
    "telefone": "8532666300"
}
```

## FUNCIONÁRIOS
- Para o registro de funcionários utilize o endpoint "/api/funcionarios" (POST) com o objeto do exemplo abaixo:
```
{
    "nome": "José da Silva",
    "cpf": "13754644009",
    "endereco": "Rua Silva Jatahy, 346",
    "cidade": "Fortaleza",
    "estado": "CE",
    "telefone":"85992644587",
    "datanasc":"14/08/1990",
    "sexo": "masculino",
    "empresaid":"1",
    "dataadmissao":"16/01/2020"
}
```

## CONTAS 
- As contas de empresas e funcionarios são geradas automaticamente no momento do registro.
- Permitida apenas uma conta por registro.
- Para depósito em conta de empresa utilize o endpoint "/api/contas/empresas/{id} (PUT) com o id da conta da empresa e o objeto do exemplo abaixo:
```
{
    "empresaid": "1"
    "deposito": "250689.78"
}
```
- Para o pagamento de um funcionário utilize o endpoint "/api/contas/funcionarios/{id} (PUT) com o id da conta do funcionário e o objeto do exemplo abaixo:
```
{
  "empresaid": 1,
  "funcionarioid": 1,
  "pagamento": "4230.78"
}
```
- Uma tarifa de 0.38% sobre o total da folha é descontada do saldo da emresa.
- Para que o pagamento seja realizado, o saldo da conta da empresa deve ser superior ao valor do pagamento e da taxa. 

# COMUNICAÇÃO-API

Refatoração de uma API REST com Java e Spring Boot, utilizando Maven, responsável pelo gerenciamento das operações
de comunicação da aplicação.

O projeto foi dividido em uma arquitetura de microsserviços, com separação em controller, entity, bussiness, infraestructure,
componentes de integração e persistência.

A operação recebe conexão com uma API externa de notificação através do Spring Cloud OpenFeign e é documentada utilizando Swagger/OpenAPI.

## Tecnologias utilizadas:
- Java 11
- Spring Boot: Framework principal.
- Maven: Gerenciamento de depedências.
- Docker: Conteinerização do projeto e suas dependências.
- Swagger: Documentação do projeto.
- MapStruct: Conversão entre objetos.
- Lombok: Redução de verbosidade.
- MySQL: Banco de dados relacional.
- Spring Data JPA: persistência de dados.

## ARQUITETURA E ORGANIZAÇÃO
* Controller: recebe e controla requisições HTTP, encaminha os dados para a camada de negócios (business) e define os retornos.
* Service: concentra as regras de negócio e coordena as operações da API.
* Client: encapsula a comunicação com a API de notificação por meio do OpenFeign.
* Converter: realiza a conversão entre DTO e entity, utilizando MapStruct para otimização do código.
* Infrastructure: reúne configurações de segurança, componentes técnicos, exceções e seus tratamentos.

## ENDPOINTS
A API apresenta os seguintes endpoints para agendamentos, consulta de dados, envio de notificações e cancelamento de comunicações.
URL Base: http://localhost:8080

### POST /comunicacao/agendar
**Responsabilidade:**
Realiza o agendamento de uma nova comunicação, recebendo os dados necessários para a sua criação e encaminhando a operação para a camada business.
O endpoint retorna os dados da comunicação após o processamento. O sistema define automaticamente o status como PENDENTE e converte os dados recebidos.
**Request Body:**
```json
{
  "dataHoraEnvio": "yyyy-mm-dd hh:mm:ss",
  "nomeDestinatario": "Nome",
  "emailDestinatario": "email@exemplo.com",
  "telefoneDestinatario": "99999999999",
  "mensagem": "Sua comunicação foi agendada.",
  "modoDeEnvio": "EMAIL"
}
```
O campo statusEnvio não deve ser enviado pelo cliente, pois é ignorado na desserialização e definido internamente pela aplicação.

**Possíveis respostas:**
```http
200 OK: Comunicação criada com sucesso.
403 FORBIDDEN: Erro ao criar comunicação.
500: Erro interno no servidor.
```

### GET /comunicacao
**Responsabilidade:**
Consulta uma comunicação previamente cadastrada utilizando o endereço de e-mail do destinatário como identificador da pesquisa.
O sistema realiza uma consulta no banco de dados por meio do e-mail informado e retorna os dados da comunicação encontrada, incluindo seu status atual.

**Params:**
emailDestinatario, String, Obrigatório.
Endereço de email utilizado para localizar a comunicação.
```http
GET /comunicacao?emailDestinatario=email@exemplo.com
```

**Resposta de sucesso:**
200 OK
```json
{
  "dataHoraEnvio": "yyyy-mm-dd hh:mm:ss",
  "nomeDestinatario": "Nome",
  "emailDestinatario": "email@exemplo.com",
  "telefoneDestinatario": "99999999999",
  "mensagem": "Sua comunicação foi agendada.",
  "modoDeEnvio": "EMAIL",
  "statusEnvio": "PENDENTE"
}
```

**Possíveis respostas:**
```http
200 OK: Comunicação criada com sucesso.
404 NOT FOUND: E-mail não encontrado.
401 UNAUTHORIZED: Credenciais inválidas.
500: Erro interno no servidor.
```
### PATCH /comunicacao/cancelar
**Responsabilidade:**
Realiza o cancelamento de uma comunicação previamente cadastrada, identificando o registro por meio do e-mail do destinatário.
Após localizar a comunicação no banco de dados, o sistema altera seu status para CANCELADO e persiste a atualização.
Demais dados da operação são preservados.

**Params:**
emailDestinatario, String, Obrigatório.
Endereço de email utilizado para localizar a comunicação que será cancelada.

```http
PATCH /comunicacao/cancelar?emailDestinatario=email@exemplo.com
```

**Resposta de sucesso:**
200 OK
```json
{
  "dataHoraEnvio": "yyyy-mm-dd hh:mm:ss",
  "nomeDestinatario": "Nome",
  "emailDestinatario": "email@exemplo.com",
  "telefoneDestinatario": "99999999999",
  "mensagem": "Sua comunicação foi agendada.",
  "modoDeEnvio": "EMAIL",
  "statusEnvio": "CANCELADO"
}
```
**Possíveis respostas:**
```http
200 OK: Comunicação cancelada com sucesso.
404 NOT FOUND: E-mail não encontrado.
401 UNAUTHORIZED: Credenciais inválidas.
500: Erro interno no servidor.
```

## Tratamento de exceções
A aplicação utiliza um GlobalExceptionHandler para centralizar o tratamento de exceções e padronizar
as respostas a erros.

* BusinessException: representa erros relacionados às regras
  de negócio.
* ConflictException: representa situações de conflito.
* ResourceNotFoundException: representa recursos não encontrados.
* Exceções de autenticação: representam falhas de autorização
  ou autenticação, sendo elas: UnauthorizedException e IllegalArgumentException.

As respostas são disponibilizadas em formato JSON, contendo
informações relevantes sobre o erro.

**Resposta do ExceptionsDTO:**
200 OK
```json
{
  "timestamp": "yyyy-mm-ddThh:mm:ss",
  "statusCode": 409,
  "message": "Exemplo de conflito",
  "path": "/comunicacao",
  "error": "Conflict"
}
```

## Requisitos

- Java 11.
- Maven.
- MySQL.
- Docker.

## Documentação da API

A documentação interativa dos endpoints está disponível
por meio do Swagger UI:

http://localhost:8080/swagger-ui.html

## Decisões Técnicas

Durante a refatoração, foram adotadas práticas para melhorar
a organização, manutenção e legibilidade do código, tornando-o menos verboso
e adicionando novas funcionalidades.

**Principais decisões:**

- Separação das responsabilidades entre as camadas da aplicação.
- Centralização do tratamento de exceções.
- Utilização de DTOs para transferência de dados.
- Utilização do MapStruct para conversão de objetos.
- Utilização do Spring Cloud OpenFeign para comunicação
  entre microsserviços.
- Organização das regras de negócio na camada de serviços.
- Padronização das respostas HTTP.
- Utilização de injeção de dependências.
- Utilização de Docker para conteinerização.
- Utilização de uma GlobalExceptionHandler para padronizar e melhorar erros e exceções.

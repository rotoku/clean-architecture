# Clean Architecture para Java 21 e SpringBoot 3

Aplicação **Customers** construída usando Java 21 e Spring Boot 3, seguindo os princípios da Clean Architecture. Cada bloco de código demonstra como usar o comando curl para realizar várias operações em recursos de clientes por meio de requisições HTTP.

A seção "Criar Cliente" mostra como enviar uma requisição POST com um payload JSON para adicionar um novo cliente ao sistema. A seção "Buscar Cliente" demonstra como buscar os detalhes de um cliente específico pelo seu ID usando uma requisição GET. Para atualizar as informações de um cliente existente, a seção "Atualizar Cliente" fornece um exemplo de requisição PUT com os dados atualizados no corpo da requisição.

Para remover um cliente, a seção "Deletar Cliente" ilustra como enviar uma requisição DELETE para o endpoint apropriado. Por fim, a seção "Listar Clientes" mostra como recuperar uma lista de todos os clientes enviando uma requisição GET para o endpoint base de clientes. Esses exemplos servem como documentação prática, facilitando para usuários e desenvolvedores testarem e entenderem a funcionalidade da API diretamente pela linha de comando.

## Criar Cliente
```bash
curl -X POST \
    -H 'Content-Type: application/json' \
    http://localhost:8080/api/v1/customers \
    -d '{"firstName": "Gugu", "lastName": "Liberato", "email": "gugu.liberato@gmail.com"}'

```    

## Buscar Cliente
```bash
curl -X GET \
    -H 'Content-Type: application/json' \
    http://localhost:8080/api/v1/customers/1
```    

## Atualizar Cliente
```bash
curl -X PUT \
    -H 'Content-Type: application/json' \
    http://localhost:8080/api/v1/customers/1 \
    -d '{"firstName": "Gugu", "lastName": "Liberato", "email": "gugu.liberato@hotmail.com"}'

```    

## Deletar Cliente
```bash
curl -X DELETE \
    -H 'Content-Type: application/json' \
    http://localhost:8080/api/v1/customers/1
```    

## Listar Clientes
```bash
curl -X GET \
    -H 'Content-Type: application/json' \
    http://localhost:8080/api/v1/customers
```
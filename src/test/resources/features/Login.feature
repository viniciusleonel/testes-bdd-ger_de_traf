# language: pt

Funcionalidade: Login de usuario
  Como usuário da API
  Quero realizar um login
  Para que eu possa acessar o sistema
  Cenário: Login bem-sucedido de usuario
    Dado que eu tenha os seguintes dados do usuario:
    | campo          | valor                |
    | email          | teste.fiap@gmail.com |
    | senha          | 123456               |
    Quando eu enviar a requisição para o endpoint "/autenticacao/login" de login de usuario
    Então o status code da resposta deve ser 200
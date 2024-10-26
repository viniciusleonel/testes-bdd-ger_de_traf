# language: pt

Funcionalidade: Cadastro, Login e Delecao de usuario
  Como usuário da API
  Quero realizar um cadastro de usuario, realizar um login e deletar o usuario
  Para que o registro seja salvo corretamente no sistema
  Contexto: Cadastro e login bem-sucedido de usuario
    Dado que eu tenha os seguintes dados do usuario:
    | campo          | valor                |
    | email          | teste.fiap@gmail.com |
    | senha          | 123456               |
    | role           | ADMIN                |
    Quando eu enviar a requisição para o endpoint "/usuarios/cadastrar" de cadastro de usuario
    Então o status code da resposta deve ser 201
    Dado que eu recupere o ID do usuario criado
    Dado que eu tenha os seguintes dados do login:
      | campo          | valor                |
      | email          | teste.fiap@gmail.com |
      | senha          | 123456               |
    Quando eu enviar a requisição para o endpoint "/autenticacao/login" de login de usuario
    Então o status code da resposta deve ser 200
    Cenario: Deletando usuario apos cadastro e login bem sucedidos
      Dado que eu recupere o token retornado ao realizar login
      Quando eu enviar a requisição com o ID para o endpoint "/usuarios/deletar" de deleção de usuario
      Então o status code da resposta deve ser 204
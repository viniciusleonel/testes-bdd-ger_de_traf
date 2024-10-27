# language: pt

@regressivo
Funcionalidade: Testes de falhas ao realizar cadastro e login
  Como usuário da API
  Quero realizar um cadastro de usuario, realizar um login e deletar o usuario
  Para que o registro seja salvo corretamente no sistema
  Cenário: Falha no cadastro do usuario ao passar um campo de email invalido
    Dado que eu tenha os seguintes dados do usuario:
      | campo          | valor                |
      | email          | teste.fiapgmail.com |
      | senha          | 123456               |
      | role           | ADMIN                |
    Quando eu enviar a requisição para o endpoint "/usuarios/cadastrar" de cadastro de usuario
    Então o status code da resposta deve ser 400

  Cenário: Falha no login do usuario ao passar um usuario inexistente
    Dado que eu tenha os seguintes dados do login:
      | campo          | valor                |
      | email          | fiao.teste@gmail.com |
      | senha          | 123                  |
    Quando eu enviar a requisição para o endpoint "/autenticacao/login" de login de usuario
    Então o status code da resposta deve ser 400


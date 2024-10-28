# language: pt

@regressivo
Funcionalidade: Testes de falhas ao realizar cadastro e login
  Como usuário da API
  Quero realizar um cadastro de usuario, realizar um login e deletar o usuario
  Para que o registro seja salvo corretamente no sistema
  Cenário: Falha no cadastro do usuario ao passar um campo de email invalido com validacao de status code e validacao de mensagem de erro
    Dado que eu tenha os seguintes dados do usuario:
      | campo          | valor                |
      | email          | teste.fiapgmail.com  |
      | senha          | 123456               |
      | role           | ADMIN                |
    Quando eu enviar a requisição para o endpoint "/usuarios/cadastrar" de cadastro de usuario
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "Insira um e-mail válido!" do campo "email"

  Cenário: Falha no cadastro do usuario ao não enviar um email com validacao de status code e validacao de mensagem de erro
    Dado que eu tenha os seguintes dados do usuario:
      | campo          | valor                |
      | email          |                      |
      | senha          | 123456               |
      | role           | ADMIN                |
    Quando eu enviar a requisição para o endpoint "/usuarios/cadastrar" de cadastro de usuario
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "Insira um e-mail!" do campo "email"

  Cenário: Falha no cadastro do usuario ao não enviar uma senha com validacao de status code e validacao de mensagem de erro
    Dado que eu tenha os seguintes dados do usuario:
      | campo          | valor                |
      | email          | teste.fiapgmail.com  |
      | senha          |                      |
      | role           | ADMIN                |
    Quando eu enviar a requisição para o endpoint "/usuarios/cadastrar" de cadastro de usuario
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "Insira uma senha!" do campo "senha"

  Cenário: Falha no login do usuario ao passar um usuario inexistente com validacao de status code e validacao de mensagem de erro
    Dado que eu tenha os seguintes dados do login:
      | campo          | valor                |
      | email          | fiao.teste@gmail.com |
      | senha          | 123                  |
    Quando eu enviar a requisição para o endpoint "/autenticacao/login" de login de usuario
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "Usuário não encontrado" do campo "message"

  Cenário: Falha ao deletar um usuario sem passar o token com validacao de status code
    Quando eu enviar a requisição para o endpoint "/usuarios/deletar" de login de usuario
    Então o status code da resposta deve ser 403

  Cenário: Falha ao listar usuarios sem passar o token com validacao de status code
    Quando eu enviar a requisição para o endpoint "/acidentes/listar?page=0&size=10" de login de usuario
    Então o status code da resposta deve ser 403

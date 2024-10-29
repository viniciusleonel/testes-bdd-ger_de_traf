# language: pt

@regressivo
Funcionalidade: Testes de falhas da API com validação de status code e validação do corpo da resposta
  Como usuário da Traffic Incident Management API
  Quero testar as falhas ao realizar requisicoes incorretas
  Para garantir que as repostas da API estejam em conformidade com o esperado

  Cenário: Falha no cadastro do usuário ao passar um campo de email inválido, com validação de status code e validação de mensagem de erro
    Dado que eu tenha os seguintes dados do usuario:
      | campo | valor               |
      | email | teste.fiapgmail.com |
      | senha | 123456              |
      | role  | ADMIN               |
    Quando eu enviar a requisição para o endpoint "/usuarios/cadastrar" de cadastro de usuario
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "Insira um e-mail válido!" do campo "email"

  Cenário: Falha no cadastro do usuário ao não enviar um email, com validação de status code e validação de mensagem de erro
    Dado que eu tenha os seguintes dados do usuario:
      | campo | valor  |
      | email |        |
      | senha | 123456 |
      | role  | ADMIN  |
    Quando eu enviar a requisição para o endpoint "/usuarios/cadastrar" de cadastro de usuario
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "Insira um e-mail!" do campo "email"

  Cenário: Falha no cadastro do usuário ao não enviar uma senha, com validação de status code e validação de mensagem de erro
    Dado que eu tenha os seguintes dados do usuario:
      | campo | valor               |
      | email | teste.fiapgmail.com |
      | senha |                     |
      | role  | ADMIN               |
    Quando eu enviar a requisição para o endpoint "/usuarios/cadastrar" de cadastro de usuario
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "Insira uma senha!" do campo "senha"

  Cenário: Falha no login do usuario ao passar um usuário inexistente, com validação de status code e validação de mensagem de erro
    Dado que eu tenha os seguintes dados do login:
      | campo | valor                |
      | email | fiao.teste@gmail.com |
      | senha | 123                  |
    Quando eu enviar a requisição para o endpoint "/autenticacao/login" de login de usuario
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "Usuário não encontrado" do campo "message"

  Cenário: Falha ao deletar um usuário sem passar o token e validação de status code
    Quando eu enviar a requisição para o endpoint "/usuarios/deletar" de login de usuario
    Então o status code da resposta deve ser 403

  Cenário: Falha ao listar usuários sem passar o token e validação de status code
    Quando eu enviar a requisição para o endpoint "/usuarios/listar" de listagem de usuarios
    Então o status code da resposta deve ser 403

  Cenário: Falha ao listar acidentes sem passar o token e validação de status code
    Quando eu enviar a requisição para o endpoint "/acidentes/listar" de listagem de acidentes
    Então o status code da resposta deve ser 403

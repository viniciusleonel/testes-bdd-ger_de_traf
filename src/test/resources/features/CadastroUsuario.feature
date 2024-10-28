# language: pt

@regressivo
Funcionalidade: Cadastro, login e deleção de usuário, com checagem de contratos e validação de status code
  Como usuário da Traffic Incident Management API
  Quero realizar um cadastro de usuario, realizar um login e deletar o usuario
  Para que o registro seja salvo corretamente no sistema e os dados sejam delatados no final
  Contexto: Cadastro e login bem-sucedido de usuario
    Dado que eu tenha os seguintes dados do usuario:
    | campo          | valor                |
    | email          | teste.fiap@gmail.com |
    | senha          | 123456               |
    | role           | ADMIN                |
    Quando eu enviar a requisição para o endpoint "/usuarios/cadastrar" de cadastro de usuario
    Então o status code da resposta deve ser 201
    E que o arquivo de contrato esperado é o "Cadastro bem sucedido"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado
    Dado que eu recupere o ID do usuario criado
    Dado que eu tenha os seguintes dados do login:
      | campo          | valor                |
      | email          | teste.fiap@gmail.com |
      | senha          | 123456               |
    Quando eu enviar a requisição para o endpoint "/autenticacao/login" de login de usuario
    Então o status code da resposta deve ser 200
    E que o arquivo de contrato esperado é o "Login bem sucedido"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado
    Cenario: Cadastrando, logando e deletando usuário após cadastro e login bem sucedidos com validacao de status code e validação de contrato
      Dado que eu recupere o token retornado ao realizar login
      Quando eu enviar a requisição com o ID para o endpoint "/usuarios/deletar" de deleção de usuario
      Então o status code da resposta deve ser 204

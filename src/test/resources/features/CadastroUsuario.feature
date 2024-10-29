# language: pt

@regressivo
Funcionalidade: Cadastro, login e deleção de usuário com validação de contratos e status code
  Como usuário da Traffic Incident Management API
  Quero garantir que o processo de cadastro, login e deleção de usuário funcione corretamente
  Para assegurar que o sistema mantenha a integridade dos dados e remova informações ao final dos testes
  Contexto: Cadastro e login bem-sucedido de usuário para obtenção do token de autenticação necessário
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

# language: pt

@regressivo
Funcionalidade: Cadastro, login e deleção de usuário com validação de contratos e status code
  Como usuário da Traffic Incident Management API
  Quero garantir que o processo de cadastro, login e deleção de usuário funcione corretamente
  Para assegurar que o sistema mantenha a integridade dos dados e remova informações ao final dos testes

  Cenario: Cadastrando usuario, recuperando ID, validando status code e contrato
    Dado que eu tenha os seguintes dados do usuario:
      | campo | valor                |
      | email | teste.fiap@gmail.com |
      | senha | 123456               |
      | role  | ADMIN                |
    Quando eu enviar a requisição para o endpoint "/usuarios/cadastrar" de cadastro de usuario
    Então o status code da resposta deve ser 201
    Dado que eu recupere o ID do usuario criado
    E que o arquivo de contrato esperado é o "Cadastro bem sucedido"
    Então a resposta da requisição deve estar conforme o contrato selecionado

  Cenario: Realizando login, validando status code e contrato
    Dado que eu realize o login com os mesmos dados cadastrados
    Quando eu enviar a requisição para o endpoint "/autenticacao/login" de login de usuario
    Então o status code da resposta deve ser 200
    Dado que eu recupere o token retornado ao realizar login
    E que o arquivo de contrato esperado é o "Login bem sucedido"
    Então a resposta da requisição deve estar conforme o contrato selecionado

  Cenário: Listando usuarios com o Token recuperado e validando status code
    Quando eu enviar a requisição para o endpoint "/usuarios/listar" de listagem de usuarios
    Então o status code da resposta deve ser 200

  Cenario: Deletando usuario com ID e Token recuperado e validando status code
    Quando eu enviar a requisição com o ID para o endpoint "/usuarios/deletar" de deleção de usuario
    Então o status code da resposta deve ser 204

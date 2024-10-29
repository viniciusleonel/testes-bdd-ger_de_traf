# language: pt

@regressivo
Funcionalidade: Registro de acidente com checagem de contrato e validacao de status code
  Como usuário da Traffic Incident Management API
  Quero garantir que o processo cadastro de acidente, listagem e deleção de acidente funcione corretamente
  Para assegurar que o sistema mantenha a integridade dos dados e remova informações ao final dos testes

  Cenario: Cadastrando usuario, validando status code e contrato
    Dado que eu tenha os seguintes dados do usuario:
      | campo | valor                   |
      | email | acidente.fiap@gmail.com |
      | senha | 123456                  |
      | role  | ADMIN                   |
    Quando eu enviar a requisição para o endpoint "/usuarios/cadastrar" de cadastro de usuario
    Então o status code da resposta deve ser 201
    E que o arquivo de contrato esperado é o "Cadastro bem sucedido"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado
    Dado que eu recupere o ID do usuario criado

  Cenario: Realizando login, recuperando token, validando status code e contrato
    Dado que eu tenha os seguintes dados do login:
      | campo | valor                   |
      | email | acidente.fiap@gmail.com |
      | senha | 123456                  |
    Quando eu enviar a requisição para o endpoint "/autenticacao/login" de login de usuario
    Então o status code da resposta deve ser 200
    Dado que eu recupere o token retornado ao realizar login
    E que o arquivo de contrato esperado é o "Login bem sucedido"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado

  Cenario: Cadastrando acidente, recuperando ID, validando status code e contrato
    Dado que eu tenha os seguintes dados do acidente:
      | campo     | valor               |
      | dataHora  | 2024-08-04T13:00:00 |
      | gravidade | grave               |
    E que eu tenha os seguintes dados do endereco:
      | campo      | valor                   |
      | logradouro | Av. Lins de Vasconcelos |
      | numero     | 1222                    |
      | bairro     | Liberdade               |
      | cep        | 01538001                |
      | cidade     | São Paulo               |
      | estado     | SP                      |
    E que eu tenha os seguintes dados do veiculo:
      | campo  | valor    |
      | placa  | ABC-123  |
      | modelo | Corcel 2 |
      | ano    | 1986     |
      | cor    | Branco   |
    E que eu tenha os seguintes dados do ferido:
      | campo     | valor              |
      | nome      | James              |
      | cpf       | 10987654321        |
      | gravidade | pequenos arranhões |
    E que eu consiga montar os dados do acidente com os dados obtidos
    Quando eu enviar a requisição para o endpoint "/acidentes/cadastrar" de cadastro de acidente
    Então o status code da resposta deve ser 201
    E que o arquivo de contrato esperado é o "Acidente cadastrado"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado
    Dado que eu recupere o ID do acidente registrado

  Cenario: Listando acidentes com token recuperado e validando status code
    Quando eu enviar a requisição para o endpoint "/acidentes/listar" de listagem de acidentes
    Então o status code da resposta deve ser 200

  Cenario: Deletando acidente com token recuperado e validando status code
    Quando eu enviar a requisição com o ID para o endpoint "/acidentes/deletar" de deleção de acidente
    Então o status code da resposta deve ser 204

  Cenario: Deletando usuário com ID recuperado e validando status code
    Quando eu enviar a requisição com o ID para o endpoint "/usuarios/deletar" de deleção de usuario
    Então o status code da resposta deve ser 204

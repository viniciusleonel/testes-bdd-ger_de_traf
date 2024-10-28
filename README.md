# Traffic Incident Management API

## Visão Geral

Esta aplicação é um conjunto de testes automatizados para a API de Gerenciamento de Incidentes de Tráfego. Ela utiliza o framework Cucumber para definir cenários de teste em linguagem natural e o Rest Assured para realizar requisições HTTP e validar respostas.

## Estrutura do Projeto

- **src/test/java/services**: Contém as classes de serviço que encapsulam a lógica de interação com a API.
- **src/test/resources/features**: Contém os arquivos de feature do Cucumber, que descrevem os cenários de teste.
- **src/test/java/steps**: Contém as definições dos passos do Cucumber, que ligam os cenários de teste à lógica de teste.
- **src/test/java/model**: Contém os modelos de dados utilizados para mapear as respostas da API.
- **src/test/resources/schemas**: Contém os esquemas JSON utilizados para validação de contrato das respostas da API.

## Funcionalidades

A aplicação cobre as seguintes funcionalidades:

1. **Cadastro de Usuário**: Criação de um novo usuário na API.
2. **Login de Usuário**: Autenticação de um usuário existente.
3. **Cadastro de Acidente**: Registro de um acidente com detalhes como data, gravidade, endereço, veículos envolvidos e feridos.
4. **Deleção de Usuário**: Remoção de um usuário existente da API.

## Como Executar

1. **Pré-requisitos**: Certifique-se de ter o Java e o Maven instalados em sua máquina.

2. **Clonar o Repositório**:
   ```bash
   git clone https://github.com/viniciusleonel/testes-bdd-ger_de_traf
   cd testes-bdd-ger_de_traf
   ```

3. **Executar os Testes**:
   Utilize o Maven para executar os testes:
   ```bash
   mvn clean test
   ```

4. **Relatórios**:
   Após a execução dos testes, um relatório em HTML será gerado na pasta `target/cucumber-reports.html`.

## Configuração

- **Base URL**: A URL base da API está configurada na classe `ApiService` e pode ser alterada conforme necessário.
- **Esquemas JSON**: Os esquemas para validação de contrato estão localizados em `src/test/resources/schemas`.

## Links da API

[Github - Traffic Incident Management API](https://github.com/viniciusleonel/traffic-incident-management-api)

[Microsoft Azure Swagger- Traffic Incident Management API ](https://traffic-incident-api-dev-dtbtfvg2e7e7a8eq.eastus2-01.azurewebsites.net/swagger-ui/index.html)

[Microsoft Azure Base URL - Traffic Incident Management API ](https://traffic-incident-api-dev-dtbtfvg2e7e7a8eq.eastus2-01.azurewebsites.net)

## Tecnologias Utilizadas

- Java
- Maven
- Lombok
- Cucumber
- Gherkin
- Gson
- Rest Assured
- GitHub Actions
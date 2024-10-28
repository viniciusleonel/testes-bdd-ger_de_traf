package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.ApiService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApiSteps {

    ApiService apiService = new ApiService();

    @Dado("que eu tenha os seguintes dados do usuario:")
    public void queEuTenhaOsSeguintesDadosDoUsuario(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            apiService.setFieldsUsuario(columns.get("campo"),  columns.get("valor"));
        }
        System.out.println("Obtendo dados do usuário...");
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de usuario")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeUsuario(String endpoint) {
        apiService.createUsuario(endpoint);
        System.out.println("Enviando uma requisição de cadastro de usuário para o endpoint: " + endpoint);
    }

    @Dado("que eu recupere o ID do usuario criado")
    public void queEuRecupereOIDDoUsuairoCriado() {
        apiService.retrieveIdUsuario();
        System.out.println("Recuperando ID do usuario...");
    }

    @Dado("que eu recupere o token retornado ao realizar login")
    public void queEuRecupereOTokenRetornadoAoRealizarLogin() {
        apiService.retrieveToken();
        System.out.println("Recuperando token...");
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de usuario")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeUsuario(String endpoint) {
        apiService.deleteUsuario(endpoint);
        System.out.println("Enviando uma requisição de deleção de usuario com o ID recuperado para o endpoint: " + endpoint);
    }

    @Dado("que eu tenha os seguintes dados do login:")
    public void queEuTenhaOsSeguintesDadosDoLogin(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            apiService.setFieldsLogin(columns.get("campo"),  columns.get("valor"));
        }
        System.out.println("Obtendo dados do login...");
    }

    @Quando("eu enviar a requisição para o endpoint {string} de login de usuario")
    public void euEnviarARequisiçãoParaOEndpointDeLoginDeUsuario(String endpoint) {
        apiService.createLogin(endpoint);
        System.out.println("Enviando uma requisição de login para o endpoint: " + endpoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, apiService.response.statusCode());
        System.out.println("Analisando status code: " + statusCode);
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        apiService.setContract(contract);
        System.out.println("Obtendo dados do contrato...");
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = apiService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
        System.out.println("Analisando resposta da requisição com o contrato selecionado...");
    }

    @Dado("que eu tenha os seguintes dados do acidente:")
    public void queEuTenhaOsSeguintesDadosDoAcidente(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            apiService.setFieldAcidente(columns.get("campo"),  columns.get("valor"));
        }
        System.out.println("Obtendo dados do acidente...");
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de acidente")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeAcidente(String endpoint) {
        apiService.createAcident(endpoint);
        System.out.println("Enviando uma requisição de cadastro de acidente para o endpoint: " + endpoint);
    }

    @E("que eu tenha os seguintes dados do endereco:")
    public void queEuTenhaOsSeguintesDadosDoEndereco(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            apiService.setFieldEndereco(columns.get("campo"),  columns.get("valor"));
        }
        System.out.println("Obtendo dados do endereco...");
    }

    @E("que eu tenha os seguintes dados do veiculo:")
    public void queEuTenhaOsSeguintesDadosDoVeiculo(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            apiService.setFieldVeiculo(columns.get("campo"),  columns.get("valor"));
        }
        System.out.println("Obtendo dados do veiculo...");
    }

    @E("que eu tenha os seguintes dados do ferido:")
    public void queEuTenhaOsSeguintesDadosDoFerido(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            apiService.setFieldFerido(columns.get("campo"),  columns.get("valor"));
        }
        System.out.println("Obtendo dados do ferido...");
    }

    @E("que eu consiga montar os dados do acidente com os dados obtidos")
    public void queEuConsigaMontarOsDadosDoAcidenteComOsDadosObtidos() {
        apiService.montarAcidente();
        System.out.println("Montando dados do acidente...");
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string} do campo {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message, String field) throws IOException {
        apiService.validateMessage(message, field);
        System.out.println("Validando corpo da resposta da requisição...");
    }

    @Quando("eu enviar a requisição para o endpoint {string} de listagem de usuarios")
    public void euEnviarARequisiçãoParaOEndpointDeListagemDeUsuarios(String endpoint) {
        apiService.enviarReqGet(endpoint);
        System.out.println("Enviando uma requisição de login para o endpoint: " + endpoint);
    }

    @Quando("eu enviar a requisição para o endpoint {string} de listagem de acidentes")
    public void euEnviarARequisiçãoParaOEndpointDeListagemDeAcidentes(String endpoint) {
        apiService.enviarReqGet(endpoint);
        System.out.println("Enviando uma requisição de login para o endpoint: " + endpoint);
    }
}

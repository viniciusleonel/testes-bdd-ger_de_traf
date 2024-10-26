package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.UsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UsuarioSteps {

    UsuarioService usuarioService = new  UsuarioService();

    @Dado("que eu tenha os seguintes dados do usuario:")
    public void queEuTenhaOsSeguintesDadosDoUsuario(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            usuarioService.setFieldsUsuario(columns.get("campo"),  columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de usuario")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeUsuario(String endpoint) {
        usuarioService.createUsuario(endpoint);
        Assert.assertEquals(201, usuarioService.response.statusCode());
    }

    @Dado("que eu recupere o ID do usuario criado")
    public void queEuRecupereOIDDoUsuairoCriado() {
        usuarioService.retrieveIdUsuario();
    }

    @Dado("que eu recupere o token retornado ao realizar login")
    public void queEuRecupereOTokenRetornadoAoRealizarLogin() {
        usuarioService.retrieveToken();
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de usuario")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeUsuario(String endpoint) {
        usuarioService.deleteUsuario(endpoint);
    }

    @Dado("que eu tenha os seguintes dados do login:")
    public void queEuTenhaOsSeguintesDadosDoLogin(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            usuarioService.setFieldsLogin(columns.get("campo"),  columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de login de usuario")
    public void euEnviarARequisiçãoParaOEndpointDeLoginDeUsuario(String endpoint) {
        usuarioService.createLogin(endpoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, usuarioService.response.statusCode());
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        usuarioService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = usuarioService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}

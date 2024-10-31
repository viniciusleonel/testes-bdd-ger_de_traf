package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import services.UsuarioService;

import java.util.List;
import java.util.Map;

public class UsuarioSteps extends UsuarioService {

    @Quando("eu enviar a requisição para o endpoint {string} de listagem de usuarios")
    public void euEnviarARequisiçãoParaOEndpointDeListagemDeUsuarios(String endpoint) {
        enviarReqGet(endpoint);
        System.out.println("Enviando uma requisição de login para o endpoint: " + endpoint);
    }

    @Dado("que eu tenha os seguintes dados do usuario:")
    public void queEuTenhaOsSeguintesDadosDoUsuario(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            setFieldsUsuario(columns.get("campo"), columns.get("valor"));
        }
        System.out.println("Obtendo dados do usuário...");
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de usuario")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeUsuario(String endpoint) {
        createUsuario(endpoint);
        System.out.println("Enviando uma requisição de cadastro de usuário para o endpoint: " + endpoint);
    }

    @Dado("que eu recupere o ID do usuario criado")
    public void queEuRecupereOIDDoUsuairoCriado() {
        retrieveIdUsuario();
        System.out.println("Recuperando ID do usuario...");
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de usuario")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeUsuario(String endpoint) {
        deleteUsuario(endpoint);
        System.out.println("Enviando uma requisição de deleção de usuario com o ID recuperado para o endpoint: " + endpoint);
    }

    @Dado("que eu tenha os seguintes dados do login:")
    public void queEuTenhaOsSeguintesDadosDoLogin(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            setFieldsLogin(columns.get("campo"), columns.get("valor"));
        }
        System.out.println("Obtendo dados do login...");
    }

    @Quando("eu enviar a requisição para o endpoint {string} de login de usuario")
    public void euEnviarARequisiçãoParaOEndpointDeLoginDeUsuario(String endpoint) {
        createLogin(endpoint);
        System.out.println("Enviando uma requisição de login para o endpoint: " + endpoint);
    }

    @Dado("que eu deixe de enviar um dos campos do usuario:")
    public void queEuDeixeDeEnviarUmDosCamposDoUsuario(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            setFieldsUsuarioWithoutAField(columns.get("campo"), columns.get("valor"));
        }
        System.out.println("Obtendo dados do login...");
    }

    @Dado("que eu realize o login com os mesmos dados cadastrados")
    public void queEuRealizeOLoginComOsMesmosDadosCadastrados() {
        setFieldsLogin();
        System.out.println("Obtendo dados do login...");
    }
}

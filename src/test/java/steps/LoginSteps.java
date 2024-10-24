package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.LoginService;

import java.util.List;
import java.util.Map;

public class LoginSteps {

    LoginService loginService = new LoginService();

    @Dado("que eu tenha os seguintes dados do usuario:")
    public void queEuTenhaOsSeguintesDadosDoUsuario(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            loginService.setFieldsLogin(columns.get("campo"),  columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de login de usuario")
    public void euEnviarARequisiçãoParaOEndpointDeLoginDeUsuario(String endpoint) {
        loginService.createLogin(endpoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, loginService.response.statusCode());
    }
}

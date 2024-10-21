package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroEntregasService;

import java.util.List;
import java.util.Map;

public class CadastroEntregasSteps {

    CadastroEntregasService cadastroEntregasService = new CadastroEntregasService();

    @Dado("que eu tenha os seguintes dados da entrega:")
    public void queEuTenhaOsSeguintesDadosDaEntrega(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            cadastroEntregasService.setFieldsDelivery(columns.get("campo"),  columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de entregas")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeEntregas(String endPoint) {
        cadastroEntregasService.createDelivery(endPoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroEntregasService.response.statusCode());
    }
}


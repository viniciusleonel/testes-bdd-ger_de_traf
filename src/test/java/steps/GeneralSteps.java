package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import org.junit.Assert;
import services.ApiService;

import java.io.IOException;
import java.util.Set;

public class GeneralSteps extends ApiService {

    @Então("o status code da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
        System.out.println("Analisando status code: " + statusCode);
    }

    @Dado("que eu recupere o token retornado ao realizar login")
    public void queEuRecupereOTokenRetornadoAoRealizarLogin() {
        retrieveToken();
        System.out.println("Recuperando token...");
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        setContract(contract);
        System.out.println("Obtendo dados do contrato...");
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
        System.out.println("Analisando resposta da requisição com o contrato selecionado...");
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string} do campo {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message, String field) throws IOException {
        validateMessage(message, field);
        System.out.println("Validando corpo da resposta da requisição...");
    }
}

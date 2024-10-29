package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import services.AcidenteService;

import java.util.List;
import java.util.Map;

public class AcidenteSteps extends AcidenteService {

    @Dado("que eu tenha os seguintes dados do acidente:")
    public void queEuTenhaOsSeguintesDadosDoAcidente(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            setFieldAcidente(columns.get("campo"), columns.get("valor"));
        }
        System.out.println("Obtendo dados do acidente...");
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de acidente")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeAcidente(String endpoint) {
        createAcident(endpoint);
        System.out.println("Enviando uma requisição de cadastro de acidente para o endpoint: " + endpoint);
    }

    @E("que eu tenha os seguintes dados do endereco:")
    public void queEuTenhaOsSeguintesDadosDoEndereco(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            setFieldEndereco(columns.get("campo"), columns.get("valor"));
        }
        System.out.println("Obtendo dados do endereco...");
    }

    @E("que eu tenha os seguintes dados do veiculo:")
    public void queEuTenhaOsSeguintesDadosDoVeiculo(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            setFieldVeiculo(columns.get("campo"), columns.get("valor"));
        }
        System.out.println("Obtendo dados do veiculo...");
    }

    @E("que eu tenha os seguintes dados do ferido:")
    public void queEuTenhaOsSeguintesDadosDoFerido(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            setFieldFerido(columns.get("campo"), columns.get("valor"));
        }
        System.out.println("Obtendo dados do ferido...");
    }

    @E("que eu consiga montar os dados do acidente com os dados obtidos")
    public void queEuConsigaMontarOsDadosDoAcidenteComOsDadosObtidos() {
        montarAcidente();
        System.out.println("Montando dados do acidente...");
    }

    @Quando("eu enviar a requisição para o endpoint {string} de listagem de acidentes")
    public void euEnviarARequisiçãoParaOEndpointDeListagemDeAcidentes(String endpoint) {
        enviarReqGet(endpoint);
        System.out.println("Enviando uma requisição de login para o endpoint: " + endpoint);
    }

    @Dado("que eu recupere o ID do acidente registrado")
    public void queEuRecupereOIDDoAcidenteRegistrado() {
        retrieveIdAcidente();
        System.out.println("Recuperando ID do acidente...");
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de acidente")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeAcidente(String endpoint) {
        deleteAcidente(endpoint);
        System.out.println("Enviando uma requisição de deleção de acidente com o ID recuperado para o endpoint: " + endpoint);
    }
}

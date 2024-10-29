package services;

import io.restassured.http.ContentType;
import model.AcidenteModel;
import model.EnderecoModel;
import model.FeridoModel;
import model.VeiculoModel;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AcidenteService extends ApiService {

    final AcidenteModel acidenteModel = new AcidenteModel();
    final EnderecoModel enderecoModel = new EnderecoModel();
    final List<VeiculoModel> veiculoModel = new ArrayList<>();
    final List<FeridoModel> feridoModel = new ArrayList<>();

    static String idAcidente;

    public void retrieveIdAcidente() {
        idAcidente = String.valueOf(gson.fromJson(response.jsonPath().prettify(), AcidenteModel.class).getIdAcidente());
    }

    public void setFieldAcidente(String field, String value) {
        switch (field) {
            case "dataHora" -> acidenteModel.setDataHora(value);
            case "gravidade" -> acidenteModel.setGravidade(value);
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
    }

    public void setFieldEndereco(String field, String value) {
        switch (field) {
            case "logradouro" -> enderecoModel.setLogradouro(value);
            case "numero" -> enderecoModel.setNumero(Integer.valueOf(value));
            case "bairro" -> enderecoModel.setBairro(value);
            case "cep" -> enderecoModel.setCep(value);
            case "cidade" -> enderecoModel.setCidade(value);
            case "estado" -> enderecoModel.setEstado(value);
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
    }

    public void setFieldVeiculo(String field, String value) {
        if (veiculoModel.isEmpty()) {
            veiculoModel.add(new VeiculoModel());
        }
        VeiculoModel veiculo = veiculoModel.get(veiculoModel.size() - 1);
        switch (field) {
            case "placa" -> veiculo.setPlaca(value);
            case "modelo" -> veiculo.setModelo(value);
            case "ano" -> veiculo.setAno(Integer.valueOf(value));
            case "cor" -> veiculo.setCor(value);
            default -> throw new IllegalStateException("Unexpected field " + field);
        }
    }

    public void setFieldFerido(String field, String value) {
        if (feridoModel.isEmpty()) {
            feridoModel.add(new FeridoModel());
        }
        FeridoModel ferido = feridoModel.get(feridoModel.size() - 1);
        switch (field) {
            case "nome" -> ferido.setNome(value);
            case "cpf" -> ferido.setCpf(value);
            case "gravidade" -> ferido.setGravidade(value);
            default -> throw new IllegalStateException("Unexpected field " + field);
        }
    }

    public void montarAcidente() {
        acidenteModel.setEndereco(enderecoModel);
        acidenteModel.setVeiculos(veiculoModel);
        acidenteModel.setFeridos(feridoModel);
    }

    public void createAcident(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(acidenteModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void deleteAcidente(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idAcidente);
        response = given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }
}

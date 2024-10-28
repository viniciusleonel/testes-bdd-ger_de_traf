package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.*;
import model.response.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class ApiService {

    final UsuarioModel usuarioModel = new UsuarioModel();
    final LoginModel loginModel = new LoginModel();
    private static AcidenteModel acidenteModel = new AcidenteModel();
    final EnderecoModel enderecoModel = new EnderecoModel();
    final List<VeiculoModel> veiculoModel = new ArrayList<>();
    final List<FeridoModel> feridoModel = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    String baseUrl = "https://traffic-incident-api-dev-dtbtfvg2e7e7a8eq.eastus2-01.azurewebsites.net";
    String schemasPath = "src/test/resources/schemas/";

    public Response response;
    String idUsuario;
    String token;
    JSONObject jsonSchema;

    public void setFieldsUsuario(String field, String value) {
        switch (field) {
            case "email" -> usuarioModel.setEmail(value);
            case "senha" -> usuarioModel.setSenha(value);
            case "role" -> usuarioModel.setRole(value);
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
    }

    public void setFieldsLogin(String field, String value) {
        switch (field) {
            case "email" -> loginModel.setEmail(value);
            case "senha" -> loginModel.setSenha(value);
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
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

    public void createUsuario(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(usuarioModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void createLogin(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(loginModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
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

    public void enviarReqGet(String endPoint) {
        String url = baseUrl + endPoint;
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }

    public void retrieveIdUsuario(){
        idUsuario = String.valueOf(gson.fromJson(response.jsonPath().prettify(), ResponseUsuarioModel.class).getId());
    }

    public void retrieveToken(){
        token = String.valueOf(gson.fromJson(response.jsonPath().prettify(), TokenResponse.class).getToken());
    }

    public void deleteUsuario(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idUsuario);
        response = given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            JSONTokener tokener = new JSONTokener(inputStream);
            return new JSONObject(tokener);
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Login bem sucedido" -> jsonSchema = loadJsonFromFile(schemasPath + "login-bem-sucedido.json");
            case "Cadastro bem sucedido" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-usuario.json");
            case "Acidente cadastrado" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-acidente.json");
            default -> throw new IllegalStateException("Unexpected contract: " + contract);
        }
    }

    public void validateMessage(String message, String field) throws IOException {
        switch (field) {
            case "email" -> {
                EmailErrorResponse emailErrorResponse = gson.fromJson(
                    response.jsonPath().prettify(), EmailErrorResponse.class);
                Assert.assertEquals(message, emailErrorResponse.getEmail());
            }
            case "senha" -> {
                SenhaErrorResponse senhaErrorResponse = gson.fromJson(
                        response.jsonPath().prettify(), SenhaErrorResponse.class);
                Assert.assertEquals(message, senhaErrorResponse.getSenha());
            }
            case "message" -> {
                ErrorMessageModel errorMessageModel = gson.fromJson(
                        response.jsonPath().prettify(), ErrorMessageModel.class);
                Assert.assertEquals(message, errorMessageModel.getMessage());
            }
            default ->  throw new IllegalStateException("Unexpected field: " + field);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException
    {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }
}

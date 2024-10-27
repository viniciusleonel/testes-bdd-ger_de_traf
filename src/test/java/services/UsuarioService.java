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
import model.LoginModel;
import model.ResponseUsuarioModel;
import model.TokenResponse;
import model.UsuarioModel;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class UsuarioService {

    final UsuarioModel usuarioModel = new UsuarioModel();
    final LoginModel loginModel = new LoginModel();
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
                .header("Authorization", "Bearer " + token) // Adiciona o cabeçalho de autorização
                .when()
                .delete(url)
                .then()
                .extract()
                .response();

        System.out.println("delete usuario response");
        System.out.println(response.getBody().asString());
        System.out.println(idUsuario);
        System.out.println(token);
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
            default -> throw new IllegalStateException("Unexpected contract" + contract);
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

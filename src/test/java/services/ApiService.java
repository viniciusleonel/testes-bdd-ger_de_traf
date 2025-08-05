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
import model.UsuarioModel;
import model.response.EmailErrorResponse;
import model.response.ErrorMessageModel;
import model.response.SenhaErrorResponse;
import model.response.TokenResponse;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class ApiService {

    static final UsuarioModel usuarioModel = new UsuarioModel();
    static final LoginModel loginModel = new LoginModel();

    static String baseUrl = "http://localhost:8080";

    static String token;
    public static Response response;

    JSONObject jsonSchema;
    String schemasPath = "src/test/resources/schemas/";

    private final ObjectMapper mapper = new ObjectMapper();
    static final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            JSONTokener tokener = new JSONTokener(inputStream);
            return new JSONObject(tokener);
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Login bem sucedido" -> jsonSchema = loadJsonFromFile(schemasPath + "login-bem-sucedido.json");
            case "Cadastro bem sucedido" ->
                    jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-usuario.json");
            case "Acidente cadastrado" ->
                    jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-acidente.json");
            default -> throw new IllegalStateException("Unexpected contract: " + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
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

    public void retrieveToken() {
        token = String.valueOf(gson.fromJson(response.jsonPath().prettify(), TokenResponse.class).getToken());
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
            default -> throw new IllegalStateException("Unexpected field: " + field);
        }
    }
}

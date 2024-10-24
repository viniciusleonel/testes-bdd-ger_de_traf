package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.LoginModel;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class LoginService {

    final LoginModel loginModel = new LoginModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "https://traffic-incident-api-dev-dtbtfvg2e7e7a8eq.eastus2-01.azurewebsites.net";
    String idDelivery;
    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();

    public void setFieldsLogin(String field, String value) {
        switch (field) {
            case "email" -> loginModel.setEmail(value);
            case "senha" -> loginModel.setSenha(value);
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
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
}

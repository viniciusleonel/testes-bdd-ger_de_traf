package services;

import io.restassured.http.ContentType;
import model.response.ResponseUsuarioModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;


public class UsuarioService extends ApiService {

    static String idUsuario;
    static String emailUsuario;
    static String senhaUsuario;

    public void retrieveIdUsuario() {
        idUsuario = String.valueOf(gson.fromJson(response.jsonPath().prettify(), ResponseUsuarioModel.class).getId());
    }

    public void setFieldsUsuario(String field, String value) {
        switch (field) {
            case "email" -> {
                emailUsuario = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + value;
                usuarioModel.setEmail(emailUsuario);
            }
            case "senha" -> {
                senhaUsuario = value;
                usuarioModel.setSenha(senhaUsuario);
            }
            case "role" -> usuarioModel.setRole(value);
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
    }

    public void setFieldsUsuarioWithoutAField(String field, String value) {
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

    public void setFieldsLogin() {
        loginModel.setEmail(emailUsuario);
        loginModel.setSenha(senhaUsuario);

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

}

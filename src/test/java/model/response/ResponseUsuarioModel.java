package model.response;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ResponseUsuarioModel {
    @Expose
    private String id;
    @Expose
    private String email;
    @Expose
    private String role;
}
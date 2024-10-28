package model.response;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class SenhaErrorResponse {

    @Expose
    private String senha;
}
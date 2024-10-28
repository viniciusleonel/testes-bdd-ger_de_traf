package model.response;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class TokenResponse {

    @Expose
    private String token;
}
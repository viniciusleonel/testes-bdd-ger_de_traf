package model.response;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class EmailErrorResponse {

    @Expose
    private String email;
}
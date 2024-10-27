package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class FeridoModel {

    @Expose
    private String nome;

    @Expose
    private String cpf;

    @Expose
    private String gravidade;

}

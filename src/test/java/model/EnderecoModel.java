package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class EnderecoModel {

    @Expose
    private String logradouro;

    @Expose
    private Integer numero;

    @Expose
    private String bairro;

    @Expose
    private String cep;

    @Expose
    private String cidade;

    @Expose
    private String estado;
}

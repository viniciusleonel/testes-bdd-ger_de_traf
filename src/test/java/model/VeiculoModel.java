package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class VeiculoModel {

    @Expose
    private String placa;

    @Expose
    private String modelo;

    @Expose
    private Integer ano;

    @Expose
    private String cor;
}

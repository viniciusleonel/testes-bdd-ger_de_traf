package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AcidenteModel {

    @Expose(serialize = false)
    private String idAcidente;

    @Expose
    private String dataHora;

    @Expose
    private String gravidade;

    @Expose
    private EnderecoModel endereco;

    @Expose
    private List<VeiculoModel> veiculos;

    @Expose
    private List<FeridoModel> feridos;
}

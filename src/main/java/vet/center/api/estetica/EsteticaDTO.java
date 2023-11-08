package vet.center.api.estetica;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EsteticaDTO {
    private Long ProprietarioId;
    private Long animalId;
    private List<Long> produtosIds;
    private List<Long> servicosIds;
    private String recomendacaoConsulta;
    private String observacao;
    private String temperamento;
    private Boolean sedativo;
    private Boolean ouvido;
    private Boolean pele;
    private Boolean ectoparasitas;
    private Boolean medicacao;
    private LocalDateTime horaTermino;
}

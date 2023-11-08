package vet.center.api.domain.cirurgia;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CirurgiaDTO {
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Long atendimentoId;
}

package vet.center.api.domain.cirurgia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vet.center.api.atendimento.Atendimento;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CirurgiaResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal valor;
}

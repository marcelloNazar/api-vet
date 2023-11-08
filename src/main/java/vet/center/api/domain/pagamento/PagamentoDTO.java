package vet.center.api.domain.pagamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {
    private BigDecimal valor;
    private String data;
    private String metodo;
    private Long atendimentoId;
}

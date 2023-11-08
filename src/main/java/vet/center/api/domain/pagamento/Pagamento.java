package vet.center.api.domain.pagamento;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vet.center.api.atendimento.Atendimento;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pagamentos")
@Entity(name = "Pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private String data;
    private String metodo;
    @ManyToOne
    @JoinColumn(name = "atendimento_id", nullable = false)
    @JsonBackReference
    @JsonIgnore
    private Atendimento atendimento;

}

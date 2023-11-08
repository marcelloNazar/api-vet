package vet.center.api.domain.servico;

import jakarta.persistence.*;
import lombok.*;
import vet.center.api.atendimento.Atendimento;

@Entity(name = "AtendimentoServico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "atendimento_id", nullable = false)
    private Atendimento atendimento;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    private int quantidade;

    public AtendimentoServico(Atendimento atendimento, Servico servico, Integer quantidade) {
        this.atendimento = atendimento;
        this.servico = servico;
        this.quantidade = quantidade;
    }
}


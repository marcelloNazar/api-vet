package vet.center.api.domain.produto;

import jakarta.persistence.*;
import lombok.*;
import vet.center.api.atendimento.Atendimento;

@Entity(name = "AtendimentoProduto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "atendimento_id", nullable = false)
    private Atendimento atendimento;
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    private int quantidade;
    public AtendimentoProduto(Atendimento atendimento, Produto produto, Integer quantidade) {
        this.atendimento = atendimento;
        this.produto = produto;
        this.quantidade = quantidade;
    }
}

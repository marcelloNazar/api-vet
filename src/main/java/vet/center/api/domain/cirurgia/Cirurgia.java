package vet.center.api.domain.cirurgia;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vet.center.api.atendimento.Atendimento;
import vet.center.api.domain.proprietario.Proprietario;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cirurgias")
@Entity(name = "Cirurgia")
public class Cirurgia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valor;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "atendimento_id", nullable = true)
    @JsonManagedReference
    @JsonIgnore
    private Atendimento atendimento;
}

package vet.center.api.estetica;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vet.center.api.domain.animal.Animal;
import vet.center.api.domain.produto.Produto;
import vet.center.api.domain.proprietario.Proprietario;
import vet.center.api.domain.servico.Servico;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estetica")
@Entity
public class Estetica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proprietario_id", nullable = false)
    private Proprietario proprietario;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToMany
    @JoinTable(
            name = "estetica_produto",
            joinColumns = @JoinColumn(name = "estetica_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    @JsonManagedReference
    private List<Produto> produtos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "estetica_servico",
            joinColumns = @JoinColumn(name = "estetica_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id"))
    @JsonManagedReference
    private List<Servico> servicos = new ArrayList<>();

    private String recomendacaoConsulta;
    private String observacao;
    private String temperamento;
    private Boolean sedativo;
    private Boolean ouvido;
    private Boolean pele;
    private Boolean ectoparasitas;
    private Boolean medicacao;
    private LocalDateTime horaTermino;
    private BigDecimal total;

}

package vet.center.api.domain.animal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import vet.center.api.atendimento.Atendimento;
import vet.center.api.domain.proprietario.Proprietario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animal")
@Entity(name = "Animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Especie especie;
    private String raca;
    private Sexo sexo;
    private String peso;
    private LocalDate nascimento;
    private String cor;
    private Temperamento temperamento;
    private Boolean castrado;
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "proprietario_id", nullable = false)
    @JsonManagedReference
    private Proprietario proprietario;
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonBackReference
    private List<Atendimento> atendimentos;
}

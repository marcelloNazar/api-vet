package vet.center.api.domain.proprietario;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import vet.center.api.domain.animal.Animal;
import vet.center.api.domain.endereco.Endereco;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proprietarios")
public class Proprietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String telefone1;
    private String telefone2;
    private String cpf;
    private String nascimento;
    private String sexo;
    private String nomeMae;
    private Double divida;
    private String descricao;
    @Embedded
    private Endereco endereco;
    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonBackReference
    private List<Animal> animais;

}

package vet.center.api.domain.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vet.center.api.domain.proprietario.Proprietario;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponseDTO {
    private Long id;
    private String nome;
    private Especie especie;
    private String raca;
    private Sexo sexo;
    private String peso;
    private String idade;
    private String cor;
    private Temperamento temperamento;
    private Boolean castrado;
    private LocalDateTime data;
    private Proprietario proprietario;
}

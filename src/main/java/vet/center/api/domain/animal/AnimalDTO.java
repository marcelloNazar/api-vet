package vet.center.api.domain.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {
    private String nome;
    private Especie especie;
    private String raca;
    private Sexo sexo;
    private String peso;
    private LocalDate nascimento;
    private String cor;
    private Temperamento temperamento;
    private Boolean castrado;
    private Long proprietarioId;
}

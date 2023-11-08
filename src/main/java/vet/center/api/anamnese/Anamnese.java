package vet.center.api.anamnese;

import jakarta.persistence.*;
import lombok.*;
import vet.center.api.atendimento.Atendimento;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "anamnese")
@Entity(name = "anamnese")
public class Anamnese {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long atendimentoId;
    private String anamnese;
    private String estado;
    private String mucosas;
    private String linfonodos;
    private String tpc;
    private String turgorCutaneo;
    private String desidratacao;
    private Boolean ectoparasitas;
    private Boolean mioclonias;
    private String prurido;
    private Boolean vomito;
    private Boolean diarreia;
    private String inapatencia;
    private String secrecoesPatologicas;
    private String calculoDentario;
    private String auscultacaoPulmonar;
    private String auscultacaoCardiaca;
    private String reflexoToce;
    private String emagrecimento;
    private String alteracaoComportamental;
    private String extoscopia;
    private String cavidadeAbdominal;
    private String cabecaPescoco;
    private String sistemaNervoso;
    private String sistemaLocomotor;
    private Boolean cansaco;
    private Boolean tosse;
    private String pulso;
    private String fc;
    private String fr;
    private LocalDateTime data;

}

package vet.center.api.domain.proprietario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vet.center.api.domain.endereco.Endereco;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProprietarioDTO {
    private String nome;
    private String telefone;
    private String telefone1;
    private String telefone2;
    private String cpf;
    private String nascimento;
    private String sexo;
    private String nomeMae;
    private String descricao;
    private Endereco endereco;
}

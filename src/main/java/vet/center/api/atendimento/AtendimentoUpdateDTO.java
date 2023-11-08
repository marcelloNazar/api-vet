package vet.center.api.atendimento;

import lombok.Data;
import vet.center.api.domain.cirurgia.Cirurgia;
import vet.center.api.domain.cirurgia.CirurgiaDTO;
import vet.center.api.domain.produto.AtendimentoProdutoDTO;
import vet.center.api.domain.servico.AtendimentoServicoDTO;

import java.util.List;

@Data
public class AtendimentoUpdateDTO {
    private Boolean concluido;
    private List<AtendimentoProdutoDTO> atendimentoProdutos;
    private List<AtendimentoServicoDTO> atendimentoServicos;
    private List<Cirurgia> cirurgias;
}

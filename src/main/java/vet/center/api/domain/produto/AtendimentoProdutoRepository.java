package vet.center.api.domain.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import vet.center.api.atendimento.Atendimento;

public interface AtendimentoProdutoRepository extends JpaRepository<AtendimentoProduto, Long> {
    void deleteByAtendimentoId(Long id);

    void deleteByAtendimento(Atendimento atendimento);
}

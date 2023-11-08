package vet.center.api.domain.servico;

import org.springframework.data.jpa.repository.JpaRepository;
import vet.center.api.atendimento.Atendimento;

public interface AtendimentoServicoRepository extends JpaRepository<AtendimentoServico, Long> {
    void deleteByAtendimentoId(Long id);

    void deleteByAtendimento(Atendimento atendimento);
}

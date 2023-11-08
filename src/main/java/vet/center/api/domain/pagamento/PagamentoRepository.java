package vet.center.api.domain.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByAtendimentoId(Long atendimentoId);
}

package vet.center.api.domain.cirurgia;

import org.springframework.data.jpa.repository.JpaRepository;
import vet.center.api.domain.animal.Animal;

import java.util.List;

public interface CirurgiaRepository  extends JpaRepository<Cirurgia, Long> {
    List<Cirurgia> findByAtendimentoId(Long atendimentoId);
}

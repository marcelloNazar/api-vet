package vet.center.api.anamnese;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnamneseRepository extends JpaRepository<Anamnese, Long> {
    Anamnese findAnamneseByAtendimentoId(Long atendimentoId);
}

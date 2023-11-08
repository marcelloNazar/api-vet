package vet.center.api.atendimento;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    Page<Atendimento> findAllByVeterinarioIdAndConcluidoFalse(Long veterinarioId, Pageable pageable);
    Page<Atendimento> findAllByConcluidoTrueAndPagoFalse(Pageable pageable);
    Page<Atendimento> findAllByPagoTrue(Pageable pageable);
    Page<Atendimento> findAllByConcluidoFalse(Pageable pageable);
    Page<Atendimento> findAllByFinalizadoTrue(Pageable pageable);
    Page<Atendimento> findAllByConcluidoTrueAndFinalizadoFalse(Pageable pageable);
    Page<Atendimento> findAllByVeterinarioIdAndConcluidoTrue(Long veterinarioId, Pageable pageable);
    Page<Atendimento> findAllByVeterinarioIdAndConcluidoTrueAndFinalizadoFalse(Long veterinarioId, Pageable pageable);
    Page<Atendimento> findAllByFinalizadoTrueAndPagoFalse(Pageable pageable);
    @Query("SELECT a FROM Atendimento a WHERE a.data BETWEEN :startOfMonth AND :endOfMonth AND a.pago = true")
    Page<Atendimento> findAllByDataBetweenAndPago(LocalDate startOfMonth, LocalDate endOfMonth, Pageable pageable);
    @Query("SELECT a FROM Atendimento a WHERE a.data BETWEEN :startOfMonth AND :endOfMonth AND a.pago = false AND a.finalizado = true")
    Page<Atendimento> findAllByDataBetweenAndPagoFalseAndFinalizadoTrue(LocalDate startOfMonth, LocalDate endOfMonth, Pageable pageable);
    @Query("SELECT a FROM Atendimento a WHERE a.data BETWEEN :startOfMonth AND :endOfMonth AND a.finalizado = true")
    Page<Atendimento> findAllByDataBetweenAndFinalizadoTrue(LocalDate startOfMonth, LocalDate endOfMonth, Pageable pageable);

}

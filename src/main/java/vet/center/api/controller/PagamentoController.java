package vet.center.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vet.center.api.domain.animal.AnimalResponseDTO;
import vet.center.api.domain.pagamento.Pagamento;
import vet.center.api.domain.pagamento.PagamentoDTO;
import vet.center.api.domain.pagamento.PagamentoService;
import vet.center.api.domain.proprietario.Proprietario;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/pagamento")
@SecurityRequirement(name = "bearer-key")
@PreAuthorize("hasRole('ADMIN')")
public class PagamentoController {


    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<Pagamento> cadastrar(@RequestBody PagamentoDTO pagamentoDTO) {
        return ResponseEntity.status(CREATED).body(pagamentoService.criarPagamento(pagamentoDTO));
    }

    @GetMapping("/{atendimentoId}")
    public List<Pagamento> getAnimalsByProprietarioId(@PathVariable(value = "atendimentoId") Long atendimentoId) {
        return pagamentoService.getPagamentosPorAtendimento(atendimentoId);
    }
}

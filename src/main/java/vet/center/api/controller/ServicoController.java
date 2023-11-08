package vet.center.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.center.api.domain.servico.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/servico")
@SecurityRequirement(name = "bearer-key")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<Servico> createServico(@RequestBody Servico servico) {
        return ResponseEntity.status(CREATED).body(servicoService.createServico(servico));
    }

    @GetMapping
    public ResponseEntity<Page<Servico>> getAllServicos(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "500") Integer size,
            @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(servicoService.getAllServicos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServicoById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(servicoService.getServicoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> updateServico(@PathVariable(value = "id") Long id, @RequestBody Servico servicoDetails) {
        return ResponseEntity.ok(servicoService.updateServico(id, servicoDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable(value = "id") Long id) {
        servicoService.deleteServico(id);
        return ResponseEntity.noContent().build();
    }
}

package vet.center.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.center.api.atendimento.*;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/atendimento")
@SecurityRequirement(name = "bearer-key")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping
    public ResponseEntity<AtendimentoResponseDTO> createAtendimento(@RequestBody AtendimentoDTO atendimentoDTO) {
        return ResponseEntity.status(CREATED).body(atendimentoService.createAtendimentoVeterinario(atendimentoDTO));
    }


    @GetMapping("/lista")
    public ResponseEntity<Page<AtendimentoResponseDTO>> getAllAtendimentos(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "50") Integer size,
            @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(atendimentoService.getAllAtendimentos(pageable));
    }
    @GetMapping("/concluidos")
    public ResponseEntity<Page<AtendimentoResponseDTO>> Concluidos(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "50") Integer size,
            @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(atendimentoService.getAllAtendimentosConcluidosUser(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendimento> getAtendimento(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(atendimentoService.getAtendimentoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoResponseDTO> updateAtendimento(@PathVariable(value = "id") Long id, @RequestBody AtendimentoUpdateDTO atendimentoDTO) {
        return ResponseEntity.ok(atendimentoService.updateAtendimento(id, atendimentoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtendimento(@PathVariable Long id) {
        atendimentoService.deleteAtendimento(id);
        return ResponseEntity.noContent().build();
    }
}

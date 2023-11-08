package vet.center.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.center.api.domain.proprietario.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/proprietario")
@SecurityRequirement(name = "bearer-key")
public class ProprietarioController {

    @Autowired
    private ProprietarioService proprietarioService;

    @PostMapping
    public ResponseEntity<Proprietario> cadastrar(@RequestBody Proprietario proprietario) {
        return ResponseEntity.status(CREATED).body(proprietarioService.createProprietario(proprietario));
    }

    @GetMapping
    public ResponseEntity<Page<Proprietario>> listar(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "1000") Integer size,
            @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(proprietarioService.getAllProprietarios(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(proprietarioService.getProprietarioById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long id, @Valid @RequestBody Proprietario proprietario) {
        return ResponseEntity.ok(proprietarioService.updateProprietario(id, proprietario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        proprietarioService.deleteProprietario(id);
        return ResponseEntity.noContent().build();
    }
}

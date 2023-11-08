package vet.center.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.center.api.estetica.Estetica;
import vet.center.api.estetica.EsteticaDTO;
import vet.center.api.estetica.EsteticaService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/estetica")
@SecurityRequirement(name = "bearer-key")
public class EsteticaController {

    @Autowired
    private EsteticaService esteticaService;

    @PostMapping
    public ResponseEntity<Estetica> createEstetica(@RequestBody EsteticaDTO esteticaDTO) {
        return ResponseEntity.status(CREATED).body(esteticaService.createEstetica(esteticaDTO));
    }

    @GetMapping
    public ResponseEntity<Page<Estetica>> getAllEstetica(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(esteticaService.getAllEstetica(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estetica> getEsteticaById(@PathVariable Long id) {
        return ResponseEntity.ok(esteticaService.getEsteticaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estetica> updateEstetica(@PathVariable Long id, @RequestBody EsteticaDTO esteticaDTO) {
        return ResponseEntity.ok(esteticaService.updateEstetica(id, esteticaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstetica(@PathVariable Long id) {
        esteticaService.deleteEstetica(id);
        return ResponseEntity.noContent().build();
    }
}

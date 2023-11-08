package vet.center.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vet.center.api.domain.cirurgia.Cirurgia;
import vet.center.api.domain.cirurgia.CirurgiaDTO;
import vet.center.api.domain.cirurgia.CirurgiaResponseDTO;
import vet.center.api.domain.cirurgia.CirurgiaService;

import java.util.List;

@RestController
@RequestMapping("/cirurgia")
@SecurityRequirement(name = "bearer-key")
public class CirurgiaController {

    @Autowired
    private CirurgiaService cirurgiaService;

    @PostMapping
    public ResponseEntity<CirurgiaResponseDTO> createCirurgia(@Valid @RequestBody CirurgiaDTO cirurgiaDTO, UriComponentsBuilder uriComponentsBuilder) {
        var animal = cirurgiaService.createCirurgia(cirurgiaDTO);
        var uri = uriComponentsBuilder.path("/animal/{id}").buildAndExpand(animal.getId()).toUri();
        return ResponseEntity.created(uri).body(animal);
    }

    @GetMapping("/atendimento/{atendimentoId}")
    public List<CirurgiaResponseDTO> getCirurgiasByProprietarioId(@PathVariable(value = "atendimentoId") Long atendimentoId) {
        return cirurgiaService.getCirurgiasByAtendimentoId(atendimentoId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cirurgia> getCirurgiaById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(cirurgiaService.getCirurgiaById(id));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCirurgia(@PathVariable(value = "id") Long id) {
        cirurgiaService.deleteCirurgia(id);
        return ResponseEntity.noContent().build();
    }


}

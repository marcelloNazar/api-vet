package vet.center.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.center.api.anamnese.Anamnese;
import vet.center.api.anamnese.AnamneseDTO;
import vet.center.api.anamnese.AnamneseService;
;import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/anamnese")
@SecurityRequirement(name = "bearer-key")
public class AnamneseController {

    @Autowired
    private AnamneseService anamneseService;

    @PostMapping
    public ResponseEntity<Anamnese> createAnamnese(@RequestBody AnamneseDTO anamneseDTO) {
        return ResponseEntity.status(CREATED).body(anamneseService.createAnamnese(anamneseDTO));
    }

    @GetMapping
    public ResponseEntity<Page<Anamnese>> getAllAnamneses(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(anamneseService.getAllAnamneses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anamnese> getAnamneseById(@PathVariable Long id) {
        return ResponseEntity.ok(anamneseService.getAnamneseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Anamnese> updateAnamnese(@PathVariable Long id, @RequestBody AnamneseDTO anamneseDTO) {
        return ResponseEntity.ok(anamneseService.updateAnamnese(id, anamneseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnamnese(@PathVariable Long id) {
        anamneseService.deleteAnamnese(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/atendimento/{atendimentoId}")
    public ResponseEntity<Anamnese> getAnamnesesByAtendimentoId(@PathVariable Long atendimentoId) {
        return ResponseEntity.ok(anamneseService.getAnamnesesByAtendimentoId(atendimentoId));
    }

}

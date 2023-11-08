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
import org.springframework.web.util.UriComponentsBuilder;
import vet.center.api.domain.animal.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/animal")
@SecurityRequirement(name = "bearer-key")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalResponseDTO> createAnimal(@Valid @RequestBody AnimalDTO animalDTO, UriComponentsBuilder uriComponentsBuilder) {
        var animal = animalService.createAnimal(animalDTO);
        var uri = uriComponentsBuilder.path("/animal/{id}").buildAndExpand(animal.getId()).toUri();
        return ResponseEntity.created(uri).body(animal);
    }

    @GetMapping
    public ResponseEntity<Page<AnimalResponseDTO>> getAllAnimals(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "500") Integer size,
            @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(animalService.getAllAnimals(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(animalService.getAnimalById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> updateAnimal(@PathVariable(value = "id") Long id, @RequestBody AnimalDTO animalDetails) {
        return ResponseEntity.ok(animalService.updateAnimal(id, animalDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable(value = "id") Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/proprietario/{proprietarioId}")
    public List<AnimalResponseDTO> getAnimalsByProprietarioId(@PathVariable(value = "proprietarioId") Long proprietarioId) {
        return animalService.getAnimalsByProprietarioId(proprietarioId);
    }
}

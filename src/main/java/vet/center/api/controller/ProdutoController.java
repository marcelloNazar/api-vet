package vet.center.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vet.center.api.domain.produto.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/produto")
@SecurityRequirement(name = "bearer-key")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        return ResponseEntity.status(CREATED).body(produtoService.createProduto(produto));
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> getAllProdutos(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "500") Integer size,
            @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(produtoService.getAllProdutos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(produtoService.getProdutoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable(value = "id") Long id, @RequestBody Produto produtoDetails) {
        return ResponseEntity.ok(produtoService.updateProduto(id, produtoDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable(value = "id") Long id) {
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.demo.controller;

import com.example.demo.model.Movimentacao;
import com.example.demo.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimentacoes")
@CrossOrigin("*")
public class MovimentacaoControlller {
    @Autowired
    private MovimentacaoService service;

    @GetMapping
    public ResponseEntity<Page<Movimentacao>> getAll(@PageableDefault(size = 10) Pageable page) {
        return ResponseEntity.ok(service.listarMovimentacoes(page));
    }

    @GetMapping("/container/{idContainer}")
    public ResponseEntity<Page<Movimentacao>> getByIdContainer(@PathVariable Long idContainer,
            @PageableDefault(size = 10) Pageable page) {
        return ResponseEntity.ok(service.buscarPorIdContainer(idContainer, page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> getById(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movimentacao> post(@RequestBody Movimentacao movimentacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.incluir(movimentacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimentacao> post(@RequestBody Movimentacao movimentacao, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.atualizar(movimentacao, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.excluir(id);
    }
}

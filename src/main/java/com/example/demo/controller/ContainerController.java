package com.example.demo.controller;

import com.example.demo.model.Container;
import com.example.demo.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/containers")
@CrossOrigin("*")
public class ContainerController {
    @Autowired
    private ContainerService service;

    @GetMapping
    public ResponseEntity<Page<Container>> getAll(@PageableDefault(size = 10)Pageable page) {
        return ResponseEntity.ok(service.listarContainers(page));
    }

    @GetMapping("/relatorio")
    public ResponseEntity<Map<String, Object>> getRelatorio(@PageableDefault(size = 10)Pageable page) {
        return ResponseEntity.ok(service.listarRelatorio(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Container> getById(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Container> post(@RequestBody Container container) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.incluir(container));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Container> post(@RequestBody Container container, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.atualizar(container, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.excluir(id);
    }
}

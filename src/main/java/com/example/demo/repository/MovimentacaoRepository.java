package com.example.demo.repository;

import com.example.demo.model.Movimentacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    Page<Movimentacao> findByContainerId(Long idContainer, Pageable pageable);
}

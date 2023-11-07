package com.example.demo.repository;

import com.example.demo.model.Container;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {
    @Query(nativeQuery = true, value = "SELECT C.cliente AS cliente, M.movimentacao_tipo AS tipoMovimentacao, COUNT(*) AS quantidade " +
            "FROM containers C " +
            "INNER JOIN movimentacoes M ON M.container_id = C.id " +
            "GROUP BY C.cliente, M.movimentacao_tipo " +
            "HAVING COUNT(*) > 0;")
    Page<Object[]> gerarRelatorio(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT C.categoria AS categoria, COUNT(*) AS quantidade " +
            "FROM containers C " +
            "GROUP BY C.categoria")
    List<Object[]> gerarSumarioCategoria();
}

package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Container;
import com.example.demo.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContainerService {

    @Autowired
    private ContainerRepository repository;

    public Page<Container> listarContainers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Container> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Container incluir(Container container) {
        return repository.save(container);
    }

    public Container atualizar(Container novoContainer, Long id) {
        Optional<Container> containerExistente = repository.findById(id);

        if(containerExistente.isPresent()) {
            Container containerAtual = containerExistente.get();

            containerAtual.setNumContainer(novoContainer.getNumContainer());
            containerAtual.setTipo(novoContainer.getTipo());
            containerAtual.setCliente(novoContainer.getCliente());
            containerAtual.setStatus(novoContainer.getStatus());
            containerAtual.setCategoria(novoContainer.getCategoria());

            return repository.save(containerAtual);
        } else {
            throw new NotFoundException();
        }
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Map<String, Object> listarRelatorio(Pageable pageable) {
        Map<String, Object> relatorioCompleto = new HashMap<>();

        Page<Object[]> relatorio = repository.gerarRelatorio(pageable);
        List<Object[]> sumario = repository.gerarSumarioCategoria();

        Page<Map<String, Object>> relatorioList = relatorio.map(obj -> {
            Map<String, Object> relatorioItem = new HashMap<>();

            relatorioItem.put("cliente", obj[0]);
            relatorioItem.put("tipoMovimentacao", obj[1]);
            relatorioItem.put("quantidade", obj[2]);

            return relatorioItem;
        });

        relatorioCompleto.put("relatorio", relatorioList);

        List<Map<String, Object>> sumarioList = new ArrayList<>();

        for(Object[] obj : sumario) {
            Map<String, Object> sumarioItem = new HashMap<>();

            sumarioItem.put("categoria", obj[0]);
            sumarioItem.put("quantidade", obj[1]);

            sumarioList.add(sumarioItem);
        }

        relatorioCompleto.put("sumario", sumarioList);

        return relatorioCompleto;
    }
}

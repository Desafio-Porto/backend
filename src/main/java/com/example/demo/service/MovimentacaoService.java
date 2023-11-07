package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Movimentacao;
import com.example.demo.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository repository;

    public Page<Movimentacao> listarMovimentacoes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Movimentacao> buscarPorIdContainer(Long idContainer, Pageable pageable) {
        return repository.findByContainerId(idContainer, pageable);
    }

    public Optional<Movimentacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Movimentacao incluir(Movimentacao movimentacao) {
        return repository.save(movimentacao);
    }

    public Movimentacao atualizar(Movimentacao novaMovimentacao, Long id) {
        Optional<Movimentacao> movimentacaoExistente = repository.findById(id);

        if(movimentacaoExistente.isPresent()) {
            Movimentacao movimentacaoAtual = movimentacaoExistente.get();

            movimentacaoAtual.setMovimentacaoTipo(novaMovimentacao.getMovimentacaoTipo());
            movimentacaoAtual.setDataHoraInicio(novaMovimentacao.getDataHoraInicio());
            movimentacaoAtual.setDataHoraTermino(novaMovimentacao.getDataHoraTermino());

            return repository.save(movimentacaoAtual);
        } else {
            throw new NotFoundException();
        }
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}

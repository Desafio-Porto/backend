package com.example.demo.model;

import com.example.demo.enums.MovimentacaoTipo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes")
@Data
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private MovimentacaoTipo movimentacaoTipo;

    @NotNull
    private LocalDateTime dataHoraInicio;

    @NotNull
    private LocalDateTime dataHoraTermino;

    @ManyToOne
    @JsonIgnoreProperties("movimentacao")
    private Container container;
}

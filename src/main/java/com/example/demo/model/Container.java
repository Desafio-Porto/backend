package com.example.demo.model;

import com.example.demo.enums.ContainerCategoria;
import com.example.demo.enums.ContainerStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "containers")
@Data
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cliente;

    @NotNull
    @Pattern(regexp = "[A-Z]{4}\\d{7}")
    private String numContainer;

    @NotNull
    @Pattern(regexp = "^(20|40)$")
    private String tipo;

    @NotNull
    private ContainerStatus status;

    @NotNull
    private ContainerCategoria categoria;

    @OneToMany(mappedBy = "container", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("container")
    private List<Movimentacao> movimentacao;
}

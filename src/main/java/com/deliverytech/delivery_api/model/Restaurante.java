package com.deliverytech.delivery_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;
    private String endereco;
    private String telefone;
    private BigDecimal taxaEntrega;
    private Boolean ativo;
    private BigDecimal avaliacao;

    //Relacionamento com o Pedido e com o Produto
    //tratativa através do @JsonIhnore para evitar problemas de serialização
    @JsonIgnore
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

}
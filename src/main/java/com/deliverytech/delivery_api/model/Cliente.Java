package com.deliverytech.delivery_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data 
@Entity
public class Cliente {

    /*
    atraves das anotações @Id e @GeneratedValue, definimos que o atributo id é a chave primária da tabela Cliente e que
     seu valor será gerado automaticamente pelo banco de dados.
    */

    @Id /* chave primaria do banco de dados */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private boolean ativo;


    /*
    TO-Do: add Data em que o Cliente foi cadastrado no sistema
    private LocalDateTime dataCadastro;
    VER UUIDV4, UUID V7, SNOWFLAKE...
    */

    /*
    @JsonIgnore evita LazyInitialization ao seriealizae o Cliente em Json,
    já que "pedidos" é uma coleção Lazy e a sessão do hibernate já está fechado
    quando o Json tenta serializar (Open-in-view=false)
    */

    @JsonIgnore 
    @OneToMany (mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
}
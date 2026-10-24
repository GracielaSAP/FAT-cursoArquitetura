package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.model.Pedido;

import com.deliverytech.delivery_api.enums.StatusPedido;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/*Anotação não importa pois o HPA já interpreta
automaticamente que não é necessaria realizar a anaotação @Repository

Diferença entre com a anaotação ou sem, vem da clareza semântica
eplicita é a outra se trata de maneira implícita (pelo nome/extensão)
porém não se faz necessário a utilização

*/

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    //buscar pedido por cliente
    List<Pedido> findByClienteOrderByDataPedidoDesc(Cliente cliente);

    //buscar pedidos por cliente ID
    List<Pedido> findByClienteIdOrderByDataPedidoDesc(Long clienteId);

    /*
    busca pedidos por cliente ID já trazendo os itens junto (fetch join).
    usamos DISTINCT porque o JOIN FETCH com uma coleção pode gerar
    linhas repetidas do esmo pedido (uma por item), e o DISTINCT 
    remove essas duplicadas no resultado final.
     */
    @Query("SELECT DISTINCT p FROM Pedido p JOIN FETCH p.itens WHERE p.cliente.id = :clienteId ORDER BY p.dataPedido DESC")
    List<Pedido> findByClienteIdWithItens(@Param("clienteId") Long clienteId);

    //buscar pedidos por status
    List<Pedido> findByStatusPedidoOrderByDataPedidoDesc(StatusPedido statusPedido);   

    //buscar por numero do pedido
    Pedido findByNumeroPedido(String numeroPedido);

    /*
    bucar pedido por ID já trazendo os itens junto (FETCH JOIN).
    isso evita o LazyInitializationException ao serializar o pedido em Json,
    já que a coleção "itens" é carregada dentro da própria consulta,
    ainda dentro da transação.
    */
    @Query ("SELECT p FROM Pedido p LEFT JOIN FETCH p.itens WHERE p.id = :id")
    Optional<Pedido> findByIdWithItens(@Param("id") Long id);

    // buscar pedidos por período genérico
    List<Pedido> findByDataPedidoBetweenOrderByDataPedidoDesc(LocalDateTime inicio, LocalDateTime fim);
 
    // buscar pedidos por restaurante
    @Query("SELECT p FROM Pedido p WHERE p.restaurante.id = :restauranteId ORDER BY p.dataPedido DESC")
    List<Pedido> findByRestauranteId(@Param("restauranteId") Long restauranteId);
 
    // relatório - pedidos por status
    @Query("SELECT p.statusPedido, COUNT(p) FROM Pedido p GROUP BY p.statusPedido")
    List<Object[]> countPedidosByStatus();
 
    // pedidos pendentes (para dashboard)
    @Query("SELECT p FROM Pedido p WHERE p.statusPedido IN ('PENDENTE', 'CONFIRMADO', 'PREPARANDO') " +
           "ORDER BY p.dataPedido ASC")
    List<Pedido> findPedidosPendentes();
 
    // CORREÇÃO: buscar pedidos de um dia específico usando os parâmetros informados
    @Query("SELECT p FROM Pedido p WHERE p.dataPedido >= :inicioDia AND p.dataPedido <= :fimDia ORDER BY p.dataPedido DESC")
    List<Pedido> findPedidosDoDia(
            @Param("inicioDia") LocalDateTime inicioDia,
            @Param("fimDia") LocalDateTime fimDia);    
}


package com.deliverytech.delivery_api.controller;
 
import com.deliverytech.delivery_api.model.Cliente;

import com.deliverytech.delivery_api.dto.req.ClienteReqDTO;
import com.deliverytech.delivery_api.dto.res.ClienteResDTO;
import com.deliverytech.delivery_api.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
/*
Definimos através da annotation de que esse arquivo trabalha com
requisições HTTP/HTTPS
*/
@RestController
@RequestMapping("/clientes")
/*
Permissão que qualquer origem acesse a API, pois está utilizando a expressão "*", que significa "todas as origens". 
Isso é útil em ambientes de desenvolvimento, mas em produção, é recomendável restringir as origens permitidas para melhorar a segurança.
 */
@CrossOrigin(origins = "*") 
@RequiredArgsConstructor
public class ClienteController {


    //injetando o serviço de cliente
    private ClienteService clienteService;

    /*
    Utilizando verbo POST para criar um novo cliente
     */
    @PostMapping 

    /*
    Anotação @Valid para validar o objeto cliente recebido no corpo da requisição
    através da anotação @RequestBody, que por sua vez indica que o objeto cliente será mapeado a partir do corpo da requisição HTTP.
    */
    public ResponseEntity<ClienteResDTO> 
    cadastrarCliente(@Valid 
        @RequestBody ClienteReqDTO dto) {
            ClienteResDTO cliente = clienteService.cadastrarCliente(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }
 
}
package com.deliverytech.delivery_api.dto.req;

import jakarta.validation.constraints.*;

public class ClienteReqDTO {

    /*
    Anotação NoteBlank para não permitir 
    que o usuario passe algo como vazio
    */
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    /*
    Regex Regular expression, para realizar busca e permitir
    que apenas a validade de determinadas entradas ocorram no sistema, podendo ser utilizado
    para busca
    */

    @Pattern (regexp = "^[0-9]{10,11}$", message = "Telefone deve conter apenas digitos e ter DDD + Telefone")
    private String telefone;

    @NotBlank(message = "Endereço é obrigatório")
    @Size(max = 200, message = "O endereço deve ter entre 2 e 200 caracteres")
    private String endereco;

    // Getter e Setter

    public String getNome() { return nome;}
    public void setNome(String nome) { this.nome = nome;}

    public String getEmail() { return email;}
    public void setEmail(String email) { this.email = email;}

    public String getTelefone() { return telefone;}
    public void setTelefone(String telefone) { this.telefone = telefone;}

    public String getEndereco() { return endereco;}
    public void setEndereco(String endereco) { this.endereco = endereco; }

}
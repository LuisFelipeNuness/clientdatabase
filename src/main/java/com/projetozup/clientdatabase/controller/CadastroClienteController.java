package com.projetozup.clientdatabase.controller;

import com.projetozup.clientdatabase.dto.MensagemDTO;
import com.projetozup.clientdatabase.exception.CadastroClienteException;
import com.projetozup.clientdatabase.model.Cliente;
import com.projetozup.clientdatabase.service.CadastroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente")
public class CadastroClienteController {

    @Autowired
    public CadastroClienteService cadastroClienteService;

    /** Metodo que insere um novo cliente na base de dados */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Cliente inserirNovoCliente(@RequestBody @Valid Cliente cliente){
        return cadastroClienteService.inserirNovoCliente(cliente);
    }

    /** Metodo que atualiza um cliente na base de dados */

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Cliente atualizarCliente(@RequestBody @Valid Cliente cliente, @PathVariable @NotNull Long id){
        return cadastroClienteService.atualizarCliente(cliente, id);
    }

    /** Metodo que deleta um cliente da base de dados */

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody MensagemDTO deletarCliente(@PathVariable @NotNull Long id){
        return cadastroClienteService.deletarCliente(id);
    }

    /** Metodo que pesquisa um cliente na base de dados com base no id
     * @return*/

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Cliente visualizarCliente(@PathVariable @NotNull Long id){
       return cadastroClienteService.visualizarCliente(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Cliente> listarClientes(){
         return cadastroClienteService.listarClientes();
    }

}

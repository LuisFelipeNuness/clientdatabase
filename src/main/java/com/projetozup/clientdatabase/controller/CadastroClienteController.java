package com.projetozup.clientdatabase.controller;

import com.projetozup.clientdatabase.exception.CadastroClienteException;
import com.projetozup.clientdatabase.model.Cliente;
import com.projetozup.clientdatabase.service.CadastroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/cadastro")
public class CadastroClienteController {

    @Autowired
    public CadastroClienteService cadastroClienteService;

    /** Metodo que insere um novo cliente na base de dados */

    @PostMapping("/inserirNovoCliente")
    @ResponseBody
    public Cliente inserirNovoCliente(@RequestBody Cliente cliente){
        return cadastroClienteService.inserirNovoCliente(cliente);
    }

    /** Metodo que atualiza um cliente na base de dados */

    @PutMapping("/atualizarNovoCliente/{id}")
    @ResponseBody
    public Optional<Cliente> atualizarNovoCliente(@RequestBody Cliente cliente, @PathVariable Long id){
        return cadastroClienteService.atualizarNovoCliente(cliente, id);
    }

    /** Metodo que deleta um cliente da base de dados */

    @DeleteMapping("/deletarCliente/{id}")
    @ResponseBody
    public String deletarCliente(@PathVariable Long id){
        cadastroClienteService.deletarCliente(id);
        return "Usuario deletado com Sucesso!";
    }

    /** Metodo que pesquisa um cliente na base de dados com base no id
     * @return*/

    @GetMapping("/visualizarCliente/{id}")
    @ResponseBody
    public Optional<Cliente> visualizarCliente(@PathVariable Long id){
       return cadastroClienteService.visualizarCliente(id);
    }

    /** Metodo dispara o aviso para as tratativas presentes no codigo */

    @ExceptionHandler(CadastroClienteException.class)
    public String errorPage() {
        return "Os campos são obrigatórios";
    }

}

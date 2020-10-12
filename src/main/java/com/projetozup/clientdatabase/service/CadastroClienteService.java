package com.projetozup.clientdatabase.service;

import com.projetozup.clientdatabase.exception.CadastroClienteException;
import com.projetozup.clientdatabase.model.Cliente;
import com.projetozup.clientdatabase.repository.CadastroClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroClienteService {

    @Autowired
    private CadastroClienteRepository cadastroClienteRepository;

    /** Service que faz a validação para verificar a integridade dos campos passados pelo request para o serviço de inserir novo cliente */

    public Cliente inserirNovoCliente(Cliente cliente) {
        try {
            if (cliente.getNome().isEmpty() || cliente.getCpf().isEmpty() || cliente.getEndereço().isEmpty() || cliente.getDataDeNascimento() == null) {
                throw new CadastroClienteException();
            }

            return cadastroClienteRepository.save(cliente);

        } catch (NullPointerException e){
            throw new CadastroClienteException();
        }
    }

    /** Service que faz a validação para verificar a integridade dos campos passados pelo request para o serviço de atualizar um cliente
     * @return*/

    public Optional<Cliente> atualizarNovoCliente(Cliente cliente, Long id ) {

        try {
            if (cliente.getNome().isEmpty() || cliente.getCpf().isEmpty() || cliente.getEndereço().isEmpty() || cliente.getDataDeNascimento() == null) {
                throw new CadastroClienteException();
            }

        return  cadastroClienteRepository.findById(id)
                .map(clienteNovo -> {
                    clienteNovo.setNome(cliente.getNome());
                    clienteNovo.setCpf(cliente.getCpf());
                    clienteNovo.setDataDeNascimento(cliente.getDataDeNascimento());
                    clienteNovo.setEndereço(cliente.getEndereço());
                    return cadastroClienteRepository.save(clienteNovo);
                });

        } catch (NullPointerException e){
            throw new CadastroClienteException();
        }
    }

    /** Serivce que deleta um cliente da base de dados */

    public void deletarCliente(Long id) {
         try {

             if(id == null){
                 throw new NullPointerException();
             }

             cadastroClienteRepository.deleteById(id);
         } catch (Exception e){
             e.printStackTrace();
         }
    }

    /** Service que consulta um cliente na base de dados
     * @return*/

    public Optional<Cliente> visualizarCliente(Long id) {

            if(id == null){
                throw new NullPointerException();
            }

            return cadastroClienteRepository.findById(id);

    }
}

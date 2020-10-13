package com.projetozup.clientdatabase.service.impl;

import com.projetozup.clientdatabase.dto.MensagemDTO;
import com.projetozup.clientdatabase.exception.CadastroClienteException;
import com.projetozup.clientdatabase.model.Cliente;
import com.projetozup.clientdatabase.repository.CadastroClienteRepository;
import com.projetozup.clientdatabase.service.CadastroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroClienteServiceImpl implements CadastroClienteService {

    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente nao encontrado";
    private static final String USUARIO_NAO_ENCONTRADO = "Usuario removido";

    @Autowired
    private CadastroClienteRepository cadastroClienteRepository;

    /** Service que faz a validação para verificar a integridade dos campos passados pelo request para o serviço de inserir novo cliente */

    public Cliente inserirNovoCliente(Cliente cliente) {

            return cadastroClienteRepository.save(cliente);

    }

    /** Service que faz a validação para verificar a integridade dos campos passados pelo request para o serviço de atualizar um cliente
     * @return*/

    public Cliente atualizarCliente(Cliente cliente, Long id ) {

        return  cadastroClienteRepository.findById(id)
                .map(clienteNovo -> {
                    clienteNovo.setNome(cliente.getNome());
                    clienteNovo.setCpf(cliente.getCpf());
                    clienteNovo.setDataDeNascimento(cliente.getDataDeNascimento());
                    clienteNovo.setEndereco(cliente.getEndereco());
                    return cadastroClienteRepository.save(clienteNovo);
                }).orElseThrow(() ->  new CadastroClienteException(CLIENTE_NAO_ENCONTRADO));

    }

    /** Serivce que deleta um cliente da base de dados */

    public MensagemDTO deletarCliente(Long id) {

        cadastroClienteRepository.deleteById(id);
        return new MensagemDTO(USUARIO_NAO_ENCONTRADO);

    }

    /** Service que consulta um cliente na base de dados
     * @return*/

    public Cliente visualizarCliente(Long id) {

         return cadastroClienteRepository.findById(id).orElseThrow(() -> new CadastroClienteException(CLIENTE_NAO_ENCONTRADO));

    }

    /** Service que lista os clientes da base de dados
     * @return*/

    public List<Cliente> listarClientes() {
        return cadastroClienteRepository.findAll();
    }
}

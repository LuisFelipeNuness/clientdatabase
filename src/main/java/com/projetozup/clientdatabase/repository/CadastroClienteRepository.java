package com.projetozup.clientdatabase.repository;

import com.projetozup.clientdatabase.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroClienteRepository extends JpaRepository<Cliente, Long> {

}

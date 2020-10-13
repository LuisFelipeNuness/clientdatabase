package com.projetozup.clientdatabase.exception;

public class CadastroClienteException extends RuntimeException {

    public String mensagem;

    public CadastroClienteException(String mensagem) {
        super();
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
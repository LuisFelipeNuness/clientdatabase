package com.projetozup.clientdatabase.exception;

import com.projetozup.clientdatabase.dto.ErroDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@ControllerAdvice
public class ClienteExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public @ResponseBody List<ErroDTO> handleValidationError(MethodArgumentNotValidException e) {

        List<ErroDTO> errosDeValidacao = new ArrayList<>();

        for (ObjectError erro : e.getBindingResult().getAllErrors()) {

            if (nonNull(erro.getCodes()) && nonNull(erro.getCodes()[0])) {
                errosDeValidacao.add(new ErroDTO("ERRO_DE_VALIDACAO",erro.getDefaultMessage()));
            }
        }

        return errosDeValidacao;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErroDTO handleAll(Exception e) {
        return new ErroDTO("ERRO_INTERNO", e.getMessage());
    }

    @ExceptionHandler({CadastroClienteException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErroDTO handleErroDeNegocio(Exception e) {
        return new ErroDTO("ERRO_DE_NEGOCIO", "Cliente nao encontrado!");
    }


}

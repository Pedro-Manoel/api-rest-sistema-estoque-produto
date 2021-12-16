package br.com.estoque.exception.controller;

import br.com.estoque.dto.ErrorMessageDTO;
import br.com.estoque.exception.FornecedorExistenteException;
import br.com.estoque.exception.FornecedorNaoExisteException;
import br.com.estoque.exception.ProdutoNaoExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FornecedorExceptionController {

    @ExceptionHandler(FornecedorNaoExisteException.class)
    public ResponseEntity<ErrorMessageDTO> handleException(FornecedorNaoExisteException exception){
        return new ResponseEntity<>(
                new ErrorMessageDTO(exception.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(FornecedorExistenteException.class)
    public ResponseEntity<ErrorMessageDTO> handleException(FornecedorExistenteException exception){
        return new ResponseEntity<>(
                new ErrorMessageDTO(exception.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

}

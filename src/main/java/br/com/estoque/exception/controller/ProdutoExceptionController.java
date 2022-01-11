package br.com.estoque.exception.controller;

import br.com.estoque.dto.ErrorMessageDTO;
import br.com.estoque.exception.ProdutoExistenteException;
import br.com.estoque.exception.ProdutoNaoExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProdutoExceptionController {

    @ExceptionHandler(ProdutoNaoExisteException.class)
    public ResponseEntity<ErrorMessageDTO> handleException(ProdutoNaoExisteException exception){
        return new ResponseEntity<>(
                new ErrorMessageDTO(exception.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ProdutoExistenteException.class)
    public ResponseEntity<ErrorMessageDTO> handleException(ProdutoExistenteException exception){
        return new ResponseEntity<>(
                new ErrorMessageDTO(exception.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}

package br.com.estoque.exception.controller;

import br.com.estoque.dto.ErrorMessageDTO;
import br.com.estoque.exception.FornecedorNaoExisteException;
import br.com.estoque.exception.TipoProdutoExistenteException;
import br.com.estoque.exception.TipoProdutoNaoExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TipoProdutoExceptionController {

    @ExceptionHandler(TipoProdutoNaoExisteException.class)
    public ResponseEntity<ErrorMessageDTO> handleException(TipoProdutoNaoExisteException exception){
        return new ResponseEntity<>(
                new ErrorMessageDTO(exception.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(TipoProdutoExistenteException.class)
    public ResponseEntity<ErrorMessageDTO> handleException(TipoProdutoExistenteException exception){
        return new ResponseEntity<>(
                new ErrorMessageDTO(exception.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

}

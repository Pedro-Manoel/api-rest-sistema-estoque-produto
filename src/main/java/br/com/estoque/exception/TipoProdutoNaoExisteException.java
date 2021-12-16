package br.com.estoque.exception;

public class TipoProdutoNaoExisteException extends RuntimeException {

    public TipoProdutoNaoExisteException (Long id) {
        super(String.format("Tipo produto com id %d não existe", id));
    }
}

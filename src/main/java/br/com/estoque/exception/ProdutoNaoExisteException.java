package br.com.estoque.exception;

public class ProdutoNaoExisteException extends RuntimeException{
    public ProdutoNaoExisteException (Long id) {
        super(String.format("Produto com id %d não existe", id));
    }
}

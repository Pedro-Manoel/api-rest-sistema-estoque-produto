package br.com.estoque.exception;

public class TipoProdutoExistenteException extends RuntimeException{

    public TipoProdutoExistenteException (String nome) {
        super(String.format("Tipo de produto com nome %s já existe", nome));
    }
}

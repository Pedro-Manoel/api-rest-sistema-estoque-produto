package br.com.estoque.exception;

public class TipoProdutoExistenteException extends RuntimeException{

    public TipoProdutoExistenteException (String nome) {
        super(String.format("TipoProduto com nome %s  já existe", nome));
    }

}

package br.com.estoque.exception;

public class ProdutoExistenteException extends RuntimeException {

    public ProdutoExistenteException (String codBarra) {
        super(String.format("Produto com código de barra %s já existe", codBarra));
    }
}

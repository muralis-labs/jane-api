package br.com.muralis.core.domain.exception;

public class ColaboradorNaoEncontradoException extends DomainException {

    public ColaboradorNaoEncontradoException(String id) {
        super(String.format("Colaborador com id %s não encontrado", id), 404);
    }

}

package br.com.muralis.core.domain.exception;

public class ColaboradorCadastradoComEmail extends RuntimeException {

    public ColaboradorCadastradoComEmail(String email) {
        super("Já existe um colaborador cadastrado com o email " + email);
    }

}

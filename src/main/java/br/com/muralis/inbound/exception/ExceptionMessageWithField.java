package br.com.muralis.inbound.exception;

import lombok.Getter;

@Getter
public class ExceptionMessageWithField {

    private final String mensagem;

    private final String campo;

    private ExceptionMessageWithField(String mensagem, String campo) {
        this.mensagem = mensagem;
        this.campo = campo;
    }

    public static ExceptionMessageWithField of(String campo, String mensagem) {
        return new ExceptionMessageWithField(mensagem, campo);
    }

}

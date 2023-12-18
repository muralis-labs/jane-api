package br.com.muralis.inbound.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;

@Getter
@RegisterForReflection
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

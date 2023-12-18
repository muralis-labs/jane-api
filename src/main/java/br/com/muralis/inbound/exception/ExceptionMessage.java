package br.com.muralis.inbound.exception;

import lombok.Getter;

@Getter
public class ExceptionMessage {

	private final String mensagem;

	private ExceptionMessage(String mensagem) {
		this.mensagem = mensagem;
	}

	public static ExceptionMessage of(String mensagem) {
		return new ExceptionMessage(mensagem);
	}

}

package br.com.muralis.inbound.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;

@Getter
@RegisterForReflection
public class ExceptionMessage {

	private final String mensagem;

	private ExceptionMessage(String mensagem) {
		this.mensagem = mensagem;
	}

	public static ExceptionMessage of(String mensagem) {
		if (mensagem != null)
			mensagem = mensagem.trim();
		return new ExceptionMessage(mensagem);
	}

}

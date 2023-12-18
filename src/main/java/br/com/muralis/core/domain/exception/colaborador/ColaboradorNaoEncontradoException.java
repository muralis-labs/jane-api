package br.com.muralis.core.domain.exception.colaborador;

import br.com.muralis.core.domain.exception.DomainException;
import io.quarkus.logging.Log;

public class ColaboradorNaoEncontradoException extends DomainException {

	public ColaboradorNaoEncontradoException(String id) {
		super(String.format("Colaborador com id %s não encontrado", id), 404);
		Log.error("Colaborador com id " + id + " não encontrado");
	}

}

package br.com.muralis.core.domain.exception.colaborador;

import br.com.muralis.core.domain.exception.DomainException;
import io.quarkus.logging.Log;

public class ColaboradorCadastradoComCpf extends DomainException {

	public ColaboradorCadastradoComCpf(String cpf) {
		super("Já existe um colaborador cadastrado com o cpf " + cpf, 400);
		Log.error("Já existe um colaborador cadastrado com o cpf " + cpf);
	}

}

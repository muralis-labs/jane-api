package br.com.muralis.core.domain.exception.empresa;

import br.com.muralis.core.domain.exception.DomainException;
import io.quarkus.logging.Log;

public class EmpresaCadastradaComCNPJ extends DomainException {

	public EmpresaCadastradaComCNPJ(String CNPJ) {
		super("Já existe uma empresa cadastrada com o CNPJ " + CNPJ, 400);
		Log.error("Já existe uma empresa cadastrada com o CNPJ " + CNPJ);
	}

}

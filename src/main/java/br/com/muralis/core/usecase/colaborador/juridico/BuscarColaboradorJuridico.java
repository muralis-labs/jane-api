package br.com.muralis.core.usecase.colaborador.juridico;

import br.com.muralis.core.domain.entity.ColaboradorJuridico;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorNaoEncontradoException;
import br.com.muralis.core.domain.repository.ColaboradorJuridicoRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BuscarColaboradorJuridico {

	@Inject
	ColaboradorJuridicoRepository colaboradorJuridicoRepository;

	public ColaboradorJuridico execute(String id) {
		Log.info("Buscando colaborador com id " + id);
		ColaboradorJuridico colaborador = colaboradorJuridicoRepository.findById(id)
			.orElseThrow(() -> new ColaboradorNaoEncontradoException(id));
		Log.info("Colaborador encontrado: " + colaborador.getEmail());
		return colaborador;
	}

}

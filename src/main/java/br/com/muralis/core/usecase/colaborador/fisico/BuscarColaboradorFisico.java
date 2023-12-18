package br.com.muralis.core.usecase.colaborador.fisico;

import br.com.muralis.core.domain.entity.ColaboradorFisico;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorNaoEncontradoException;
import br.com.muralis.core.domain.repository.ColaboradorFisicoRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BuscarColaboradorFisico {

	@Inject
	ColaboradorFisicoRepository colaboradorFisicoRepository;

	public ColaboradorFisico execute(String id) {
		Log.info("Buscando colaborador com id " + id);
		ColaboradorFisico colaborador = colaboradorFisicoRepository.findById(id)
			.orElseThrow(() -> new ColaboradorNaoEncontradoException(id));
		Log.info("Colaborador encontrado: " + colaborador.getEmail());
		return colaborador;
	}

}

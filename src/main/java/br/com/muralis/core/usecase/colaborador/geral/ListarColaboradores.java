package br.com.muralis.core.usecase.colaborador.geral;

import br.com.muralis.core.domain.entity.Colaborador;
import br.com.muralis.core.domain.repository.ColaboradorRepository;
import br.com.muralis.core.dto.CustomPage;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ListarColaboradores {

	@Inject
	ColaboradorRepository colaboradorRepository;

	public CustomPage<Colaborador> execute(int page, int size) {
		Log.info("Listando colaboradores da página " + page + " com tamanho " + size);
		var colaboradores = colaboradorRepository.findAll(page, size);
		var total = colaboradorRepository.count();
		Log.info("Colaboradores encontrados: " + colaboradores.size());
		return new CustomPage<>(colaboradores, page, size, total);
	}

}
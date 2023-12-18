package br.com.muralis.core.usecase.colaborador.fisico;

import br.com.muralis.core.domain.entity.ColaboradorFisico;
import br.com.muralis.core.domain.repository.ColaboradorFisicoRepository;
import br.com.muralis.core.dto.CustomPage;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ListarColaboradoresFisico {

	@Inject
	ColaboradorFisicoRepository colaboradorFisicoRepository;

	public CustomPage<ColaboradorFisico> execute(int page, int size) {
		Log.info("Listando colaboradores da página " + page + " com tamanho " + size);
		var colaboradores = colaboradorFisicoRepository.findAll(page, size);
		var total = colaboradorFisicoRepository.count();
		Log.info("Colaboradores encontrados: " + colaboradores.size());
		return new CustomPage<>(colaboradores, page, size, total);
	}

}
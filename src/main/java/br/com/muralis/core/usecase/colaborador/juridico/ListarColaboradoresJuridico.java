package br.com.muralis.core.usecase.colaborador.juridico;

import br.com.muralis.core.domain.entity.ColaboradorJuridico;
import br.com.muralis.core.domain.repository.ColaboradorJuridicoRepository;
import br.com.muralis.core.dto.CustomPage;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ListarColaboradoresJuridico {

	@Inject
	ColaboradorJuridicoRepository colaboradorJuridicoRepository;

	public CustomPage<ColaboradorJuridico> execute(int page, int size) {
		Log.info("Listando colaboradores da página " + page + " com tamanho " + size);
		var colaboradores = colaboradorJuridicoRepository.findAll(page, size);
		var total = colaboradorJuridicoRepository.count();
		Log.info("Colaboradores encontrados: " + colaboradores.size());
		return new CustomPage<>(colaboradores, page, size, total);
	}

}
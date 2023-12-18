package br.com.muralis.core.usecase.colaborador.fisico;

import br.com.muralis.core.domain.entity.ColaboradorFisico;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorCadastradoComEmail;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorNaoEncontradoException;
import br.com.muralis.core.domain.repository.ColaboradorFisicoRepository;
import br.com.muralis.core.dto.colaborador.fisico.AtualizarColaboradorFisicoCommand;
import br.com.muralis.core.mapper.ColaboradorFisicoMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Objects;

@ApplicationScoped
public class AtualizarColaboradorFisico {

	@Inject
	ColaboradorFisicoRepository colaboradorFisicoRepository;

	@Inject
	ColaboradorFisicoMapper colaboradorFisicoMapper;

	@Transactional
	public ColaboradorFisico execute(String id, AtualizarColaboradorFisicoCommand command) {
		Log.info("Preparando para atualizar colaborador: " + id);
		ColaboradorFisico colaborador = colaboradorFisicoRepository.findById(id)
			.orElseThrow(() -> new ColaboradorNaoEncontradoException(id));
		if (isNotEmailEquals(command, colaborador) && colaboradorFisicoRepository.existsByEmail(command.getEmail()))
			throw new ColaboradorCadastradoComEmail(command.getEmail());
		ColaboradorFisico colaboradorParaAtualizar = colaboradorFisicoMapper.update(colaborador, command);
		Log.info("ColaboradorFisico preparado para salvar: " + id);
		ColaboradorFisico colaboradorSalvo = colaboradorFisicoRepository.update(colaboradorParaAtualizar);
		Log.info("ColaboradorFisico salvo: " + id);
		return colaboradorSalvo;
	}

	private boolean isNotEmailEquals(AtualizarColaboradorFisicoCommand command, ColaboradorFisico colaborador) {
		return !Objects.equals(colaborador.getEmail(), command.getEmail());
	}

}

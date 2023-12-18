package br.com.muralis.core.usecase.colaborador.juridico;

import br.com.muralis.core.domain.entity.ColaboradorJuridico;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorCadastradoComEmail;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorNaoEncontradoException;
import br.com.muralis.core.domain.repository.ColaboradorJuridicoRepository;
import br.com.muralis.core.dto.colaborador.juridico.AtualizarColaboradorJuridicoCommand;
import br.com.muralis.core.mapper.ColaboradorJuridicoMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Objects;

@ApplicationScoped
public class AtualizarColaboradorJuridico {

	@Inject
	ColaboradorJuridicoRepository colaboradorJuridicoRepository;

	@Inject
	ColaboradorJuridicoMapper colaboradorJuridicoMapper;

	@Transactional
	public ColaboradorJuridico execute(String id, AtualizarColaboradorJuridicoCommand command) {
		Log.info("Preparando para atualizar colaborador: " + id);
		ColaboradorJuridico colaborador = colaboradorJuridicoRepository.findById(id)
			.orElseThrow(() -> new ColaboradorNaoEncontradoException(id));
		if (isNotEmailEquals(command, colaborador) && colaboradorJuridicoRepository.existsByEmail(command.getEmail()))
			throw new ColaboradorCadastradoComEmail(command.getEmail());
		ColaboradorJuridico colaboradorParaAtualizar = colaboradorJuridicoMapper.update(colaborador, command);
		Log.info("ColaboradorJuridico preparado para salvar: " + id);
		ColaboradorJuridico colaboradorSalvo = colaboradorJuridicoRepository.update(colaboradorParaAtualizar);
		Log.info("ColaboradorJuridico salvo: " + id);
		return colaboradorSalvo;
	}

	private boolean isNotEmailEquals(AtualizarColaboradorJuridicoCommand command, ColaboradorJuridico colaborador) {
		return !Objects.equals(colaborador.getEmail(), command.getEmail());
	}

}

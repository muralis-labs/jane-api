package br.com.muralis.core.usecase.colaborador.fisico;

import br.com.muralis.core.domain.entity.ColaboradorFisico;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorCadastradoComCpf;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorCadastradoComEmail;
import br.com.muralis.core.domain.repository.ColaboradorFisicoRepository;
import br.com.muralis.core.dto.colaborador.fisico.CadastrarColaboradorFisicoCommand;
import br.com.muralis.core.mapper.ColaboradorFisicoMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CadastrarColaboradorFisico {

	@Inject
	ColaboradorFisicoRepository colaboradorFisicoRepository;

	@Inject
	ColaboradorFisicoMapper colaboradorFisicoMapper;

	@Transactional
	public ColaboradorFisico execute(CadastrarColaboradorFisicoCommand command) {
		Log.info("Preparando para cadastrar colaborador: " + command.getNome());
		if (command.getEmail() != null && colaboradorFisicoRepository.existsByEmail(command.getEmail()))
			throw new ColaboradorCadastradoComEmail(command.getEmail());
		if (command.getCpf() != null && colaboradorFisicoRepository.existsByCpf(command.getCpf()))
			throw new ColaboradorCadastradoComCpf(command.getCpf());
		ColaboradorFisico colaborador = colaboradorFisicoMapper.from(command);
		colaborador.cadastrar();
		Log.info("Colaborador preparado para salvar: " + colaborador.getNome());
		ColaboradorFisico colaboradorSalvo = colaboradorFisicoRepository.save(colaborador);
		Log.info("Colaborador salvo: " + colaboradorSalvo.getNome());
		return colaboradorSalvo;
	}

}

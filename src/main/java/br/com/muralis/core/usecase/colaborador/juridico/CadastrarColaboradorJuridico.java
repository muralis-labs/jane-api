package br.com.muralis.core.usecase.colaborador.juridico;

import br.com.muralis.core.domain.entity.ColaboradorJuridico;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorCadastradoComCpf;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorCadastradoComEmail;
import br.com.muralis.core.domain.repository.ColaboradorJuridicoRepository;
import br.com.muralis.core.dto.colaborador.juridico.CadastrarColaboradorJuridicoCommand;
import br.com.muralis.core.mapper.ColaboradorJuridicoMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CadastrarColaboradorJuridico {

	@Inject
	ColaboradorJuridicoRepository colaboradorJuridicoRepository;

	@Inject
	ColaboradorJuridicoMapper colaboradorJuridicoMapper;

	@Transactional
	public ColaboradorJuridico execute(CadastrarColaboradorJuridicoCommand command) {
		Log.info("Preparando para cadastrar colaborador: " + command.getNome());
		if (command.getEmail() != null && colaboradorJuridicoRepository.existsByEmail(command.getEmail()))
			throw new ColaboradorCadastradoComEmail(command.getEmail());
		if (command.getCpf() != null && colaboradorJuridicoRepository.existsByCpf(command.getCpf()))
			throw new ColaboradorCadastradoComCpf(command.getCpf());
		ColaboradorJuridico colaborador = colaboradorJuridicoMapper.from(command);
		colaborador.cadastrar();
		Log.info("Colaborador preparado para salvar: " + colaborador.getNome());
		ColaboradorJuridico colaboradorSalvo = colaboradorJuridicoRepository.save(colaborador);
		Log.info("Colaborador salvo: " + colaboradorSalvo.getNome());
		return colaboradorSalvo;
	}

}

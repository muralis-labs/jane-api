package br.com.muralis.core.usecase.empresa;

import br.com.muralis.core.domain.entity.Empresa;
import br.com.muralis.core.domain.exception.empresa.EmpresaNaoEncontrada;
import br.com.muralis.core.domain.repository.EmpresaRepository;
import br.com.muralis.core.dto.empresa.AtualizarEmpresaCommand;
import br.com.muralis.core.mapper.EmpresaMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Objects;

@ApplicationScoped
public class AtualizarEmpresa {

	@Inject
	EmpresaRepository empresaRepository;

	@Inject
	EmpresaMapper empresaMapper;

	@Transactional
	public Empresa execute(String id, AtualizarEmpresaCommand command) {
		Log.info("Preparando para atualizar empresa: " + id);
		Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new EmpresaNaoEncontrada(id));
		if (isNotCNPJEquals(command, empresa) && empresaRepository.existsByCNPJ(command.getCnpj()))
			throw new EmpresaNaoEncontrada(command.getEmail());
		Empresa empresaParaAtualizar = empresaMapper.update(empresa, command);
		Log.info("Empresa preparada para salvar: " + id);
		Empresa empresaSalva = empresaRepository.update(empresaParaAtualizar);
		Log.info("Empresa salva: " + id);
		return empresaSalva;
	}

	private boolean isNotCNPJEquals(AtualizarEmpresaCommand command, Empresa empresa) {
		return !Objects.equals(empresa.getCnpj(), command.getCnpj());
	}

}

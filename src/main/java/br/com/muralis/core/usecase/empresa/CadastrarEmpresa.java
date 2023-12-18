package br.com.muralis.core.usecase.empresa;

import br.com.muralis.core.domain.entity.Empresa;
import br.com.muralis.core.domain.exception.empresa.EmpresaCadastradaComCNPJ;
import br.com.muralis.core.domain.repository.EmpresaRepository;
import br.com.muralis.core.dto.empresa.CadastrarEmpresaCommand;
import br.com.muralis.core.mapper.EmpresaMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CadastrarEmpresa {

	@Inject
	EmpresaRepository empresaRepository;

	@Inject
	EmpresaMapper empresaMapper;

	@Transactional
	public Empresa execute(CadastrarEmpresaCommand command) {
		Log.info("Preparando para cadastrar empresa: " + command.getCnpj());
		if (empresaRepository.existsByCNPJ(command.getCnpj()))
			throw new EmpresaCadastradaComCNPJ(command.getCnpj());
		Empresa empresa = empresaMapper.from(command);
		empresa.cadastrar();
		Log.info("Empresa preparada para salvar: " + empresa.getEmail());
		Empresa empresaSalva = empresaRepository.save(empresa);
		Log.info("Empresa salva: " + empresaSalva.getEmail());
		return empresaSalva;
	}

}

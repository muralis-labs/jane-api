package br.com.muralis.outbound.hibernate.repository;

import br.com.muralis.core.domain.entity.Empresa;
import br.com.muralis.core.domain.repository.EmpresaRepository;
import br.com.muralis.outbound.hibernate.mapper.PanacheEmpresaMapper;
import br.com.muralis.outbound.hibernate.table.PanacheEmpresa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmpresaPanacheRepository implements EmpresaRepository {

	@Inject
	PanacheEmpresaMapper panacheEmpresaMapper;

	@Override
	public Empresa save(Empresa empresa) {
		PanacheEmpresa panacheEmpresa = panacheEmpresaMapper.from(empresa);
		panacheEmpresa.persist();
		return panacheEmpresaMapper.from(panacheEmpresa);
	}

	@Override
	public boolean existsByCNPJ(String cnpj) {
		return PanacheEmpresa.count("cnpj", cnpj) > 0;
	}

	@Override
	public Optional<Empresa> findById(String id) {
		PanacheEmpresa panacheEmpresa = PanacheEmpresa.findById(id);
		if (panacheEmpresa == null) {
			return Optional.empty();
		}
		return Optional.of(panacheEmpresaMapper.from(panacheEmpresa));
	}

	@Override
	public boolean deleteById(String id) {
		return PanacheEmpresa.deleteById(id);
	}

	@Override
	public List<Empresa> findAll(int page, int size) {
		return panacheEmpresaMapper.from(PanacheEmpresa.findAll().page(page, size).list());
	}

	@Override
	public long count() {
		return PanacheEmpresa.count();
	}

	@Override
	public Empresa update(Empresa empresaParaAtualizar) {
		PanacheEmpresa panacheEmpresa = PanacheEmpresa.findById(empresaParaAtualizar.getId());
		panacheEmpresaMapper.update(panacheEmpresa, empresaParaAtualizar);
		return panacheEmpresaMapper.from(panacheEmpresa);
	}

}

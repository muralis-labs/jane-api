package br.com.muralis.core.domain.repository;

import br.com.muralis.core.domain.entity.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository {

	Empresa save(Empresa empresa);

	boolean existsByCNPJ(String CNPJ);

	Optional<Empresa> findById(String id);

	boolean deleteById(String id);

	List<Empresa> findAll(int page, int size);

	long count();

	Empresa update(Empresa empresaParaAtualizar);

}

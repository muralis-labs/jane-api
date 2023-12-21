package br.com.muralis.core.domain.repository;

import br.com.muralis.core.domain.entity.ColaboradorFisico;

import java.util.List;
import java.util.Optional;

public interface ColaboradorFisicoRepository {

	ColaboradorFisico save(ColaboradorFisico colaborador);

	Optional<ColaboradorFisico> findById(String id);

	boolean existsByEmail(String email);

	boolean deleteById(String id);

	List<ColaboradorFisico> findAll(int page, int size);

	long count();

	ColaboradorFisico update(ColaboradorFisico colaboradorParaAtualizar);

	boolean existsByCpf(String cpf);

}

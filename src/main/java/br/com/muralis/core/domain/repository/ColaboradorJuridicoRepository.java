package br.com.muralis.core.domain.repository;

import br.com.muralis.core.domain.entity.ColaboradorJuridico;

import java.util.List;
import java.util.Optional;

public interface ColaboradorJuridicoRepository {

	ColaboradorJuridico save(ColaboradorJuridico colaborador);

	Optional<ColaboradorJuridico> findById(String id);

	boolean existsByEmail(String email);

	boolean deleteById(String id);

	List<ColaboradorJuridico> findAll(int page, int size);

	long count();

	ColaboradorJuridico update(ColaboradorJuridico colaboradorParaAtualizar);

}

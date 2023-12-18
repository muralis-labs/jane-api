package br.com.muralis.core.domain.repository;

import br.com.muralis.core.domain.entity.Colaborador;

import java.util.List;
import java.util.Optional;

public interface ColaboradorRepository {

    List<Colaborador> findAll(int page, int size);

    Optional<Colaborador> findById(String id);

    long count();

}

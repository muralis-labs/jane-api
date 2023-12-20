package br.com.muralis.outbound.hibernate.repository;

import br.com.muralis.core.domain.entity.ColaboradorJuridico;
import br.com.muralis.core.domain.repository.ColaboradorJuridicoRepository;
import br.com.muralis.outbound.hibernate.mapper.PanacheColaboradorJuridicoMapper;
import br.com.muralis.outbound.hibernate.table.PanacheColaboradorJuridico;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ColaboradorPanacheJuridicoRepository implements ColaboradorJuridicoRepository {

	@Inject
	PanacheColaboradorJuridicoMapper panacheColaboradorJuridicoMapper;

	@Override
	@Transactional
	public ColaboradorJuridico save(ColaboradorJuridico colaborador) {
		PanacheColaboradorJuridico panacheColaborador = panacheColaboradorJuridicoMapper.from(colaborador);
		panacheColaborador.persist();
		return panacheColaboradorJuridicoMapper.from(panacheColaborador);
	}

	@Override
	public Optional<ColaboradorJuridico> findById(String id) {
		PanacheColaboradorJuridico panacheColaborador = PanacheColaboradorJuridico.findById(id);
		if (panacheColaborador == null) {
			return Optional.empty();
		}
		return Optional.of(panacheColaboradorJuridicoMapper.from(panacheColaborador));
	}

	@Override
	public boolean existsByEmail(String email) {
		return PanacheColaboradorJuridico.count("email", email) > 0;
	}

	@Override
	public boolean deleteById(String id) {
		return PanacheColaboradorJuridico.deleteById(id);
	}

	@Override
	public List<ColaboradorJuridico> findAll(int page, int size) {
		return panacheColaboradorJuridicoMapper.from(PanacheColaboradorJuridico.findAll().page(page, size).list());
	}

	@Override
	public long count() {
		return PanacheColaboradorJuridico.count();
	}

	@Override
	@Transactional
	public ColaboradorJuridico update(ColaboradorJuridico colaboradorParaAtualizar) {
		PanacheColaboradorJuridico panacheColaborador = PanacheColaboradorJuridico
			.findById(colaboradorParaAtualizar.getId());
		panacheColaboradorJuridicoMapper.update(panacheColaborador, colaboradorParaAtualizar);
		return panacheColaboradorJuridicoMapper.from(panacheColaborador);
	}

}

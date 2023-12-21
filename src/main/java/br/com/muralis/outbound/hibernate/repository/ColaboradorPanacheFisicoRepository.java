package br.com.muralis.outbound.hibernate.repository;

import br.com.muralis.core.domain.entity.ColaboradorFisico;
import br.com.muralis.core.domain.repository.ColaboradorFisicoRepository;
import br.com.muralis.outbound.hibernate.mapper.PanacheColaboradorFisicoMapper;
import br.com.muralis.outbound.hibernate.table.PanacheColaboradorFisico;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ColaboradorPanacheFisicoRepository implements ColaboradorFisicoRepository {

	@Inject
	PanacheColaboradorFisicoMapper panacheColaboradorFisicoMapper;

	@Override
	public ColaboradorFisico save(ColaboradorFisico colaborador) {
		PanacheColaboradorFisico panacheColaborador = panacheColaboradorFisicoMapper.from(colaborador);
		panacheColaborador.persist();
		return panacheColaboradorFisicoMapper.from(panacheColaborador);
	}

	@Override
	public Optional<ColaboradorFisico> findById(String id) {
		PanacheColaboradorFisico panacheColaborador = PanacheColaboradorFisico.findById(id);
		if (panacheColaborador == null) {
			return Optional.empty();
		}
		return Optional.of(panacheColaboradorFisicoMapper.from(panacheColaborador));
	}

	@Override
	public boolean existsByEmail(String email) {
		return PanacheColaboradorFisico.count("email", email) > 0;
	}

	@Override
	public boolean existsByCpf(String cpf) {
		return PanacheColaboradorFisico.count("cpf", cpf) > 0;
	}

	@Override
	public boolean deleteById(String id) {
		return PanacheColaboradorFisico.deleteById(id);
	}

	@Override
	public List<ColaboradorFisico> findAll(int page, int size) {
		return panacheColaboradorFisicoMapper.from(PanacheColaboradorFisico.findAll().page(page, size).list());
	}

	@Override
	public long count() {
		return PanacheColaboradorFisico.count();
	}

	@Override
	@Transactional
	public ColaboradorFisico update(ColaboradorFisico colaboradorParaAtualizar) {
		PanacheColaboradorFisico panacheColaborador = PanacheColaboradorFisico
			.findById(colaboradorParaAtualizar.getId());
		panacheColaboradorFisicoMapper.update(panacheColaborador, colaboradorParaAtualizar);
		return panacheColaboradorFisicoMapper.from(panacheColaborador);
	}

}

package br.com.muralis.outbound.hibernate.repository;

import br.com.muralis.core.domain.entity.Colaborador;
import br.com.muralis.core.domain.repository.ColaboradorRepository;
import br.com.muralis.outbound.hibernate.mapper.PanacheColaboradorMapper;
import br.com.muralis.outbound.hibernate.table.PanacheColaborador;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ColaboradorPanacheRepository implements ColaboradorRepository {

    @Inject
    PanacheColaboradorMapper panacheColaboradorMapper;

    @Override
    public Colaborador save(Colaborador colaborador) {
        PanacheColaborador panacheColaborador = panacheColaboradorMapper.from(colaborador);
        panacheColaborador.persist();
        return panacheColaboradorMapper.from(panacheColaborador);
    }

    @Override
    public Optional<Colaborador> findById(String id) {
        PanacheColaborador panacheColaborador = PanacheColaborador.findById(id);
        if (panacheColaborador == null) {
            return Optional.empty();
        }
        return Optional.of(panacheColaboradorMapper.from(panacheColaborador));
    }

    @Override
    public boolean existsByEmail(String email) {
        return PanacheColaborador.count("email", email) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return PanacheColaborador.deleteById(id);
    }

    @Override
    public List<Colaborador> findAll(int page, int size) {
        return panacheColaboradorMapper.from(PanacheColaborador.findAll().page(page, size).list());
    }

    @Override
    public long count() {
        return PanacheColaborador.count();
    }

    @Override
    @Transactional
    public Colaborador update(Colaborador colaboradorParaAtualizar) {
        PanacheColaborador panacheColaborador = PanacheColaborador.findById(colaboradorParaAtualizar.getId());
        panacheColaboradorMapper.update(panacheColaborador, colaboradorParaAtualizar);
        return panacheColaboradorMapper.from(panacheColaborador);
    }

}

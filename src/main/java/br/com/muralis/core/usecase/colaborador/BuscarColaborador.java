package br.com.muralis.core.usecase.colaborador;

import br.com.muralis.core.domain.entity.Colaborador;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorNaoEncontradoException;
import br.com.muralis.core.domain.repository.ColaboradorRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BuscarColaborador {

    @Inject
    ColaboradorRepository colaboradorRepository;

    public Colaborador execute(String id) {
        Log.info("Buscando colaborador com id " + id);
        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new ColaboradorNaoEncontradoException(id));
        Log.info("Colaborador encontrado: " + colaborador.getEmail());
        return colaborador;
    }

}

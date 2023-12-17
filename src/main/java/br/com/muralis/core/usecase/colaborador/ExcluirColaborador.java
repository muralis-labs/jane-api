package br.com.muralis.core.usecase.colaborador;

import br.com.muralis.core.domain.exception.colaborador.ColaboradorNaoEncontradoException;
import br.com.muralis.core.domain.repository.ColaboradorRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ExcluirColaborador {

    @Inject
    ColaboradorRepository colaboradorRepository;

    @Transactional
    public void execute(String id) {
        Log.info("Preparando para excluir colaborador: " + id);
        if (!colaboradorRepository.deleteById(id))
            throw new ColaboradorNaoEncontradoException(id);
        Log.info("Colaborador excluído: " + id);
    }

}

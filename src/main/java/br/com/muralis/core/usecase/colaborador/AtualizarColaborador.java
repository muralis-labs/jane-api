package br.com.muralis.core.usecase.colaborador;

import br.com.muralis.core.domain.entity.Colaborador;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorCadastradoComEmail;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorNaoEncontradoException;
import br.com.muralis.core.domain.repository.ColaboradorRepository;
import br.com.muralis.core.dto.colaborador.AtualizarColaboradorCommand;
import br.com.muralis.core.mapper.ColaboradorMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Objects;

@ApplicationScoped
public class AtualizarColaborador {

    @Inject
    ColaboradorRepository colaboradorRepository;

    @Inject
    ColaboradorMapper colaboradorMapper;

    @Transactional
    public Colaborador execute(String id, AtualizarColaboradorCommand command) {
        Log.info("Preparando para atualizar colaborador: " + id);
        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new ColaboradorNaoEncontradoException(id));
        if (isNotEmailEquals(command, colaborador) && colaboradorRepository.existsByEmail(command.getEmail()))
            throw new ColaboradorCadastradoComEmail(command.getEmail());
        Colaborador colaboradorParaAtualizar = colaboradorMapper.update(colaborador, command);
        Log.info("Colaborador preparado para salvar: " + id);
        Colaborador colaboradorSalvo = colaboradorRepository.update(colaboradorParaAtualizar);
        Log.info("Colaborador salvo: " + id);
        return colaboradorSalvo;
    }

    private boolean isNotEmailEquals(AtualizarColaboradorCommand command, Colaborador colaborador) {
        return !Objects.equals(colaborador.getEmail(), command.getEmail());
    }

}

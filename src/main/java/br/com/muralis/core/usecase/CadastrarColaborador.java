package br.com.muralis.core.usecase;

import br.com.muralis.core.domain.entity.Colaborador;
import br.com.muralis.core.domain.exception.ColaboradorCadastradoComEmail;
import br.com.muralis.core.domain.repository.ColaboradorRepository;
import br.com.muralis.core.dto.CadastrarColaboradorCommand;
import br.com.muralis.core.mapper.ColaboradorMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CadastrarColaborador {

    @Inject
    ColaboradorRepository colaboradorRepository;

    @Inject
    ColaboradorMapper colaboradorMapper;

    @Transactional
    public Colaborador execute(CadastrarColaboradorCommand command) {
        Log.info("Preparando para cadastrar colaborador: " + command.getEmail());
        if (colaboradorRepository.existsByEmail(command.getEmail()))
            throw new ColaboradorCadastradoComEmail(command.getEmail());
        Colaborador colaborador = colaboradorMapper.from(command);
        colaborador.prepararParaSalvar();
        Log.info("Colaborador preparado para salvar: " + colaborador.getEmail());
        Colaborador colaboradorSalvo = colaboradorRepository.save(colaborador);
        Log.info("Colaborador salvo: " + colaboradorSalvo.getEmail());
        return colaboradorSalvo;
    }

}

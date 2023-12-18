package br.com.muralis.core.usecase.empresa;

import br.com.muralis.core.domain.exception.empresa.EmpresaNaoEncontrada;
import br.com.muralis.core.domain.repository.EmpresaRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ExcluirEmpresa {
    @Inject
    EmpresaRepository empresaRepository;

    @Transactional
    public void execute(String id) {
        Log.info("Preparando para excluir empresa: " + id);
        if (!empresaRepository.deleteById(id))
            throw new EmpresaNaoEncontrada(id);
        Log.info("Empresa excluída: " + id);
    }
}

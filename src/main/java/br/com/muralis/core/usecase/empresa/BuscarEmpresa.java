package br.com.muralis.core.usecase.empresa;

import br.com.muralis.core.domain.entity.Empresa;
import br.com.muralis.core.domain.exception.empresa.EmpresaNaoEncontrada;
import br.com.muralis.core.domain.repository.EmpresaRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BuscarEmpresa {
    @Inject
    EmpresaRepository empresaRepository;

    public Empresa execute(String id) {
        Log.info("Buscando empresa com id " + id);
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EmpresaNaoEncontrada(id));
        Log.info("Empresa encontrada: " + empresa.getEmail());
        return empresa;
    }
}

package br.com.muralis.core.usecase.empresa;

import br.com.muralis.core.domain.entity.Empresa;
import br.com.muralis.core.domain.repository.EmpresaRepository;
import br.com.muralis.core.dto.CustomPage;
import br.com.muralis.outbound.hibernate.table.PanacheEmpresa;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ListarEmpresas {
    @Inject
    EmpresaRepository empresaRepository;

    public CustomPage<Empresa> execute(int page, int size) {
        Log.info("Listando empresas da página " + page + " com tamanho " + size);
        var empresas = empresaRepository.findAll(page, size);
        var total = empresaRepository.count();
        Log.info("Empresas encontradas: " + empresas.size());
        return new CustomPage<>(empresas, page, size, total);
    }
}

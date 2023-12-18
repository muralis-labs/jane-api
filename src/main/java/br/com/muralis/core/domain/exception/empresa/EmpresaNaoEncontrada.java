package br.com.muralis.core.domain.exception.empresa;

import br.com.muralis.core.domain.exception.DomainException;
import io.quarkus.logging.Log;

public class EmpresaNaoEncontrada extends DomainException {

    public EmpresaNaoEncontrada(String id) {
        super(String.format("Empresa com id %s não encontrada", id), 404);
        Log.error("Empresa com id " + id + " não encontrada");
    }

}

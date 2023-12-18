package br.com.muralis.core.usecase.colaborador;

import br.com.muralis.core.domain.exception.colaborador.ColaboradorNaoEncontradoException;
import br.com.muralis.core.domain.repository.ColaboradorRepository;
import br.com.muralis.core.dto.colaborador.CadastrarColaboradorCommand;
import br.com.muralis.core.usecase.IntegrationProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(IntegrationProfile.class)
class ExcluirColaboradorTest {

    @Inject
    ExcluirColaborador excluirColaborador;

    @Inject
    CadastrarColaborador cadastrarColaborador;

    @Inject
    ColaboradorRepository colaboradorRepository;

    @Test
    @DisplayName("Deve excluir um colaborador pelo id")
    void deveExcluirUmColaboradorPeloId() {
        var colaborador = cadastrarColaborador.execute(CadastrarColaboradorCommand.builder()
                .nome("Fulano")
                .email(UUID.randomUUID() + "@gmail.com")
                .build());
        assertNotNull(colaborador.getId());
        excluirColaborador.execute(colaborador.getId());
        var colaboradorDoBanco = colaboradorRepository.findById(colaborador.getId()).orElse(null);
        assertNull(colaboradorDoBanco);
    }

    @Test
    @DisplayName("Deve lançar exceção quando não encontrar um colaborador pelo id")
    void deveLancarExcecaoQuandoNaoEncontrarUmColaboradorPeloId() {
        var id = UUID.randomUUID().toString();
        assertThrows(ColaboradorNaoEncontradoException.class, () -> excluirColaborador.execute(id));
    }

}
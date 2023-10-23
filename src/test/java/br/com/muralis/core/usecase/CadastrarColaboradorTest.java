package br.com.muralis.core.usecase;

import br.com.muralis.core.domain.exception.ColaboradorCadastradoComEmail;
import br.com.muralis.core.domain.repository.ColaboradorRepository;
import br.com.muralis.core.dto.CadastrarColaboradorCommand;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(IntegrationProfile.class)
class CadastrarColaboradorTest {

    @Inject
    CadastrarColaborador cadastrarColaborador;

    @Inject
    ColaboradorRepository colaboradorRepository;

    @Test
    @DisplayName("Deve cadastrar um colaborador")
    public void deveCadastrarUmColaborador() {
        var command = CadastrarColaboradorCommand.builder()
                .nome("João")
                .email("joao@email.com")
                .build();
        var colaborador = cadastrarColaborador.execute(command);
        assertNotNull(colaborador.getId());
        var colaboradorDoBanco = colaboradorRepository.findById(colaborador.getId());
        assertTrue(colaboradorDoBanco.isPresent());
        assertEquals(colaborador.getId(), colaboradorDoBanco.get().getId());
        assertEquals(colaborador.getNome(), colaboradorDoBanco.get().getNome());
        assertEquals(colaborador.getEmail(), colaboradorDoBanco.get().getEmail());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar um colaborador com e-mail já cadastrado")
    public void deveLancarExcecaoAoCadastrarUmColaboradorComEmailJaCadastrado() {
        var command = CadastrarColaboradorCommand.builder()
                .nome("João")
                .email("joaooct231142@email.com")
                .build();
        cadastrarColaborador.execute(command);
        assertThrows(ColaboradorCadastradoComEmail.class, () -> cadastrarColaborador.execute(command));
    }

}
package br.com.muralis.core.usecase;

import br.com.muralis.core.domain.entity.Colaborador;
import br.com.muralis.core.domain.exception.DomainException;
import br.com.muralis.core.domain.repository.ColaboradorRepository;
import br.com.muralis.core.dto.AtualizarColaboradorCommand;
import br.com.muralis.core.dto.CadastrarColaboradorCommand;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(IntegrationProfile.class)
class AtualizarColaboradorTest {

    @Inject
    AtualizarColaborador atualizarColaborador;

    @Inject
    CadastrarColaborador cadastrarColaborador;

    @Inject
    ColaboradorRepository colaboradorRepository;

    String email;

    String id;

    @BeforeEach
    void setUp() {
        email = UUID.randomUUID() + "@gmail.com";
        var cadastrarCommand = CadastrarColaboradorCommand.builder()
                .nome("Fulano")
                .email(email)
                .build();
        Colaborador colaborador = cadastrarColaborador.execute(cadastrarCommand);
        id = colaborador.getId();
    }

    @Test
    @DisplayName("Deve atualizar um colaborador")
    void deveAtualizarUmColaborador() {
        var command = AtualizarColaboradorCommand
                .builder()
                .nome("Fulano da Silva")
                .email(email)
                .build();
        atualizarColaborador.execute(id, command);
        var colaborador = colaboradorRepository.findById(id).orElseThrow();
        assertEquals("Fulano da Silva", colaborador.getNome());
        assertEquals(email, colaborador.getEmail());
    }

    @Test
    @DisplayName("Caso o email seja alterado, deve atualizar")
    void casoOEmailSejaAlteradoDeveAtualizar() {
        var command = AtualizarColaboradorCommand
                .builder()
                .nome("Fulano da Silva")
                .email(UUID.randomUUID() + "@gmail.com")
                .build();
        atualizarColaborador.execute(id, command);
        var colaborador = colaboradorRepository.findById(id).orElseThrow();
        assertEquals("Fulano da Silva", colaborador.getNome());
        assertNotEquals(email, colaborador.getEmail());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o colaborador não for encontrado")
    void deveLancarExcecaoQuandoOColaboradorNaoForEncontrado() {
        var command = AtualizarColaboradorCommand
                .builder()
                .nome("Fulano da Silva")
                .email(UUID.randomUUID() + "@gmail.com")
                .build();
        assertThrows(DomainException.class, () -> atualizarColaborador.execute(UUID.randomUUID().toString(), command));
    }

    @Test
    @DisplayName("Deve lançar exceção quando o colaborador já estiver cadastrado com o email informado")
    void deveLancarExcecaoQuandoOColaboradorJaEstiverCadastradoComOEmailInformado() {
        var command = AtualizarColaboradorCommand
                .builder()
                .nome("Fulano da Silva")
                .email(UUID.randomUUID() + "@gmail.com")
                .build();
        var cadastrarCommand = CadastrarColaboradorCommand.builder()
                .nome("Fulano")
                .email(command.getEmail())
                .build();
        cadastrarColaborador.execute(cadastrarCommand);
        assertThrows(DomainException.class, () -> atualizarColaborador.execute(id, command));
    }

}
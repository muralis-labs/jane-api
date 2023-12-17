package br.com.muralis.core.usecase.colaborador;

import br.com.muralis.core.domain.exception.colaborador.ColaboradorCadastradoComEmail;
import br.com.muralis.core.domain.repository.ColaboradorRepository;
import br.com.muralis.core.dto.colaborador.CadastrarColaboradorCommand;
import br.com.muralis.core.usecase.IntegrationProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
                .nome("João Junior")
                .email("joao@email.com")
                .RG("380.064.778-1")
                .CPF("286.738.300-52")
                .nomePai("João")
                .cidadNascimento("Mogi das Cruzes")
                .dataExpedicao(LocalDate.now())
                .estadoCivil("Solteiro")
                .estadoNascimento("São Paulo")
                .grauInstrucao("Ensino médio completo")
                .sexo("Masculino")
                .nascimento(LocalDate.now())
                .nomeMae("Maria")
                .orgaoExpedidor("SSP/SP")
                .paisNascimento("Brasil")
                .telefoneCelular("11971723993")
                .telefoneResidencial("")
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
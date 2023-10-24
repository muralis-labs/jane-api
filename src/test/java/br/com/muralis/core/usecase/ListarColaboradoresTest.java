package br.com.muralis.core.usecase;

import br.com.muralis.core.dto.CadastrarColaboradorCommand;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestProfile(IntegrationProfile.class)
class ListarColaboradoresTest {

    @Inject
    ListarColaboradores listarColaboradores;

    @Inject
    CadastrarColaborador cadastrarColaborador;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 10; i++) {
            var command = CadastrarColaboradorCommand
                    .builder()
                    .nome("Colaborador " + i)
                    .email("colaborador" + i + UUID.randomUUID() + "@muralis.com.br")
                    .build();
            cadastrarColaborador.execute(command);
        }
    }

    @Test
    @DisplayName("Deve listar somente 5 colaboradores")
    void deveListarSomente5Colaboradores() {
        var colaboradores = listarColaboradores.execute(0, 5).getContent();
        assertEquals(5, colaboradores.size());
    }

    @Test
    @DisplayName("Deve haver mais que uma página de colaboradores")
    void deveHaverMaisQueUmaPaginaDeColaboradores() {
        var colaboradores = listarColaboradores.execute(0, 5);
        assertTrue(colaboradores.getTotalPages() > 1);
    }

    @Test
    @DisplayName("Total de páginas deve ser a divisão do total de colaboradores pelo tamanho da página")
    void totalDePaginasDeveSerADivisaoDoTotalDeColaboradoresPeloTamanhoDaPagina() {
        var colaboradores = listarColaboradores.execute(0, 5);
        var total = colaboradores.getTotalElements();
        var size = colaboradores.getSize();
        var totalPages = colaboradores.getTotalPages();
        assertEquals(totalPages, (int) Math.ceil((double) total / size));
    }

}
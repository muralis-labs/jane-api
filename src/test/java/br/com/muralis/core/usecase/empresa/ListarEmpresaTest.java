package br.com.muralis.core.usecase.empresa;

import br.com.muralis.core.dto.empresa.CadastrarEmpresaCommand;
import br.com.muralis.core.mock.GenerateCNPJ;
import br.com.muralis.core.usecase.IntegrationProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestProfile(IntegrationProfile.class)
public class ListarEmpresaTest {

    @Inject
    CadastrarEmpresa cadastrarEmpresa;

    @Inject
    ListarEmpresas listarEmpresas;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 10; i++) {
            var command = CadastrarEmpresaCommand
                    .builder()
                    .razaoSocial("Muralis " + i)
                    .CEP("1010101")
                    .email("email@email.com")
                    .CNPJ(GenerateCNPJ.generateRandomCnpj())
                    .bairro("Jd. Europa")
                    .cidade("Mogi das Cruzes")
                    .dataContrato(LocalDate.now())
                    .complemento("")
                    .estado("São Paulo")
                    .endereco("Rua Conceição Augusto Penha")
                    .numero(30)
                    .inscricaoEstadual("101010")
                    .inscricaoMunicipal("102030")
                    .objetoContratual("Contrato")
                    .regimeTributario("Simples nacional")
                    .mensalidadeContrato("100")
                    .telefone("1191919102")
                    .email("colaborador" + i + UUID.randomUUID() + "@muralis.com.br")
                    .build();
            cadastrarEmpresa.execute(command);
        }
    }

    @Test
    @DisplayName("Deve listar somente 5 empresas")
    void deveListarSomente5Empresas() {
        var empresas = listarEmpresas.execute(0, 5).getContent();
        assertEquals(5, empresas.size());

    }
}

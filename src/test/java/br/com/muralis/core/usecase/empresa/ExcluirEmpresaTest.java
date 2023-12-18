package br.com.muralis.core.usecase.empresa;

import br.com.muralis.core.domain.repository.EmpresaRepository;
import br.com.muralis.core.dto.empresa.CadastrarEmpresaCommand;
import br.com.muralis.core.usecase.IntegrationProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@TestProfile(IntegrationProfile.class)
public class ExcluirEmpresaTest {

    @Inject
    ExcluirEmpresa excluirEmpresa;

    @Inject
    EmpresaRepository empresaRepository;

    @Inject
    CadastrarEmpresa cadastrarEmpresa;
    @Test
    @DisplayName("Deve excluir uma empresa pelo id")
    public void deveExcluirUmaEmpresaPorId() {
        var empresa = cadastrarEmpresa.execute(CadastrarEmpresaCommand.builder()
                .razaoSocial("Muralis")
                .CEP("1010101")
                .email("email@email.com")
                .CNPJ("38.278.587/0001-78")
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
                .build());
        assertNotNull(empresa.getId());
        excluirEmpresa.execute(empresa.getId());
        var empresaDoBanco = empresaRepository.findById(empresa.getId()).orElse(null);
        assertNull(empresaDoBanco);
    }
}

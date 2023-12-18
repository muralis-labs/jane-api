package br.com.muralis.core.usecase.empresa;

import br.com.muralis.core.domain.entity.Empresa;
import br.com.muralis.core.domain.repository.EmpresaRepository;
import br.com.muralis.core.dto.empresa.CadastrarEmpresaCommand;
import br.com.muralis.core.usecase.IntegrationProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@TestProfile(IntegrationProfile.class)
public class BuscarEmpresaTest {
    @Inject
    CadastrarEmpresa cadastrarEmpresa;

    @Inject
    BuscarEmpresa buscarEmpresa;

    @Inject
    EmpresaRepository empresaRepository;

    Empresa empresa;
    @BeforeEach
    void setUp() {
        var command = CadastrarEmpresaCommand.builder()
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
                .build();
        empresa = cadastrarEmpresa.execute(command);
    }

    @Test
    @DisplayName("Deve buscar uma empresa pelo id")
    void deveBuscarUmaEmpresaPeloId() {
        var empresaEncontrado = buscarEmpresa.execute(empresa.getId());
        assertNotNull(empresaEncontrado);
        assertEquals(empresa.getId(), empresaEncontrado.getId());
        assertEquals(empresa.getCNPJ(), empresaEncontrado.getCNPJ());
        assertEquals(empresa.getEmail(), empresaEncontrado.getEmail());
        var empresaDoBanco = empresaRepository.findById(empresa.getId()).orElse(null);
        assertNotNull(empresaDoBanco);
        assertEquals(empresaDoBanco.getId(), empresaEncontrado.getId());
        assertEquals(empresaDoBanco.getCNPJ(), empresaEncontrado.getCNPJ());
        assertEquals(empresaDoBanco.getEmail(), empresaEncontrado.getEmail());
    }
}

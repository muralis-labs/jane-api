package br.com.muralis.core.usecase.empresa;

import br.com.muralis.core.domain.repository.EmpresaRepository;
import br.com.muralis.core.dto.empresa.CadastrarEmpresaCommand;
import br.com.muralis.core.mock.GenerateCNPJ;
import br.com.muralis.core.objectValue.Endereco;
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
public class CadastrarEmpresaTest {

	@Inject
	CadastrarEmpresa cadastrarEmpresa;

	@Inject
	EmpresaRepository empresaRepository;

	@Test
	@DisplayName("Deve cadastrar empresa")
	public void deveCadastrarEmpresa() {
		var command = CadastrarEmpresaCommand.builder()
			.razaoSocial("Muralis")
			.email("email@email.com")
			.cnpj(GenerateCNPJ.generateRandomCnpj())
			.dataContrato(LocalDate.now())
			.inscricaoEstadual("101010")
			.inscricaoMunicipal("102030")
			.objetoContratual("Contrato")
			.regimeTributario("Simples nacional")
			.mensalidadeContrato("100")
			.telefone("1191919102")
			.endereco(Endereco
					.builder()
					.numero(30)
					.endereco("Rua Conceição Augusto Penha")
					.estado("São Paulo")
					.complemento("")
					.cidade("Mogi das Cruzes")
					.bairro("Jd. Europa")
					.cep("1010101")
					.build())
			.build();

		var empresa = cadastrarEmpresa.execute(command);
		assertNotNull(empresa.getId());
		var empresaBanco = empresaRepository.findById(empresa.getId());
		assertTrue(empresaBanco.isPresent());
		assertEquals(empresa.getCnpj(), empresaBanco.get().getCnpj());
		assertEquals(empresa.getEndereco().getCep(), empresaBanco.get().getEndereco().getCep());
	}

}

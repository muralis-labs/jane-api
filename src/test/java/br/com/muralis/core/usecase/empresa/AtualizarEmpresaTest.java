package br.com.muralis.core.usecase.empresa;

import br.com.muralis.core.domain.entity.Empresa;
import br.com.muralis.core.domain.repository.EmpresaRepository;
import br.com.muralis.core.dto.empresa.AtualizarEmpresaCommand;
import br.com.muralis.core.dto.empresa.CadastrarEmpresaCommand;
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
public class AtualizarEmpresaTest {

	@Inject
	CadastrarEmpresa cadastrarEmpresa;

	@Inject
	AtualizarEmpresa atualizarEmpresa;

	@Inject
	EmpresaRepository empresaRepository;

	String email;

	String id;

	@BeforeEach
	void setUp() {
		email = UUID.randomUUID() + "@gmail.com";
		var cadastrarCommand = CadastrarEmpresaCommand.builder()
			.email(email)
			.razaoSocial("Muralis")
			.cep("1010101")
			.email("email@email.com")
			.cnpj("38.278.587/0001-78")
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
		Empresa empresa = cadastrarEmpresa.execute(cadastrarCommand);
		id = empresa.getId();
	}

	@Test
	@DisplayName("Deve atualizar uma empresa")
	void deveAtualizarUmEmpresa() {
		var command = AtualizarEmpresaCommand.builder()
			.razaoSocial("Muralis123")
			.cep("1010101")
			.email("email@email.com")
			.cnpj("38.278.587/0001-78")
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
			.email(email)
			.build();
		atualizarEmpresa.execute(id, command);
		var empresa = empresaRepository.findById(id).orElseThrow();
		assertEquals("Muralis123", empresa.getRazaoSocial());
		assertEquals(email, empresa.getEmail());
		assertEquals("38.278.587/0001-78", empresa.getCnpj());
	}

}

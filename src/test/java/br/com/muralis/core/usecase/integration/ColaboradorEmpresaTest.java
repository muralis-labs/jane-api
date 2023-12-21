package br.com.muralis.core.usecase.integration;

import br.com.muralis.core.domain.repository.ColaboradorJuridicoRepository;
import br.com.muralis.core.domain.repository.EmpresaRepository;
import br.com.muralis.core.dto.colaborador.juridico.CadastrarColaboradorJuridicoCommand;
import br.com.muralis.core.dto.empresa.CadastrarEmpresaCommand;
import br.com.muralis.core.mock.CPFGenerator;
import br.com.muralis.core.mock.GenerateCNPJ;
import br.com.muralis.core.usecase.IntegrationProfile;
import br.com.muralis.core.usecase.colaborador.juridico.CadastrarColaboradorJuridico;
import br.com.muralis.core.usecase.empresa.CadastrarEmpresa;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(IntegrationProfile.class)
public class ColaboradorEmpresaTest {

	@Inject
	CadastrarColaboradorJuridico cadastrarColaboradorJuridico;

	@Inject
	ColaboradorJuridicoRepository colaboradorJuridicoRepository;

	@Inject
	CadastrarEmpresa cadastrarEmpresa;

	@Inject
	EmpresaRepository empresaRepository;

	@Test
	@DisplayName("Deve cadastrar um colaborador e correlacionar a uma empresa")
	public void deveCadastrarUmColaborador() {
		var empresaCommand = createEmpresaCommand();
		var empresa = cadastrarEmpresa.execute(empresaCommand);
		assertNotNull(empresa.getId());
		var empresaBanco = empresaRepository.findById(empresa.getId());
		assertTrue(empresaBanco.isPresent());
		assertEquals(empresa.getCnpj(), empresaBanco.get().getCnpj());

		var command = createColaboradorCommand();
		command.setEmpresaId(empresa.getId());
		var colaborador = cadastrarColaboradorJuridico.execute(command);
		assertNotNull(colaborador.getId());
		var colaboradorDoBanco = colaboradorJuridicoRepository.findById(colaborador.getId());
		assertTrue(colaboradorDoBanco.isPresent());
		assertEquals(colaborador.getId(), colaboradorDoBanco.get().getId());
		assertEquals(colaborador.getNome(), colaboradorDoBanco.get().getNome());
		assertEquals(colaborador.getEmail(), colaboradorDoBanco.get().getEmail());
		assertEquals(colaborador.getEmpresa().getId(), colaboradorDoBanco.get().getEmpresa().getId());
	}

	private CadastrarEmpresaCommand createEmpresaCommand() {
		return CadastrarEmpresaCommand.builder()
			.razaoSocial("Muralis")
			.cep("1010101")
			.email("email@email.com")
			.cnpj(GenerateCNPJ.generateRandomCnpj())
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
	}

	private CadastrarColaboradorJuridicoCommand createColaboradorCommand() {
		return CadastrarColaboradorJuridicoCommand.builder()
			.nome("João Junior")
			.email("pabloteste" + UUID.randomUUID() + "@email.com")
			.rg("380.064.778-1")
			.cpf(CPFGenerator.generate())
			.nomePai("João")
			.cidadeNascimento("Mogi das Cruzes")
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
	}

}

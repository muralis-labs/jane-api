package br.com.muralis.core.usecase.colaborador.fisico;

import br.com.muralis.core.domain.entity.Colaborador;
import br.com.muralis.core.domain.exception.DomainException;
import br.com.muralis.core.domain.repository.ColaboradorFisicoRepository;
import br.com.muralis.core.dto.colaborador.fisico.AtualizarColaboradorFisicoCommand;
import br.com.muralis.core.dto.colaborador.fisico.CadastrarColaboradorFisicoCommand;
import br.com.muralis.core.mock.CPFGenerator;
import br.com.muralis.core.usecase.IntegrationProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(IntegrationProfile.class)
class AtualizarColaboradorFisicoTest {

	@Inject
	AtualizarColaboradorFisico atualizarColaboradorFisico;

	@Inject
	CadastrarColaboradorFisico cadastrarColaboradorFisico;

	@Inject
	ColaboradorFisicoRepository colaboradorFisicoRepository;

	String email;

	String id;

	@BeforeEach
	void setUp() {
		email = UUID.randomUUID() + "@gmail.com";
		var cadastrarCommand = CadastrarColaboradorFisicoCommand.builder()
			.nome("Fulano")
			.email(email)
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
		Colaborador colaborador = cadastrarColaboradorFisico.execute(cadastrarCommand);
		id = colaborador.getId();
	}

	@Test
	@DisplayName("Deve atualizar um colaborador")
	void deveAtualizarUmColaborador() {
		var command = AtualizarColaboradorFisicoCommand.builder()
			.nome("Fulano da Silva")
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
			.email(email)
			.cep("111111111")
			.estado("São Paulo")
			.cidade("Mogi das Cruzes")
			.bairro("Parque Monte Líbano")
			.complemento("Casa 1")
			.numeroResidencial(123)
			.endereco("Rua dos coelhos")
			.build();
		atualizarColaboradorFisico.execute(id, command);
		var colaborador = colaboradorFisicoRepository.findById(id).orElseThrow();
		assertEquals("Fulano da Silva", colaborador.getNome());
		assertEquals(email, colaborador.getEmail());
		assertEquals(colaborador.getNumeroResidencial(), 123);
		assertEquals(colaborador.getBairro(), "Parque Monte Líbano");
		assertEquals(colaborador.getCep(), "111111111");
		assertEquals(colaborador.getComplemento(), "Casa 1");
		assertEquals(colaborador.getCidade(), "Mogi das Cruzes");
		assertEquals(colaborador.getEstado(), "São Paulo");
	}

	@Test
	@DisplayName("Caso o email seja alterado, deve atualizar")
	void casoOEmailSejaAlteradoDeveAtualizar() {
		var command = AtualizarColaboradorFisicoCommand.builder()
			.nome("Fulano da Silva")
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
			.email(UUID.randomUUID() + "@gmail.com")
			.build();
		atualizarColaboradorFisico.execute(id, command);
		var colaborador = colaboradorFisicoRepository.findById(id).orElseThrow();
		assertEquals("Fulano da Silva", colaborador.getNome());
		assertNotEquals(email, colaborador.getEmail());
	}

	@Test
	@DisplayName("Deve lançar exceção quando o colaborador não for encontrado")
	void deveLancarExcecaoQuandoOColaboradorNaoForEncontrado() {
		var command = AtualizarColaboradorFisicoCommand.builder()
			.nome("Fulano da Silva")
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
			.email(UUID.randomUUID() + "@gmail.com")
			.build();
		assertThrows(DomainException.class,
				() -> atualizarColaboradorFisico.execute(UUID.randomUUID().toString(), command));
	}

	@Test
	@DisplayName("Deve lançar exceção quando o colaborador já estiver cadastrado com o email informado")
	void deveLancarExcecaoQuandoOColaboradorJaEstiverCadastradoComOEmailInformado() {
		var command = AtualizarColaboradorFisicoCommand.builder()
			.nome("Fulano da Silva")
			.cpf(CPFGenerator.generate())
			.email(UUID.randomUUID() + "@gmail.com")
			.build();
		var cadastrarCommand = CadastrarColaboradorFisicoCommand.builder()
			.nome("Fulano")
			.cpf(CPFGenerator.generate())
			.email(command.getEmail())
			.build();
		cadastrarColaboradorFisico.execute(cadastrarCommand);
		assertThrows(DomainException.class, () -> atualizarColaboradorFisico.execute(id, command));
	}

}
package br.com.muralis.core.usecase.colaborador.juridico;

import br.com.muralis.core.domain.entity.Colaborador;
import br.com.muralis.core.domain.exception.DomainException;
import br.com.muralis.core.domain.repository.ColaboradorJuridicoRepository;
import br.com.muralis.core.dto.colaborador.juridico.AtualizarColaboradorJuridicoCommand;
import br.com.muralis.core.dto.colaborador.juridico.CadastrarColaboradorJuridicoCommand;
import br.com.muralis.core.mock.CPFGenerator;
import br.com.muralis.core.objectValue.DadosContratuaisJuridico;
import br.com.muralis.core.objectValue.Endereco;
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
class AtualizarColaboradorJuridicoTest {

	@Inject
	AtualizarColaboradorJuridico atualizarColaboradorJuridico;

	@Inject
	CadastrarColaboradorJuridico cadastrarColaboradorJuridico;

	@Inject
	ColaboradorJuridicoRepository colaboradorJuridicoRepository;

	String email;

	String id;

	@BeforeEach
	void setUp() {
		email = UUID.randomUUID() + "@gmail.com";
		var cadastrarCommand = CadastrarColaboradorJuridicoCommand.builder()
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
			.endereco(Endereco.builder()
					.cep("111111111")
					.estado("São Paulo")
					.cidade("Mogi das Cruzes")
					.complemento("Casa 1")
					.numero(123)
					.endereco("Rua dos coelhos")
					.build())
			.dadosContratuaisJuridico(DadosContratuaisJuridico.builder()
					.regimeSocial("Regime")
					.CNPJ("27.535.284/0001-77")
					.dataContrato(LocalDate.now())
					.inscricaoEstadual("123")
					.mensalidadeContrato("1200")
					.objetoContratual("Objeto")
					.razaoSocial("Empresa")
					.build())
			.build();
		Colaborador colaborador = cadastrarColaboradorJuridico.execute(cadastrarCommand);
		id = colaborador.getId();
	}

	@Test
	@DisplayName("Deve atualizar um colaborador")
	void deveAtualizarUmColaborador() {
		var command = AtualizarColaboradorJuridicoCommand.builder()
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
			.endereco(Endereco.builder()
					.cep("111111112")
					.estado("São Paulo")
					.cidade("Mogi das Cruzes")
					.complemento("Casa 1")
					.numero(123)
					.endereco("Rua dos coelhos")
					.build())
			.dadosContratuaisJuridico(DadosContratuaisJuridico.builder()
					.regimeSocial("Regime")
					.CNPJ("27.535.284/0001-77")
					.dataContrato(LocalDate.now())
					.inscricaoEstadual("123")
					.mensalidadeContrato("1202")
					.objetoContratual("Objeto")
					.razaoSocial("Empresa")
					.build())
			.build();
		atualizarColaboradorJuridico.execute(id, command);
		var colaborador = colaboradorJuridicoRepository.findById(id).orElseThrow();
		assertEquals("Fulano da Silva", colaborador.getNome());
		assertEquals(email, colaborador.getEmail());
		assertEquals("111111112", colaborador.getEndereco().getCep());
		assertEquals("1202", colaborador.getDadosContratuaisJuridico().mensalidadeContrato);
	}

	@Test
	@DisplayName("Caso o email seja alterado, deve atualizar")
	void casoOEmailSejaAlteradoDeveAtualizar() {
		var command = AtualizarColaboradorJuridicoCommand.builder()
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
		atualizarColaboradorJuridico.execute(id, command);
		var colaborador = colaboradorJuridicoRepository.findById(id).orElseThrow();
		assertEquals("Fulano da Silva", colaborador.getNome());
		assertNotEquals(email, colaborador.getEmail());
	}

	@Test
	@DisplayName("Deve lançar exceção quando o colaborador não for encontrado")
	void deveLancarExcecaoQuandoOColaboradorNaoForEncontrado() {
		var command = AtualizarColaboradorJuridicoCommand.builder()
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
				() -> atualizarColaboradorJuridico.execute(UUID.randomUUID().toString(), command));
	}

	@Test
	@DisplayName("Deve lançar exceção quando o colaborador já estiver cadastrado com o email informado")
	void deveLancarExcecaoQuandoOColaboradorJaEstiverCadastradoComOEmailInformado() {
		var command = AtualizarColaboradorJuridicoCommand.builder()
			.nome("Fulano da Silva")
			.email(UUID.randomUUID() + "@gmail.com")
			.build();
		var cadastrarCommand = CadastrarColaboradorJuridicoCommand.builder()
			.nome("Fulano")
			.email(command.getEmail())
			.build();
		cadastrarColaboradorJuridico.execute(cadastrarCommand);
		assertThrows(DomainException.class, () -> atualizarColaboradorJuridico.execute(id, command));
	}

}
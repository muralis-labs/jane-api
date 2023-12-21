package br.com.muralis.core.usecase.colaborador.fisico;

import br.com.muralis.core.domain.exception.colaborador.ColaboradorCadastradoComCpf;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorCadastradoComEmail;
import br.com.muralis.core.domain.repository.ColaboradorFisicoRepository;
import br.com.muralis.core.dto.colaborador.fisico.CadastrarColaboradorFisicoCommand;
import br.com.muralis.core.mock.CPFGenerator;
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
class CadastrarColaboradorFisicoTest {

	@Inject
	CadastrarColaboradorFisico cadastrarColaboradorFisico;

	@Inject
	ColaboradorFisicoRepository colaboradorFisicoRepository;

	@Test
	@DisplayName("Deve cadastrar um colaborador")
	public void deveCadastrarUmColaborador() {
		var command = CadastrarColaboradorFisicoCommand.builder()
			.nome("João Junior")
			.email("joao@email.com")
			.rg("380.064.778-1")
			.cpf("286.738.300-52")
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
		var colaborador = cadastrarColaboradorFisico.execute(command);
		assertNotNull(colaborador.getId());
		var colaboradorDoBanco = colaboradorFisicoRepository.findById(colaborador.getId());
		assertTrue(colaboradorDoBanco.isPresent());
		assertEquals(colaborador.getId(), colaboradorDoBanco.get().getId());
		assertEquals(colaborador.getNome(), colaboradorDoBanco.get().getNome());
		assertEquals(colaborador.getEmail(), colaboradorDoBanco.get().getEmail());
	}

	@Test
	@DisplayName("Deve lançar exceção ao cadastrar um colaborador com e-mail já cadastrado")
	public void deveLancarExcecaoAoCadastrarUmColaboradorComEmailJaCadastrado() {
		var command = CadastrarColaboradorFisicoCommand.builder().nome("João").email("joaooct231142@email.com").build();
		cadastrarColaboradorFisico.execute(command);
		assertThrows(ColaboradorCadastradoComEmail.class, () -> cadastrarColaboradorFisico.execute(command));
	}

	@Test
	@DisplayName("Deve lançar exceção ao cadastrar um colaborador com cpf já cadastrado")
	public void deveLancarExcecaoAoCadastrarUmColaboradorComCpfJaCadastrado() {
		var command = CadastrarColaboradorFisicoCommand.builder().nome("João").cpf(CPFGenerator.generate()).build();
		cadastrarColaboradorFisico.execute(command);
		assertThrows(ColaboradorCadastradoComCpf.class, () -> cadastrarColaboradorFisico.execute(command));
	}

}
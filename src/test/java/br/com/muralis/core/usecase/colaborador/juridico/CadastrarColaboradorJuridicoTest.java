package br.com.muralis.core.usecase.colaborador.juridico;

import br.com.muralis.core.domain.exception.colaborador.ColaboradorCadastradoComEmail;
import br.com.muralis.core.domain.repository.ColaboradorJuridicoRepository;
import br.com.muralis.core.dto.colaborador.juridico.CadastrarColaboradorJuridicoCommand;
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
class CadastrarColaboradorJuridicoTest {

	@Inject
	CadastrarColaboradorJuridico cadastrarColaboradorJuridico;

	@Inject
	ColaboradorJuridicoRepository colaboradorJuridicoRepository;

	@Test
	@DisplayName("Deve cadastrar um colaborador")
	public void deveCadastrarUmColaborador() {
		var command = CadastrarColaboradorJuridicoCommand.builder()
			.nome("João Junior")
			.email("pabloteste180850@email.com")
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
		var colaborador = cadastrarColaboradorJuridico.execute(command);
		assertNotNull(colaborador.getId());
		var colaboradorDoBanco = colaboradorJuridicoRepository.findById(colaborador.getId());
		assertTrue(colaboradorDoBanco.isPresent());
		assertEquals(colaborador.getId(), colaboradorDoBanco.get().getId());
		assertEquals(colaborador.getNome(), colaboradorDoBanco.get().getNome());
		assertEquals(colaborador.getEmail(), colaboradorDoBanco.get().getEmail());
	}

	@Test
	@DisplayName("Deve lançar exceção ao cadastrar um colaborador com e-mail já cadastrado")
	public void deveLancarExcecaoAoCadastrarUmColaboradorComEmailJaCadastrado() {
		var command = CadastrarColaboradorJuridicoCommand.builder()
			.nome("João")
			.email("pabloteste18120949@email.com")
			.build();
		cadastrarColaboradorJuridico.execute(command);
		assertThrows(ColaboradorCadastradoComEmail.class, () -> cadastrarColaboradorJuridico.execute(command));
	}

}
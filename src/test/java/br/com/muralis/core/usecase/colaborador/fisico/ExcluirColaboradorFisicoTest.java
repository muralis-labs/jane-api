package br.com.muralis.core.usecase.colaborador.fisico;

import br.com.muralis.core.domain.exception.colaborador.ColaboradorNaoEncontradoException;
import br.com.muralis.core.domain.repository.ColaboradorFisicoRepository;
import br.com.muralis.core.dto.colaborador.fisico.CadastrarColaboradorFisicoCommand;
import br.com.muralis.core.usecase.IntegrationProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(IntegrationProfile.class)
class ExcluirColaboradorFisicoTest {

	@Inject
	ExcluirColaboradorFisico excluirColaboradorFisico;

	@Inject
	CadastrarColaboradorFisico cadastrarColaboradorFisico;

	@Inject
	ColaboradorFisicoRepository colaboradorFisicoRepository;

	@Test
	@DisplayName("Deve excluir um colaborador pelo id")
	void deveExcluirUmColaboradorPeloId() {
		var colaborador = cadastrarColaboradorFisico.execute(CadastrarColaboradorFisicoCommand.builder()
			.nome("Fulano")
			.email(UUID.randomUUID() + "@gmail.com")
			.build());
		assertNotNull(colaborador.getId());
		excluirColaboradorFisico.execute(colaborador.getId());
		var colaboradorDoBanco = colaboradorFisicoRepository.findById(colaborador.getId()).orElse(null);
		assertNull(colaboradorDoBanco);
	}

	@Test
	@DisplayName("Deve lançar exceção quando não encontrar um colaborador pelo id")
	void deveLancarExcecaoQuandoNaoEncontrarUmColaboradorPeloId() {
		var id = UUID.randomUUID().toString();
		assertThrows(ColaboradorNaoEncontradoException.class, () -> excluirColaboradorFisico.execute(id));
	}

}
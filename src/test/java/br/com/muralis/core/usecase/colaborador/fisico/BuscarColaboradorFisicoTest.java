package br.com.muralis.core.usecase.colaborador.fisico;

import br.com.muralis.core.domain.entity.Colaborador;
import br.com.muralis.core.domain.exception.colaborador.ColaboradorNaoEncontradoException;
import br.com.muralis.core.domain.repository.ColaboradorFisicoRepository;
import br.com.muralis.core.dto.colaborador.fisico.CadastrarColaboradorFisicoCommand;
import br.com.muralis.core.usecase.IntegrationProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(IntegrationProfile.class)
class BuscarColaboradorFisicoTest {

	@Inject
	BuscarColaboradorFisico buscarColaboradorFisico;

	@Inject
	CadastrarColaboradorFisico cadastrarColaboradorFisico;

	@Inject
	ColaboradorFisicoRepository colaboradorFisicoRepository;

	Colaborador colaborador;

	@BeforeEach
	void setUp() {
		var command = CadastrarColaboradorFisicoCommand.builder()
			.nome("Fulano")
			.email("fulano" + UUID.randomUUID() + "@gmail.com")
			.build();
		colaborador = cadastrarColaboradorFisico.execute(command);
	}

	@Test
	@DisplayName("Deve buscar um colaborador pelo id")
	void deveBuscarUmColaboradorPeloId() {
		var colaboradorEncontrado = buscarColaboradorFisico.execute(colaborador.getId());
		assertNotNull(colaboradorEncontrado);
		assertEquals(colaborador.getId(), colaboradorEncontrado.getId());
		assertEquals(colaborador.getNome(), colaboradorEncontrado.getNome());
		assertEquals(colaborador.getEmail(), colaboradorEncontrado.getEmail());
		var colaboradorDoBanco = colaboradorFisicoRepository.findById(colaborador.getId()).orElse(null);
		assertNotNull(colaboradorDoBanco);
		assertEquals(colaboradorDoBanco.getId(), colaboradorEncontrado.getId());
		assertEquals(colaboradorDoBanco.getNome(), colaboradorEncontrado.getNome());
		assertEquals(colaboradorDoBanco.getEmail(), colaboradorEncontrado.getEmail());
	}

	@Test
	@DisplayName("Deve lançar exceção quando não encontrar um colaborador pelo id")
	void deveLancarExcecaoQuandoNaoEncontrarUmColaboradorPeloId() {
		var id = UUID.randomUUID().toString();
		var exception = assertThrows(ColaboradorNaoEncontradoException.class,
				() -> buscarColaboradorFisico.execute(id));
		assertEquals("Colaborador com id " + id + " não encontrado", exception.getMessage());
	}

}
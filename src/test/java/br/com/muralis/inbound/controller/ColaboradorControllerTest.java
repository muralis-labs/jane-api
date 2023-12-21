package br.com.muralis.inbound.controller;

import br.com.muralis.core.dto.colaborador.geral.CadastrarColaboradorCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@QuarkusTest
class ColaboradorControllerTest {

	@Test
	@DisplayName("Caso há somente o nome do colaborador no DTO, deve cadastrar com sucesso")
	void deveCadastrarColaboradorComSucesso() {
		var colaborador = CadastrarColaboradorCommand.builder().nome("Fulano").build();
		createColaboradorSuccefully(TipoColaborador.FISICO, colaborador);
		createColaboradorSuccefully(TipoColaborador.JURIDICO, colaborador);
	}

	@Test
	@DisplayName("Quando cadastrado dois colaboradores com o mesmo nome, deve conseguir cadastrar, porém mudar o ID entre eles")
	void deveCadastrarColaboradorComMesmoNome() {
		var colaborador = CadastrarColaboradorCommand.builder().nome("Fulano").build();
		var responseFisico1 = createColaboradorSuccefully(TipoColaborador.FISICO, colaborador);
		var responseFisico2 = createColaboradorSuccefully(TipoColaborador.FISICO, colaborador);
		responseFisico1.body("id", not(equalTo(responseFisico2.extract().body().jsonPath().get("id"))));
		var responseJuridico1 = createColaboradorSuccefully(TipoColaborador.JURIDICO, colaborador);
		var responseJuridico2 = createColaboradorSuccefully(TipoColaborador.JURIDICO, colaborador);
		responseJuridico1.body("id", not(equalTo(responseJuridico2.extract().body().jsonPath().get("id"))));
	}

	@Test
	@DisplayName("Quando CPF inválido, deve retornar erro 400")
	void deveRetornarErro400QuandoCpfInvalido() {
		var colaborador = CadastrarColaboradorCommand.builder().nome("Fulano").cpf("123").build();
		ValidatableResponse response = createColaboradorWithError(TipoColaborador.FISICO, colaborador);
		response.body("mensagem", equalTo("CPF inválido"));
	}

	@Test
	@DisplayName("Quando e-mail inválido, deve retornar erro 400")
	void deveRetornarErro400QuandoEmailInvalido() {
		var colaborador = CadastrarColaboradorCommand.builder().nome("Fulano").email("email").build();
		ValidatableResponse response = createColaboradorWithError(TipoColaborador.FISICO, colaborador);
		response.body("mensagem", equalTo("E-mail inválido"));
	}

	@Test
	@DisplayName("Quando telefone celular inválido, deve retornar erro 400")
	void deveRetornarErro400QuandoTelefoneCelularInvalido() {
		var colaborador = CadastrarColaboradorCommand.builder().nome("Fulano").telefoneCelular("123").build();
		ValidatableResponse response = createColaboradorWithError(TipoColaborador.FISICO, colaborador);
		response.body("mensagem", equalTo("Telefone celular inválido. Ex (11) 99999-9999"));
	}

	@Test
	@DisplayName("Quando telefone residencial inválido, deve retornar erro 400")
	void deveRetornarErro400QuandoTelefoneResidencialInvalido() {
		var colaborador = CadastrarColaboradorCommand.builder().nome("Fulano").telefoneResidencial("123").build();
		ValidatableResponse response = createColaboradorWithError(TipoColaborador.FISICO, colaborador);
		response.body("mensagem", equalTo("Telefone residencial inválido. Ex (11) 99999-9999 ou (11) 9999-9999"));
	}

	private ValidatableResponse createColaboradorSuccefully(TipoColaborador tipoColaborador,
			CadastrarColaboradorCommand command) {
		return RestAssured.given()
			.log()
			.all()
			.contentType("application/json")
			.when()
			.body(toJson(command))
			.post("/v1/colaboradores" + tipoColaborador.getPath())
			.then()
			.log()
			.all()
			.statusCode(201)
			.body("id", Matchers.notNullValue())
			.body("nome", equalTo(command.getNome()));
	}

	private ValidatableResponse createColaboradorWithError(TipoColaborador tipoColaborador,
			CadastrarColaboradorCommand colaborador) {
		return RestAssured.given()
			.log()
			.all()
			.contentType("application/json")
			.when()
			.body(toJson(colaborador))
			.post("/v1/colaboradores" + tipoColaborador.getPath())
			.then()
			.log()
			.all()
			.statusCode(400);
	}

	@Getter
	private enum TipoColaborador {

		FISICO("/fisico"), JURIDICO("/juridico");

		private final String path;

		TipoColaborador(String path) {
			this.path = path;
		}

	}

	private String toJson(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(object);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
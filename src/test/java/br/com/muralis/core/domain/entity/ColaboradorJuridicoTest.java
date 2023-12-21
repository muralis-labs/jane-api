package br.com.muralis.core.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ColaboradorJuridicoTest {

	@Test
	@DisplayName("Caso empresa nula, quando chamar o getEmpresaId, deve retornar nulo")
	void deveRetornarNuloQuandoEmpresaNula() {
		var colaborador = ColaboradorJuridico.builder().build();
		assertNull(colaborador.getEmpresaId());
	}

	@Test
	@DisplayName("Caso empresa não nula, quando chamar o getEmpresaId, deve retornar o ID da empresa")
	void deveRetornarIdEmpresaQuandoEmpresaNaoNula() {
		var colaborador = ColaboradorJuridico.builder().empresa(Empresa.builder().id("123").build()).build();
		assertEquals("123", colaborador.getEmpresaId());
	}

}
package br.com.muralis.inbound.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionMessageWithFieldTest {

	@Test
	@DisplayName("Deve retirar espaços em branco da mensagem")
	void deveRetirarEspacosEmBrancoDaMensagem() {
		var mensagem = "  Mensagem com espaços em branco  ";
		var exceptionMessage = ExceptionMessageWithField.of("mensagem", mensagem);
		assertEquals("Mensagem com espaços em branco", exceptionMessage.getMensagem());
	}

}
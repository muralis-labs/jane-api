package br.com.muralis.core.domain.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class Colaborador {

	private String id;

	@Setter
	private String nome;

	@Setter
	private String sexo;

	@Setter
	private LocalDate nascimento;

	@Setter
	private String estadoCivil;

	@Setter
	private String paisNascimento;

	@Setter
	private String estadoNascimento;

	@Setter
	private String cidadeNascimento;

	@Setter
	private String nomeMae;

	@Setter
	private String email;

	@Setter
	private String telefoneCelular;

	@Setter
	private String telefoneResidencial;

	@Setter
	private String nomePai;

	@Setter
	private String rg;

	@Setter
	private String orgaoExpedidor;

	@Setter
	private LocalDate dataExpedicao;

	@Setter
	private String cpf;

	@Setter
	private String grauInstrucao;

	public void cadastrar() {
		this.id = UUID.randomUUID().toString();
	}

}

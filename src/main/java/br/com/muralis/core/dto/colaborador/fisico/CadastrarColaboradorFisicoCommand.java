package br.com.muralis.core.dto.colaborador.fisico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarColaboradorFisicoCommand {

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@Email(message = "Email inválido")
	@NotBlank(message = "Email é obrigatório")
	private String email;

	@NotBlank(message = "Sexo é obrigatório")
	public String sexo;

	@NotNull(message = "Nascimento é obrigatório")
	public LocalDate nascimento;

	@NotBlank(message = "Estado cívil é obrigatório")
	public String estadoCivil;

	@NotBlank(message = "País de nascimento é obrigatório")
	public String paisNascimento;

	@NotBlank(message = "Estado de nascimento é obrigatório")
	public String estadoNascimento;

	@NotBlank(message = "Cidade de nascimento é obrigatório")
	public String cidadeNascimento;

	@NotBlank(message = "Nome da mãe é obrigatório")
	public String nomeMae;

	@NotBlank(message = "Telefone celular é obrigatório")
	public String telefoneCelular;

	public String telefoneResidencial;

	public String nomePai;

	@NotBlank(message = "RG é obrigatório")
	public String rg;

	@NotBlank(message = "Órgão expedidor é obrigatório")
	public String orgaoExpedidor;

	@NotNull(message = "Órgão expedidor é obrigatório")
	public LocalDate dataExpedicao;

	@NotBlank(message = "CPF é obrigatório")
	@CPF(message = "CPF inválido")
	public String cpf;

	@NotBlank(message = "Grau de instrução é obrigatório")
	public String grauInstrucao;

}

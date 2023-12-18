package br.com.muralis.core.dto.colaborador.juridico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class CadastrarColaboradorJuridicoCommand {

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@Email(message = "Email inválido")
	@NotBlank(message = "Email é obrigatório")
	private String email;

	@NotBlank(message = "Sexo é obrigatório")
	private String sexo;

	@NotNull(message = "Nascimento é obrigatório")
	private LocalDate nascimento;

	@NotBlank(message = "Estado cívil é obrigatório")
	private String estadoCivil;

	@NotBlank(message = "País de nascimento é obrigatório")
	private String paisNascimento;

	@NotBlank(message = "Estado de nascimento é obrigatório")
	private String estadoNascimento;

	@NotBlank(message = "Cidade de nascimento é obrigatório")
	private String cidadeNascimento;

	@NotBlank(message = "Nome da mãe é obrigatório")
	private String nomeMae;

	@NotBlank(message = "Telefone celular é obrigatório")
	@Pattern(regexp = "\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}", message = "Telefone celular inválido. Ex (11) 99999-9999")
	private String telefoneCelular;

	@Pattern(regexp = "\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}", message = "Telefone celular inválido. Ex (11) 99999-9999")
	private String telefoneResidencial;

	private String nomePai;

	@NotBlank(message = "RG é obrigatório")
	private String rg;

	@NotBlank(message = "Órgão expedidor é obrigatório")
	private String orgaoExpedidor;

	@NotNull(message = "Órgão expedidor é obrigatório")
	private LocalDate dataExpedicao;

	@NotBlank(message = "CPF é obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;

	@NotBlank(message = "Grau de instrução é obrigatório")
	private String grauInstrucao;

}

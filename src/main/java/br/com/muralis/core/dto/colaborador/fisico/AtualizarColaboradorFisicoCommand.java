package br.com.muralis.core.dto.colaborador.fisico;

import br.com.muralis.core.objectValue.DadosContratuaisFisico;
import br.com.muralis.core.objectValue.Endereco;
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
public class AtualizarColaboradorFisicoCommand {

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@Email(message = "E-mail inválido")
	private String email;

	private String sexo;

	private LocalDate nascimento;

	private String estadoCivil;

	private String paisNascimento;

	private String estadoNascimento;

	private String cidadeNascimento;

	private String nomeMae;

	@Pattern(regexp = "\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}", message = "Telefone celular inválido. Ex (11) 99999-9999")
	private String telefoneCelular;

	@Pattern(regexp = "\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}",
			message = "Telefone residencial inválido. Ex (11) 99999-9999")
	private String telefoneResidencial;

	private String nomePai;

	private String rg;

	private String orgaoExpedidor;

	private LocalDate dataExpedicao;

	@CPF(message = "CPF inválido")
	private String cpf;

	private String grauInstrucao;

	private Endereco endereco;

	private DadosContratuaisFisico dadosContratuaisFisico;

}

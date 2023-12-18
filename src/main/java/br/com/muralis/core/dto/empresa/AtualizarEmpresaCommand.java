package br.com.muralis.core.dto.empresa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarEmpresaCommand {

	@NotBlank(message = "Email é obrigatório")
	@Email
	public String email;

	@NotBlank(message = "Razão social é obrigatório")
	public String razaoSocial;

	@NotBlank(message = "CNPJ é obrigatório")
	@CNPJ(message = "CNPJ inválido")
	public String cnpj;

	@NotBlank(message = "Data de contrato é obrigatório")
	public LocalDate dataContrato;

	@NotBlank(message = "Regime tributário é obrigatório")
	public String regimeTributario;

	@NotBlank(message = "Objeto contratual é obrigatório")
	public String objetoContratual;

	@NotBlank(message = "Mensalidade de contrato é obrigatório")
	public String mensalidadeContrato;

	@NotBlank(message = "Inscrição municipal é obrigatório")
	public String inscricaoMunicipal;

	@NotBlank(message = "Inscrição estadual é obrigatório")
	public String inscricaoEstadual;

	@NotBlank(message = "CEP é obrigatório")
	@Length(min = 8, max = 8, message = "CEP deve conter 8 dígitos")
	public String cep;

	@NotBlank(message = "Estado é obrigatório")
	public String estado;

	@NotBlank(message = "Cidade é obrigatório")
	public String cidade;

	@NotBlank(message = "Bairro é obrigatório")
	public String bairro;

	@NotBlank(message = "Endereço é obrigatório")
	public String endereco;

	@NotBlank(message = "Número é obrigatório")
	public Integer numero;

	@NotBlank(message = "Telefone é obrigatório")
	public String telefone;

	public String complemento;

}

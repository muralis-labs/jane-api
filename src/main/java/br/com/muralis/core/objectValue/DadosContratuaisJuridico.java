package br.com.muralis.core.objectValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosContratuaisJuridico {

	public LocalDate dataContrato;

	public String CNPJ;

	public String razaoSocial;

	public String regimeSocial;

	public String objetoContratual;

	public String mensalidadeContrato;

	public String inscricaoMunicipal;

	public String inscricaoEstadual;

}

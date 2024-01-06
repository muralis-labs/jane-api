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
public class DadosContratuaisFisico {

	public String cargo;

	public String salario;

	public String regime;

	public String numeroPIS;

	public String numeroCTPS;

	public String serieCTPS;

	public LocalDate dataAdmissao;

	public String fluxoAdmissao;

}

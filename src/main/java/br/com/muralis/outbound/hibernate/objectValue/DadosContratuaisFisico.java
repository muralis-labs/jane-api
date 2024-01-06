package br.com.muralis.outbound.hibernate.objectValue;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
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

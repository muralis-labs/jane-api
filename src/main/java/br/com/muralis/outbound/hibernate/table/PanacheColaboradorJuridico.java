package br.com.muralis.outbound.hibernate.table;

import br.com.muralis.outbound.hibernate.objectValue.DadosContratuaisJuridico;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class PanacheColaboradorJuridico extends PanacheColaborador {

	@ManyToOne
	public PanacheEmpresa empresa;

	@Embedded
	public DadosContratuaisJuridico dadosContratuaisJuridico;


}

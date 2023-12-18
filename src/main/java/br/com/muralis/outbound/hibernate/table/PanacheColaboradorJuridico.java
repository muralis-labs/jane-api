package br.com.muralis.outbound.hibernate.table;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class PanacheColaboradorJuridico extends PanacheColaborador {

	@ManyToOne
	public PanacheEmpresa empresa;

}

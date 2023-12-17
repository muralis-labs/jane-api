package br.com.muralis.outbound.hibernate.table;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
@Entity
public class PanacheColaboradorPJ extends PanacheColaborador {
    @ManyToOne
    public PanacheEmpresa empresa;
}

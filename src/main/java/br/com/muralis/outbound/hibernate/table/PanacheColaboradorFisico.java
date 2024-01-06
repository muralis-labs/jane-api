package br.com.muralis.outbound.hibernate.table;

import br.com.muralis.outbound.hibernate.objectValue.DadosContratuaisFisico;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class PanacheColaboradorFisico extends PanacheColaborador {
    @Embedded
    public DadosContratuaisFisico dadosContratuaisFisico;
}

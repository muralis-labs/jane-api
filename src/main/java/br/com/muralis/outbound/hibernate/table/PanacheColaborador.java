package br.com.muralis.outbound.hibernate.table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "colaborador", indexes = {
        @Index(name = "idx_colaborador_email", columnList = "email", unique = true)
})
public class PanacheColaborador extends PanacheEntityBase {

    @Id
    public String id;

    public String nome;

    public String email;

}

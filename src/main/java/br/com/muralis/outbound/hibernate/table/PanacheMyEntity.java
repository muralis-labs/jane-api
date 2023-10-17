package br.com.muralis.outbound.hibernate.table;

import br.com.muralis.core.domain.entity.MyEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class PanacheMyEntity extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(generator = "uuid")
    public String id;

    public String field;

    // TODO: add mapstruct to replace this method
    public static PanacheMyEntity from(MyEntity myEntity) {
        var panacheMyEntity = new PanacheMyEntity();
        panacheMyEntity.field = myEntity.getField();
        return panacheMyEntity;
    }

}

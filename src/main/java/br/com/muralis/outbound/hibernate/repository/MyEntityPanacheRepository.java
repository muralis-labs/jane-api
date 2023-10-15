package br.com.muralis.outbound.hibernate.repository;

import br.com.muralis.core.domain.entity.MyEntity;
import br.com.muralis.core.domain.repository.MyEntityRepository;
import br.com.muralis.outbound.hibernate.table.PanacheMyEntity;

public class MyEntityPanacheRepository implements MyEntityRepository {

    @Override
    public MyEntity save(MyEntity myEntity) {
        PanacheMyEntity panacheMyEntity = PanacheMyEntity.from(myEntity);
        panacheMyEntity.persist();
        return myEntity;
    }

}

package br.com.muralis.core.domain.repository;

import br.com.muralis.core.domain.entity.MyEntity;

public interface MyEntityRepository {
    
    MyEntity save(MyEntity myEntity);

}

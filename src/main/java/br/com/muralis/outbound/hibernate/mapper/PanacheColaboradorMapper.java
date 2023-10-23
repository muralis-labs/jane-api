package br.com.muralis.outbound.hibernate.mapper;

import br.com.muralis.core.domain.entity.Colaborador;
import br.com.muralis.outbound.hibernate.table.PanacheColaborador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PanacheColaboradorMapper {

    PanacheColaborador from(Colaborador colaborador);

    Colaborador from(PanacheColaborador colaborador);

}

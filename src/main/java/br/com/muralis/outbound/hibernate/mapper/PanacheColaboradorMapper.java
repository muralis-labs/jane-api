package br.com.muralis.outbound.hibernate.mapper;

import br.com.muralis.core.domain.entity.Colaborador;
import br.com.muralis.outbound.hibernate.table.PanacheColaborador;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PanacheColaboradorMapper {

	Colaborador from(PanacheColaborador colaborador);

	List<Colaborador> from(List<PanacheColaborador> list);

}

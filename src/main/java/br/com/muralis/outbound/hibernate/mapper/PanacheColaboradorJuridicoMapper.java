package br.com.muralis.outbound.hibernate.mapper;

import br.com.muralis.core.domain.entity.ColaboradorJuridico;
import br.com.muralis.outbound.hibernate.table.PanacheColaboradorJuridico;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PanacheColaboradorJuridicoMapper {

	PanacheColaboradorJuridico from(ColaboradorJuridico colaborador);

	ColaboradorJuridico from(PanacheColaboradorJuridico colaborador);

	List<ColaboradorJuridico> from(List<PanacheColaboradorJuridico> list);

	PanacheColaboradorJuridico update(@MappingTarget PanacheColaboradorJuridico panacheColaborador,
			ColaboradorJuridico colaboradorParaAtualizar);

}

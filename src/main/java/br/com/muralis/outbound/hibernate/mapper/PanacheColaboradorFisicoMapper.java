package br.com.muralis.outbound.hibernate.mapper;

import br.com.muralis.core.domain.entity.ColaboradorFisico;
import br.com.muralis.outbound.hibernate.table.PanacheColaboradorFisico;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PanacheColaboradorFisicoMapper {

	PanacheColaboradorFisico from(ColaboradorFisico colaborador);

	ColaboradorFisico from(PanacheColaboradorFisico colaborador);

	List<ColaboradorFisico> from(List<PanacheColaboradorFisico> list);

	PanacheColaboradorFisico update(@MappingTarget PanacheColaboradorFisico panacheColaborador,
			ColaboradorFisico colaboradorParaAtualizar);

}

package br.com.muralis.core.mapper;

import br.com.muralis.core.domain.entity.ColaboradorFisico;
import br.com.muralis.core.dto.colaborador.fisico.AtualizarColaboradorFisicoCommand;
import br.com.muralis.core.dto.colaborador.fisico.CadastrarColaboradorFisicoCommand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface ColaboradorFisicoMapper {

	ColaboradorFisico from(CadastrarColaboradorFisicoCommand command);

	ColaboradorFisico update(@MappingTarget ColaboradorFisico colaborador, AtualizarColaboradorFisicoCommand command);

}

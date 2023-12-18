package br.com.muralis.core.mapper;

import br.com.muralis.core.domain.entity.ColaboradorJuridico;
import br.com.muralis.core.dto.colaborador.juridico.AtualizarColaboradorJuridicoCommand;
import br.com.muralis.core.dto.colaborador.juridico.CadastrarColaboradorJuridicoCommand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface ColaboradorJuridicoMapper {

	ColaboradorJuridico from(CadastrarColaboradorJuridicoCommand command);

	ColaboradorJuridico update(@MappingTarget ColaboradorJuridico colaborador,
			AtualizarColaboradorJuridicoCommand command);

}

package br.com.muralis.core.mapper;

import br.com.muralis.core.domain.entity.Colaborador;
import br.com.muralis.core.dto.AtualizarColaboradorCommand;
import br.com.muralis.core.dto.CadastrarColaboradorCommand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface ColaboradorMapper {

    Colaborador from(CadastrarColaboradorCommand command);

    Colaborador update(@MappingTarget Colaborador colaborador, AtualizarColaboradorCommand command);

}

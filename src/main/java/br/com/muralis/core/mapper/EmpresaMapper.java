package br.com.muralis.core.mapper;

import br.com.muralis.core.domain.entity.Empresa;
import br.com.muralis.core.dto.empresa.AtualizarEmpresaCommand;
import br.com.muralis.core.dto.empresa.CadastrarEmpresaCommand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface EmpresaMapper {

	Empresa from(CadastrarEmpresaCommand command);

	Empresa update(@MappingTarget Empresa empresa, AtualizarEmpresaCommand command);

}

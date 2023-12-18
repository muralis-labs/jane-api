package br.com.muralis.outbound.hibernate.mapper;

import br.com.muralis.core.domain.entity.Empresa;
import br.com.muralis.outbound.hibernate.table.PanacheEmpresa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PanacheEmpresaMapper {

	Empresa from(PanacheEmpresa empresa);

	PanacheEmpresa from(Empresa empresa);

	List<Empresa> from(List<PanacheEmpresa> list);

	PanacheEmpresa update(@MappingTarget PanacheEmpresa panacheEmpresa, Empresa empresaParaAtualizar);

}

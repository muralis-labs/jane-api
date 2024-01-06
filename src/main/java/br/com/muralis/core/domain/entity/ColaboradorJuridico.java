package br.com.muralis.core.domain.entity;

import br.com.muralis.outbound.hibernate.objectValue.DadosContratuaisJuridico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@Getter
@SuperBuilder
public class ColaboradorJuridico extends Colaborador {

	@JsonIgnore
	private Empresa empresa;

	@Setter
	private DadosContratuaisJuridico dadosContratuaisJuridico;

	public String getEmpresaId() {
		return Optional.ofNullable(empresa).map(Empresa::getId).orElse(null);
	}

}

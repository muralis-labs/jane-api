package br.com.muralis.core.domain.entity;

import br.com.muralis.core.objectValue.DadosContratuaisFisico;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@RegisterForReflection
public class ColaboradorFisico extends Colaborador {

	@Setter
	private DadosContratuaisFisico dadosContratuaisFisico;

}

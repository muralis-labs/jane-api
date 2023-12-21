package br.com.muralis.core.dto.colaborador.juridico;

import br.com.muralis.core.dto.colaborador.geral.CadastrarColaboradorCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class CadastrarColaboradorJuridicoCommand extends CadastrarColaboradorCommand {

	private String empresaId;

}

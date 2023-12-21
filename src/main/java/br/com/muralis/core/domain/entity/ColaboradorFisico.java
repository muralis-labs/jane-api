package br.com.muralis.core.domain.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@RegisterForReflection
public class ColaboradorFisico extends Colaborador {

}

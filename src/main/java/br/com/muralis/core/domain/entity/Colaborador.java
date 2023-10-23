package br.com.muralis.core.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
public class Colaborador {

    private String id;

    @Setter
    private String nome;

    @Setter
    private String email;

    public void prepararParaSalvar() {
        this.id = UUID.randomUUID().toString();
    }

}

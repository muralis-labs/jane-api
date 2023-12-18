package br.com.muralis.core.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public abstract class Colaborador {

    private String id;

    @Setter
    private String nome;

    @Setter
    private String email;

    @Setter
    private String sexo;

    @Setter
    private LocalDate nascimento;

    @Setter
    private String estadoCivil;

    @Setter
    private String paisNascimento;

    @Setter
    private String estadoNascimento;

    @Setter
    private String cidadNascimento;

    @Setter
    private String nomeMae;

    @Setter
    private String telefoneCelular;

    @Setter
    private String telefoneResidencial;

    @Setter
    private String nomePai;

    @Setter
    private String RG;

    @Setter
    private String orgaoExpedidor;

    @Setter
    private LocalDate dataExpedicao;

    @Setter
    private String CPF;

    @Setter
    private String grauInstrucao;

    public void cadastrar() {
        this.id = UUID.randomUUID().toString();
    }

}

package br.com.muralis.core.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class Empresa {
    private String id;

    @Setter
    private String email;

    @Setter
    private String razaoSocial;

    @Setter
    private String CNPJ;

    @Setter
    private LocalDate dataContrato;

    @Setter
    private String regimeTributario;

    @Setter
    private String objetoContratual;

    @Setter
    private String mensalidadeContrato;

    @Setter
    private String inscricaoMunicipal;

    @Setter
    private String inscricaoEstadual;

    @Setter
    private String CEP;

    @Setter
    private String estado;

    @Setter
    private String cidade;

    @Setter
    private String bairro;

    @Setter
    private String endereco;

    @Setter
    private Integer numero;

    @Setter
    private String complemento;

    @Setter
    private String telefone;

    public void cadastrar() {
        this.id = UUID.randomUUID().toString();
    }

}

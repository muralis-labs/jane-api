package br.com.muralis.outbound.hibernate.objectValue;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;
@Embeddable
public class DadosContratuaisJuridico {
    public LocalDate dataContrato;
    public String CNPJ;
    public String razaoSocial;
    public String regimeSocial;
    public String objetoContratual;
    public String mensalidadeContrato;
    public String inscricaoMunicipal;
    public String inscricaoEstadual;
}

package br.com.muralis.outbound.hibernate.table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empresa")
public class PanacheEmpresa extends PanacheEntityBase {
    @Id
    public String id;
    public String email;
    public String razaoSocial;
    public String CNPJ;
    public LocalDate dataContrato;
    public String regimeTributario;
    public String objetoContratual;
    public String mensalidadeContrato;
    public String inscricaoMunicipal;
    public String inscricaoEstadual;
    public String CEP;
    public String estado;
    public String cidade;
    public String bairro;
    public String endereco;
    public Integer numero;
    public String complemento;
    public String telefone;
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<PanacheColaboradorPJ> colaboradoresPJ = new ArrayList<>();
}
package br.com.muralis.outbound.hibernate.table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "colaborador", indexes = {
        @Index(name = "idx_colaborador_email", columnList = "email", unique = true)
})
public class PanacheColaborador extends PanacheEntityBase {

    @Id
    public String id;
    public String nome;

    public String email;
    public String sexo;
    public LocalDate nascimento;
    public String estadoCivil;
    public String paisNascimento;
    public String estadoNascimento;
    public String cidadNascimento;
    public String nomeMae;
    public String telefoneCelular;
    public String telefoneResidencial;
    public String nomePai;
    public String RG;
    public String orgaoExpedidor;
    public LocalDate dataExpedicao;
    public String CPF;
    public String grauInstrucao;
}

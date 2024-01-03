package br.com.muralis.outbound.hibernate.table;

import br.com.muralis.outbound.hibernate.objectValue.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "colaborador", indexes = { @Index(name = "idx_colaborador_email", columnList = "email", unique = true),
		@Index(name = "idx_colaborador_cpf", columnList = "cpf", unique = true) })
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

	public String cidadeNascimento;

	public String nomeMae;

	public String telefoneCelular;

	public String telefoneResidencial;

	public String nomePai;

	public String rg;

	public String orgaoExpedidor;

	public LocalDate dataExpedicao;

	public String cpf;

	public String grauInstrucao;
	@Embedded
	public Endereco endereco;

}

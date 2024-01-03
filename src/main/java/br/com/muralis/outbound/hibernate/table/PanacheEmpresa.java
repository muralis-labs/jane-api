package br.com.muralis.outbound.hibernate.table;

import br.com.muralis.outbound.hibernate.objectValue.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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

	public String cnpj;

	public LocalDate dataContrato;

	public String regimeTributario;

	public String objetoContratual;

	public String mensalidadeContrato;

	public String inscricaoMunicipal;

	public String inscricaoEstadual;

	@Embedded
	public Endereco endereco;

	public String telefone;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<PanacheColaboradorJuridico> colaboradoresJuridicos = new ArrayList<>();

}
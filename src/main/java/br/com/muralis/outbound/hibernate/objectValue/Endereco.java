package br.com.muralis.outbound.hibernate.objectValue;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

	public String cep;

	public String estado;

	public String cidade;

	public String bairro;

	public String endereco;

	public Integer numero;

	public String complemento;

}

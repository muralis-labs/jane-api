package br.com.muralis.core.objectValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    public String cep;

    public String estado;

    public String cidade;

    public String bairro;

    public String endereco;

    public Integer numero;

    public String complemento;
}

package br.com.lucas.pagamentos.dto.empresa;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpresaDto implements Serializable {

    private String razaosocial;

    private String nomefantasia;

    private String cnpj;

    private String fundacao;

    private String endereco;

    private String cidade;

    private String estado;

    private String telefone;

}

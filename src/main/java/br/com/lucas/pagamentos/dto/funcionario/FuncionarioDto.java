package br.com.lucas.pagamentos.dto.funcionario;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDto implements Serializable {

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String cpf;

    @Getter
    @Setter
    private String endereco;

    @Getter
    @Setter
    private String cidade;

    @Getter
    @Setter
    private String estado;

    @Getter
    @Setter
    private String telefone;

    @Getter
    @Setter
    private String datanasc;

    @Getter
    @Setter
    private String sexo;

    @Getter
    @Setter
    private Long empresaid;

    @Getter
    @Setter
    private String dataadmissao;

}

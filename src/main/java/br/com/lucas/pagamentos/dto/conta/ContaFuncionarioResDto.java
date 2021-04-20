package br.com.lucas.pagamentos.dto.conta;

import br.com.lucas.pagamentos.model.conta.ContaFuncionario;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class ContaFuncionarioResDto {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private BigDecimal saldo;

    @Getter
    @Setter
    private Long funcionarioid;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String empresa;

    public ContaFuncionarioResDto fromContaFuncionario(ContaFuncionario contaFuncionario){
        this.setId(contaFuncionario.getId());
        this.setSaldo(contaFuncionario.getSaldo());
        this.setFuncionarioid(contaFuncionario.getId());
        this.setNome(contaFuncionario.getFuncionario().getNome());
        this.setEmpresa(contaFuncionario.getFuncionario().getEmpresa().getNomefantasia());

        return this;
    }

}

package br.com.lucas.pagamentos.dto.conta;

import br.com.lucas.pagamentos.model.conta.ContaEmpresa;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class ContaEmpresaResDto {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private BigDecimal saldo;

    @Getter
    @Setter
    private Long empresaid;

    @Getter
    @Setter
    private String nomefantasia;

    public ContaEmpresaResDto fromContaEmpresa(ContaEmpresa contaEmpresa){
        this.setId(contaEmpresa.getId());
        this.setSaldo(contaEmpresa.getSaldo());
        this.setEmpresaid(contaEmpresa.getEmpresa().getId());
        this.setNomefantasia(contaEmpresa.getEmpresa().getNomefantasia());

        return this;
    }
}

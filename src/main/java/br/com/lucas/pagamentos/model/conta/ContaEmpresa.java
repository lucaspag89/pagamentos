package br.com.lucas.pagamentos.model.conta;

import br.com.lucas.pagamentos.model.empresa.Empresa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contaempresa")
public class ContaEmpresa implements Serializable {

    @Getter
    @Setter
    @Id
    @SequenceGenerator(name = "contaempresa_id_seq",
            sequenceName = "contaempresa_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contaempresa_id_seq")
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "saldo")
    private BigDecimal saldo;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "empresaid", referencedColumnName = "id")
    private Empresa empresa;

    public ContaEmpresa fromEmpresa(Empresa empresa) {
        this.saldo = new BigDecimal("0");
        this.empresa = empresa;

        return this;
    }

    public ContaEmpresa getFromDto(Long id, Empresa empresa, BigDecimal saldoEmpresa) {
        this.id = id;
        this.saldo = saldoEmpresa;
        this.empresa = empresa;

        return this;
    }

    public ContaEmpresa getFromPagamento(Long id, Empresa empresa, BigDecimal saldoEmpresa) {
        this.id = id;
        this.saldo = saldoEmpresa;
        this.empresa = empresa;

        return this;
    }

}

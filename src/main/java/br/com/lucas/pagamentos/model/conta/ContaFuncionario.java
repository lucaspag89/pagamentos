package br.com.lucas.pagamentos.model.conta;

import br.com.lucas.pagamentos.model.funcionario.Funcionario;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contafuncionario")
public class ContaFuncionario implements Serializable {

    @Getter
    @Setter
    @Id
    @SequenceGenerator(name = "contafuncionario_id_seq",
            sequenceName = "contafuncionario_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contafuncionario_id_seq")
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "saldo")
    private BigDecimal saldo;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "funcionarioid", referencedColumnName = "id")
    private Funcionario funcionario;

    public ContaFuncionario fromFuncionario(Funcionario funcionario) {
        this.saldo = new BigDecimal("0");
        this.funcionario = funcionario;

        return this;
    }

    public ContaFuncionario getFromPagamento(Long id, Funcionario funcionario, BigDecimal saldoFuncionario) {
        this.id = id;
        this.saldo = saldoFuncionario;
        this.funcionario = funcionario;

        return this;
    }
}

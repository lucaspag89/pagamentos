package br.com.lucas.pagamentos.dto.conta;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class ContaFuncionarioDto {

    @Getter
    @Setter
    private Long funcionarioid;

    @Getter
    @Setter
    private Long empresaid;

    @Getter
    @Setter
    private String pagamento;

    public ContaFuncionarioDto(Long funcionarioid) {

        this.funcionarioid = funcionarioid;

    }
}

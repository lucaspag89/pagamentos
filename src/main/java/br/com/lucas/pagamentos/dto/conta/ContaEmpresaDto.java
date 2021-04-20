package br.com.lucas.pagamentos.dto.conta;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class ContaEmpresaDto {

    @Getter
    @Setter
    private Long empresaid;

    @Getter
    @Setter
    private String deposito;

    public ContaEmpresaDto(Long empresaid) {
        this.empresaid = empresaid;
    }
}

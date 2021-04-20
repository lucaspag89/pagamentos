package br.com.lucas.pagamentos.dto.funcionario;

import br.com.lucas.pagamentos.model.Estado;
import br.com.lucas.pagamentos.model.funcionario.Funcionario;
import br.com.lucas.pagamentos.model.funcionario.Sexo;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioResDto {

    @Getter
    @Setter
    private Long id;

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
    private Estado estado;

    @Getter
    @Setter
    private String telefone;

    @Getter
    @Setter
    private String datanasc;

    @Getter
    @Setter
    private Sexo sexo;

    @Getter
    @Setter
    private String dataregistro;

    @Getter
    @Setter
    private Long empresaid;

    @Getter
    @Setter
    private String dataadmissao;

    public FuncionarioResDto fromFuncionario(Funcionario funcionario) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        this.setId(funcionario.getId());
        this.setNome(funcionario.getNome());
        this.setCpf(funcionario.getCpf());
        this.setEndereco(funcionario.getEndereco());
        this.setCidade(funcionario.getCidade());
        this.setEstado(funcionario.getEstado());
        this.setTelefone(funcionario.getTelefone());
        this.setDatanasc(df.format(funcionario.getDatanasc()));
        this.setSexo(funcionario.getSexo());
        this.setDataregistro(df.format(funcionario.getDataregistro()));
        this.setEmpresaid(funcionario.getEmpresa().getId());
        this.setDataadmissao(df.format(funcionario.getDataadmissao()));

        return this;

    }
}

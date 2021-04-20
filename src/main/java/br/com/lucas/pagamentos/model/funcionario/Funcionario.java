package br.com.lucas.pagamentos.model.funcionario;

import br.com.lucas.pagamentos.dto.funcionario.FuncionarioDto;
import br.com.lucas.pagamentos.model.Estado;
import br.com.lucas.pagamentos.model.conta.ContaFuncionario;
import br.com.lucas.pagamentos.model.empresa.Empresa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    @Getter
    @Setter
    @Id
    @SequenceGenerator(name = "funcionario_id_seq", sequenceName = "funcionario_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_id_seq")
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
    @JsonIgnore
    private ContaFuncionario contaFuncionario;

    @Getter
    @Setter
    @Column(name = "nome")
    private String nome;

    @Getter
    @Setter
    @Column(name = "cpf")
    private String cpf;

    @Getter
    @Setter
    @Column(name = "endereco")
    private String endereco;

    @Getter
    @Setter
    @Column(name = "cidade")
    private String cidade;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @Getter
    @Setter
    @Column(name = "telefone")
    private String telefone;

    @Getter
    @Setter
    @Column(name = "datanasc")
    private Date datanasc;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private Sexo sexo;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "dataregistro")
    private Date dataregistro;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "empresaid", nullable = false, foreignKey = @ForeignKey(name = "empresaid"))
    private Empresa empresa;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "dataadmissao")
    private Date dataadmissao;

    public Funcionario getFromDto(FuncionarioDto funcionarioDto, Empresa empresa) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        this.nome = funcionarioDto.getNome();
        this.cpf = funcionarioDto.getCpf();
        this.endereco = funcionarioDto.getEndereco();
        this.cidade = funcionarioDto.getCidade();
        this.estado = Estado.valueOf(funcionarioDto.getEstado().toUpperCase());
        this.telefone = funcionarioDto.getTelefone();
        try {
            this.datanasc = funcionarioDto.getDatanasc() != null ? df.parse(funcionarioDto.getDatanasc()) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.sexo = Sexo.valueOf(funcionarioDto.getSexo().toUpperCase());
        this.dataregistro = new Date();
        this.empresa = empresa;
        try {
            this.dataadmissao = funcionarioDto.getDataadmissao() != null
                    ? df.parse(funcionarioDto.getDataadmissao()) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return this;
    }

    public Funcionario getFromDto(FuncionarioDto funcionarioDto, Funcionario funcionario, Empresa empresa) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        this.id = funcionario.getId();
        this.nome = funcionarioDto.getNome();
        this.cpf = funcionarioDto.getCpf();
        this.endereco = funcionarioDto.getEndereco();
        this.cidade = funcionarioDto.getCidade();
        this.estado = Estado.valueOf(funcionarioDto.getEstado().toUpperCase());
        this.telefone = funcionarioDto.getTelefone();
        try {
            this.datanasc = funcionarioDto.getDatanasc() != null ? df.parse(funcionarioDto.getDatanasc()) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.sexo = Sexo.valueOf(funcionarioDto.getSexo().toUpperCase());
        this.dataregistro = funcionario.getDataregistro();
        this.empresa = empresa;
        try {
            this.dataadmissao = funcionarioDto.getDataadmissao() != null ? df.parse(funcionarioDto.getDataadmissao()) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return this;
    }
}

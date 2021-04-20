package br.com.lucas.pagamentos.model.empresa;

import br.com.lucas.pagamentos.dto.empresa.EmpresaDto;
import br.com.lucas.pagamentos.model.Estado;
import br.com.lucas.pagamentos.model.conta.ContaEmpresa;
import br.com.lucas.pagamentos.model.funcionario.Funcionario;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "empresa")
public class Empresa implements Serializable {

    @Getter
    @Setter
    @Id
    @SequenceGenerator(name="empresa_id_seq", sequenceName = "empresa_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_id_seq")
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    @Getter
    @Setter
    @OneToOne(mappedBy = "empresa", cascade = CascadeType.ALL)
    private ContaEmpresa contaEmpresa;

    @Getter
    @Setter
    @Column(name = "razaosocial")
    private String razaosocial;

    @Getter
    @Setter
    @Column(name = "nomefantasia")
    private String nomefantasia;

    @Getter
    @Setter
    @Column(name = "cnpj")
    private String cnpj;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "fundacao")
    private Date fundacao;

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
    @Temporal(TemporalType.DATE)
    @Column(name = "dataregistro")
    private Date dataregistro;

    public Empresa getFromDto(EmpresaDto empresaDto) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        this.razaosocial = empresaDto.getRazaosocial();
        this.nomefantasia = empresaDto.getNomefantasia();
        this.cnpj = empresaDto.getCnpj();
        try {
            this.fundacao = empresaDto.getFundacao() != null ? df.parse(empresaDto.getFundacao()) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.endereco = empresaDto.getEndereco();
        this.cidade = empresaDto.getCidade();
        this.estado = Estado.valueOf(empresaDto.getEstado().toUpperCase());
        this.telefone = empresaDto.getTelefone();
        this.dataregistro = new Date();

        return this;
    }

    public Empresa getFromDto(EmpresaDto empresaDto, Empresa empresa) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        this.id = empresa.getId();
        this.razaosocial = empresaDto.getRazaosocial();
        this.nomefantasia = empresaDto.getNomefantasia();
        this.cnpj = empresaDto.getCnpj();
        try {
            this.fundacao = empresaDto.getFundacao() != null ? df.parse(empresaDto.getFundacao()) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.endereco = empresaDto.getEndereco();
        this.cidade = empresaDto.getCidade();
        this.estado = Estado.valueOf(empresaDto.getEstado().toUpperCase());
        this.telefone = empresaDto.getTelefone();
        this.dataregistro = empresa.getDataregistro();

        return this;
    }
}

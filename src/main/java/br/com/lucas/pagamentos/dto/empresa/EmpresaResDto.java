package br.com.lucas.pagamentos.dto.empresa;

import br.com.lucas.pagamentos.model.empresa.Empresa;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Data
public class EmpresaResDto {

    private Long id;

    private String razaosocial;

    private String nomefantasia;

    private String cnpj;

    private String fundacao;

    private String endereco;

    private String cidade;

    private String estado;

    private String telefone;

    private String dataregistro;

    public EmpresaResDto fromEmpresa (Empresa empresa) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        this.setId(empresa.getId());
        this.setRazaosocial(empresa.getRazaosocial());
        this.setNomefantasia(empresa.getNomefantasia());
        this.setCnpj(empresa.getCnpj());
        this.setFundacao(df.format(empresa.getFundacao()));
        this.setEndereco(empresa.getEndereco());
        this.setCidade(empresa.getCidade());
        this.setEstado(empresa.getEstado().name());
        this.setTelefone(empresa.getTelefone());
        this.setDataregistro(df.format(empresa.getDataregistro()));

        return this;

    }
}

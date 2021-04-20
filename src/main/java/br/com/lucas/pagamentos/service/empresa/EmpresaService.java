package br.com.lucas.pagamentos.service.empresa;

import br.com.lucas.pagamentos.dto.empresa.EmpresaDto;
import br.com.lucas.pagamentos.dto.empresa.EmpresaResDto;
import br.com.lucas.pagamentos.model.empresa.Empresa;

import java.util.List;

public interface EmpresaService {

    List<EmpresaResDto> findAllEmpresas();

    EmpresaResDto findByEmpresaId(Long id);

    EmpresaResDto registerEmpresa(EmpresaDto empresaDto);

    EmpresaResDto updateEmpresa(Long id, EmpresaDto empresaDto);

    Boolean removeEmpresa(Long id);

}

package br.com.lucas.pagamentos.service.conta;

import br.com.lucas.pagamentos.dto.conta.ContaEmpresaDto;
import br.com.lucas.pagamentos.dto.conta.ContaEmpresaResDto;
import br.com.lucas.pagamentos.dto.conta.ContaFuncionarioDto;
import br.com.lucas.pagamentos.dto.conta.ContaFuncionarioResDto;

import java.util.List;

public interface ContaService {

    List<ContaEmpresaResDto> findAllContasEmpresas();

    ContaEmpresaResDto findContaEmpresaById(Long id);

    ContaEmpresaResDto updateContaEmpresa(Long id, ContaEmpresaDto contaEmpresaDto);

    List<ContaFuncionarioResDto> findAllContasFuncionarios();

    ContaFuncionarioResDto findContaFuncionarioById(Long id);

    ContaFuncionarioResDto updateContaFuncionario(Long id, ContaFuncionarioDto contaFuncionarioDto);

}

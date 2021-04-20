package br.com.lucas.pagamentos.service.funcionario;

import br.com.lucas.pagamentos.dto.funcionario.FuncionarioDto;
import br.com.lucas.pagamentos.dto.funcionario.FuncionarioResDto;
import br.com.lucas.pagamentos.model.funcionario.Funcionario;

import java.util.List;

public interface FuncionarioService {

    List<FuncionarioResDto> findAllFuncionarios();

    FuncionarioResDto findByFuncionarioId(Long id);

    FuncionarioResDto registerFuncionario(FuncionarioDto FuncionarioDto);

    FuncionarioResDto updateFuncionario(Long id, FuncionarioDto FuncionarioDto);

    Boolean removeFuncionario(Long id);

}

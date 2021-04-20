package br.com.lucas.pagamentos.service.funcionario;

import br.com.lucas.pagamentos.dto.funcionario.FuncionarioDto;
import br.com.lucas.pagamentos.dto.funcionario.FuncionarioResDto;
import br.com.lucas.pagamentos.model.conta.ContaFuncionario;
import br.com.lucas.pagamentos.model.empresa.Empresa;
import br.com.lucas.pagamentos.model.funcionario.Funcionario;
import br.com.lucas.pagamentos.repository.EmpresaRepository;
import br.com.lucas.pagamentos.repository.FuncionarioRepository;
import br.com.lucas.pagamentos.repository.conta.ContaFuncionarioRepository;
import br.com.lucas.pagamentos.service.conta.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    ContaFuncionarioRepository contaFuncionarioRepository;

    @Override
    public List<FuncionarioResDto> findAllFuncionarios() {

        List<Funcionario> funcionarioList = (List<Funcionario>) funcionarioRepository.findAll();
        List<FuncionarioResDto> funcionarios = new ArrayList<>();

        if (funcionarioList.size() > 0) {
            funcionarioList.forEach(funcionario ->
                    funcionarios.add(new FuncionarioResDto().fromFuncionario(funcionario)));

            return funcionarios;
        }

        return funcionarios;
    }

    @Override
    public FuncionarioResDto findByFuncionarioId(Long id) {
        Funcionario funcionario = funcionarioRepository.findFirstById(id);

        if (funcionario != null) {
            return new FuncionarioResDto().fromFuncionario(funcionario);
        }

        return null;
    }

    @Override
    public FuncionarioResDto registerFuncionario(FuncionarioDto funcionarioDto) {

        Empresa empresa = empresaRepository.findFirstById(funcionarioDto.getEmpresaid());

        if (empresa != null) {

            Funcionario funcionario = new Funcionario().getFromDto(funcionarioDto, empresa);

            funcionarioRepository.save(funcionario);

            createContaFuncionario(funcionario);

            return new FuncionarioResDto().fromFuncionario(funcionario);

        }

        return null;
    }

    private void createContaFuncionario(Funcionario funcionario) {

        contaFuncionarioRepository.save(new ContaFuncionario().fromFuncionario(funcionario));

    }

    @Override
    public FuncionarioResDto updateFuncionario(Long id, FuncionarioDto funcionarioDto) {

        Funcionario currentFuncionario = funcionarioRepository.findFirstById(id);
        Empresa empresa = empresaRepository.findFirstById(funcionarioDto.getEmpresaid());

        if (empresa != null && currentFuncionario != null) {
            funcionarioRepository.save(new Funcionario().getFromDto(funcionarioDto,
                    currentFuncionario, empresa));

            return new FuncionarioResDto().fromFuncionario(currentFuncionario);
        }

        return null;
    }

    @Override
    public Boolean removeFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepository.findFirstById(id);

        if (funcionario != null) {
            funcionarioRepository.delete(funcionario);

            return true;
        }

        return false;
    }

}

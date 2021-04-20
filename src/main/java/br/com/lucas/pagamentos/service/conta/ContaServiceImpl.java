package br.com.lucas.pagamentos.service.conta;

import br.com.lucas.pagamentos.dto.conta.ContaEmpresaDto;
import br.com.lucas.pagamentos.dto.conta.ContaEmpresaResDto;
import br.com.lucas.pagamentos.dto.conta.ContaFuncionarioDto;
import br.com.lucas.pagamentos.dto.conta.ContaFuncionarioResDto;
import br.com.lucas.pagamentos.model.conta.ContaEmpresa;
import br.com.lucas.pagamentos.model.conta.ContaFuncionario;
import br.com.lucas.pagamentos.model.empresa.Empresa;
import br.com.lucas.pagamentos.model.funcionario.Funcionario;
import br.com.lucas.pagamentos.repository.EmpresaRepository;
import br.com.lucas.pagamentos.repository.FuncionarioRepository;
import br.com.lucas.pagamentos.repository.conta.ContaEmpresaRepository;
import br.com.lucas.pagamentos.repository.conta.ContaFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaEmpresaRepository contaEmpresaRepository;

    @Autowired
    ContaFuncionarioRepository contaFuncionarioRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override
    public List<ContaEmpresaResDto> findAllContasEmpresas() {

        List<ContaEmpresa> contaEmpresaList = (List<ContaEmpresa>) contaEmpresaRepository.findAll();
        List<ContaEmpresaResDto> contaEmpresas = new ArrayList<>();

        if (contaEmpresaList.size() > 0) {
            contaEmpresaList.forEach(contaEmpresa ->
                    contaEmpresas.add(new ContaEmpresaResDto().fromContaEmpresa(contaEmpresa)));

            return contaEmpresas;
        }

        return contaEmpresas;
    }

    @Override
    public ContaEmpresaResDto findContaEmpresaById(Long id) {

        ContaEmpresa contaEmpresa = contaEmpresaRepository.findFirstById(id);

        if (contaEmpresa != null) {
            return new ContaEmpresaResDto().fromContaEmpresa(contaEmpresa);
        }

        return null;
    }

    @Override
    public ContaEmpresaResDto updateContaEmpresa(Long id, ContaEmpresaDto contaEmpresaDto) {

        ContaEmpresa currentContaEmpresa = contaEmpresaRepository.findFirstById(id);
        Empresa empresa = currentContaEmpresa.getEmpresa();

        if (empresa != null) {
            BigDecimal updateSaldoEmpresa = currentContaEmpresa.getSaldo().add(
                    new BigDecimal(contaEmpresaDto.getDeposito()));

            ContaEmpresa contaEmpresa = new ContaEmpresa()
                    .getFromDto(currentContaEmpresa.getId(), empresa, updateSaldoEmpresa);

            contaEmpresaRepository.save(contaEmpresa);

            return new ContaEmpresaResDto().fromContaEmpresa(contaEmpresa);
        }

        return null;
    }

    @Override
    public List<ContaFuncionarioResDto> findAllContasFuncionarios() {

        List<ContaFuncionario> contaFuncionarioList =
                (List<ContaFuncionario>) contaFuncionarioRepository.findAll();
        List<ContaFuncionarioResDto> contaFuncionarios = new ArrayList<>();

        if (contaFuncionarioList.size() > 0) {
            contaFuncionarioList.forEach(contaFuncionario ->
                    contaFuncionarios.add(new ContaFuncionarioResDto()
                            .fromContaFuncionario(contaFuncionario)));

            return contaFuncionarios;
        }

        return contaFuncionarios;
    }

    @Override
    public ContaFuncionarioResDto findContaFuncionarioById(Long id) {

        ContaFuncionario contaFuncionario = contaFuncionarioRepository.findFirstById(id);

        if (contaFuncionario != null) {
            return new ContaFuncionarioResDto().fromContaFuncionario(contaFuncionario);
        }

        return null;
    }

    @Override
    public ContaFuncionarioResDto updateContaFuncionario(
            Long id, ContaFuncionarioDto contaFuncionarioDto) {

        ContaFuncionario currentContaFuncionario = contaFuncionarioRepository.findFirstById(id);
        Empresa empresa = empresaRepository.findFirstById(contaFuncionarioDto.getEmpresaid());

        if (currentContaFuncionario != null && empresa != null) {

            Funcionario funcionario = currentContaFuncionario.getFuncionario();
            ContaEmpresa contaEmpresa = empresa.getContaEmpresa();

            BigDecimal pagamento = new BigDecimal(contaFuncionarioDto.getPagamento());
            BigDecimal tarifa = pagamento.multiply(new BigDecimal("0.0038"));

            if (contaEmpresa.getSaldo().compareTo(pagamento.add(tarifa)) > 0) {
                updateSaldoEmpresa(contaEmpresa, pagamento, tarifa, empresa);
                ContaFuncionario contaFuncionario =
                        updateSaldoFuncionario(currentContaFuncionario, pagamento, funcionario);

                return new ContaFuncionarioResDto().fromContaFuncionario(contaFuncionario);
            }
        }

        return null;
    }

    private void updateSaldoEmpresa(
            ContaEmpresa contaEmpresa, BigDecimal pagamento, BigDecimal tarifa, Empresa empresa) {

        BigDecimal saldoEmpresa = contaEmpresa.getSaldo().subtract(pagamento).subtract(tarifa);
        contaEmpresaRepository.save(new ContaEmpresa()
                .getFromPagamento(contaEmpresa.getId(), empresa, saldoEmpresa));

    }

    private ContaFuncionario updateSaldoFuncionario(
            ContaFuncionario contaFuncionario, BigDecimal pagamento, Funcionario funcionario) {

        BigDecimal saldoFuncionario = contaFuncionario.getSaldo().add(pagamento);

        return contaFuncionarioRepository.save(new ContaFuncionario()
                .getFromPagamento(contaFuncionario.getId(), funcionario, saldoFuncionario));

    }

}

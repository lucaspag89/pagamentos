package br.com.lucas.pagamentos.service.empresa;

import br.com.lucas.pagamentos.dto.empresa.EmpresaDto;
import br.com.lucas.pagamentos.dto.empresa.EmpresaResDto;
import br.com.lucas.pagamentos.model.conta.ContaEmpresa;
import br.com.lucas.pagamentos.model.empresa.Empresa;
import br.com.lucas.pagamentos.repository.EmpresaRepository;
import br.com.lucas.pagamentos.repository.conta.ContaEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    ContaEmpresaRepository contaEmpresaRepository;

    @Override
    public List<EmpresaResDto> findAllEmpresas() {

        List<Empresa> empresaList = (List<Empresa>) empresaRepository.findAll();
        List<EmpresaResDto> empresas = new ArrayList<>();

        if (empresaList.size() > 0) {
            empresaList.forEach(empresa ->
                    empresas.add(new EmpresaResDto().fromEmpresa(empresa)));

            return empresas;
        }

        return empresas;
    }

    @Override
    public EmpresaResDto findByEmpresaId(Long id) {

        Empresa empresa = empresaRepository.findFirstById(id);

        if (empresa != null) {
            return new EmpresaResDto().fromEmpresa(empresa);
        }

        return null;
    }

    @Override
    public EmpresaResDto registerEmpresa(EmpresaDto empresaDto) {

        Empresa empresa = new Empresa().getFromDto(empresaDto);

        empresaRepository.save(empresa);

        createContaEmpresa(empresa);

        return new EmpresaResDto().fromEmpresa(empresa);

    }

    private void createContaEmpresa(Empresa empresa) {

        contaEmpresaRepository.save(new ContaEmpresa().fromEmpresa(empresa));

    }

    @Override
    public EmpresaResDto updateEmpresa(Long id, EmpresaDto empresaDto) {
        Empresa currentEmpresa = empresaRepository.findFirstById(id);

        if (currentEmpresa != null) {
            Empresa empresa = new Empresa().getFromDto(empresaDto, currentEmpresa);
            empresaRepository.save(empresa);

            return new EmpresaResDto().fromEmpresa(empresa);
        }

        return null;
    }

    @Override
    public Boolean removeEmpresa(Long id) {
        Empresa empresa = empresaRepository.findFirstById(id);

        if (empresa != null) {
            try {
                empresaRepository.delete(empresa);

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

}

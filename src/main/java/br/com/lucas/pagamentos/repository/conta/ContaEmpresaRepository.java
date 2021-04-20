package br.com.lucas.pagamentos.repository.conta;

import br.com.lucas.pagamentos.model.conta.ContaEmpresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaEmpresaRepository extends CrudRepository<ContaEmpresa, Long> {

    ContaEmpresa findFirstById(Long id);

    ContaEmpresa findFirstByEmpresaId(Long id);

}

package br.com.lucas.pagamentos.repository.conta;

import br.com.lucas.pagamentos.model.conta.ContaFuncionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaFuncionarioRepository extends CrudRepository<ContaFuncionario, Long> {

    ContaFuncionario findFirstById(Long id);

    ContaFuncionario findFirstByFuncionarioId(Long id);

}

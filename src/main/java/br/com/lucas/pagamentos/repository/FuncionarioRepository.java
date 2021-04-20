package br.com.lucas.pagamentos.repository;

import br.com.lucas.pagamentos.model.funcionario.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

    Funcionario findFirstById(Long id);

}

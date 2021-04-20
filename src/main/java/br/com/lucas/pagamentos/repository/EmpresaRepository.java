package br.com.lucas.pagamentos.repository;

import br.com.lucas.pagamentos.model.empresa.Empresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Long> {

    Empresa findFirstById(Long id);

}

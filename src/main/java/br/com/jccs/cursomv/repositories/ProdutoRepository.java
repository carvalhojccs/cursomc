package br.com.jccs.cursomv.repositories;

import br.com.jccs.cursomv.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
    
}

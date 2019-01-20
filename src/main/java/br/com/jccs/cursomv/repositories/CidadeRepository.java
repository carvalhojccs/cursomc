package br.com.jccs.cursomv.repositories;

import br.com.jccs.cursomv.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
    
}

package br.com.jccs.cursomv.services;

import br.com.jccs.cursomv.domain.Categoria;
import br.com.jccs.cursomv.repositories.CategoriaRepository;
import br.com.jccs.cursomv.services.exceptions.ObjectNotFouondException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repo;
    
    public Categoria buscar(Integer id){
        Optional<Categoria> obj = repo.findById(id);
        
        /*
        Caso o objeto nao seja encontrado, lanca minha excecao personalizada ObjectNotFoundException
        */
        return obj.orElseThrow(
                () -> new ObjectNotFouondException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())
        );
    }
}

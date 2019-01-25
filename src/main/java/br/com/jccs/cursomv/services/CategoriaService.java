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
    
    public Categoria find(Integer id){
        Optional<Categoria> obj = repo.findById(id);
        
        /*
        Caso o objeto nao seja encontrado, lanca minha excecao personalizada ObjectNotFoundException
        */
        return obj.orElseThrow(
                () -> new ObjectNotFouondException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())
        );
    }
    
    public Categoria insert(Categoria obj){
        //verifica se o id é nulo. Se não for nulo, é uma atualização
        obj.setId(null);
        
        //persiste na tabela categoria o obj
        return repo.save(obj);
    }
    
    public Categoria update(Categoria obj){
        //realiza uma busca para verificar se o id existe antes da atualização
        find(obj.getId());
        return repo.save(obj);
        
    }
}

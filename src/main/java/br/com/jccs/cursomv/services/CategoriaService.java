package br.com.jccs.cursomv.services;

import br.com.jccs.cursomv.domain.Categoria;
import br.com.jccs.cursomv.domain.Cliente;
import br.com.jccs.cursomv.dto.CategoriaDTO;
import br.com.jccs.cursomv.repositories.CategoriaRepository;
import br.com.jccs.cursomv.services.exceptions.DataIntegrityException;
import br.com.jccs.cursomv.services.exceptions.ObjectNotFouondException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
        Categoria newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    
    public void delete(Integer id){
        //realiza uma busca para verificar se o id existe antes de deletar
        find(id);
        
        try{
            repo.deleteById(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
        }
    }
    
    public List<Categoria> findAll(){
        return repo.findAll();
    }
    
    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }
    
    public Categoria fromDTO(CategoriaDTO objDto){
        return new Categoria(objDto.getId(), objDto.getNome());
    }
    
    private void updateData(Categoria newObj, Categoria obj){
        newObj.setNome(obj.getNome());
    }
}

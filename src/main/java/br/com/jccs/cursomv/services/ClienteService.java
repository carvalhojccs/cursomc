package br.com.jccs.cursomv.services;

import br.com.jccs.cursomv.domain.Cliente;
import br.com.jccs.cursomv.domain.Cliente;
import br.com.jccs.cursomv.dto.ClienteDTO;
import br.com.jccs.cursomv.repositories.ClienteRepository;
import br.com.jccs.cursomv.services.exceptions.DataIntegrityException;
import br.com.jccs.cursomv.services.exceptions.ObjectNotFouondException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repo;
    
    public Cliente find(Integer id){
        Optional<Cliente> obj = repo.findById(id);
        
        /*
        Caso o objeto nao seja encontrado, lanca minha excecao personalizada ObjectNotFoundException
        */
        return obj.orElseThrow(
                () -> new ObjectNotFouondException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())
        );
    }
    
    public Cliente update(Cliente obj){
        Cliente newObj = find(obj.getId());
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
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas!");
        }
    }
    
    public List<Cliente> findAll(){
        return repo.findAll();
    }
    
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }
    
    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }
    
    private void updateData(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}

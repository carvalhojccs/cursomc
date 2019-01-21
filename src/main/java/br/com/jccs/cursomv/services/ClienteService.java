package br.com.jccs.cursomv.services;

import br.com.jccs.cursomv.domain.Cliente;
import br.com.jccs.cursomv.repositories.ClienteRepository;
import br.com.jccs.cursomv.services.exceptions.ObjectNotFouondException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repo;
    
    public Cliente buscar(Integer id){
        Optional<Cliente> obj = repo.findById(id);
        
        /*
        Caso o objeto nao seja encontrado, lanca minha excecao personalizada ObjectNotFoundException
        */
        return obj.orElseThrow(
                () -> new ObjectNotFouondException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())
        );
    }
}

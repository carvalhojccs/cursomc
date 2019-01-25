package br.com.jccs.cursomv.services;

import br.com.jccs.cursomv.domain.Pedido;
import br.com.jccs.cursomv.repositories.PedidoRepository;
import br.com.jccs.cursomv.services.exceptions.ObjectNotFouondException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository repo;
    
    public Pedido buscar(Integer id){
        Optional<Pedido> obj = repo.findById(id);
        
        /*
        Caso o objeto nao seja encontrado, lanca minha excecao personalizada ObjectNotFoundException
        */
        return obj.orElseThrow(
                () -> new ObjectNotFouondException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())
        );
    }
}

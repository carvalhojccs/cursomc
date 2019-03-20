package br.com.jccs.cursomv.services;

import br.com.jccs.cursomv.domain.Categoria;
import br.com.jccs.cursomv.domain.Produto;
import br.com.jccs.cursomv.repositories.CategoriaRepository;
import br.com.jccs.cursomv.repositories.ProdutoRepository;
import br.com.jccs.cursomv.services.exceptions.ObjectNotFouondException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository repo;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public Produto find(Integer id){
        Optional<Produto> obj = repo.findById(id);
        
        /*
        Caso o objeto nao seja encontrado, lanca minha excecao personalizada ObjectNotFoundException
        */
        return obj.orElseThrow(
                () -> new ObjectNotFouondException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName())
        );
    }
    
    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        
        return repo.search(nome, categorias, pageRequest);
        
        //usando padrão de nomes do spring na consulta JPQL
        //return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}

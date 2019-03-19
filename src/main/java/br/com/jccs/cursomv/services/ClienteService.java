package br.com.jccs.cursomv.services;

import br.com.jccs.cursomv.domain.Cidade;
import br.com.jccs.cursomv.domain.Cliente;
import br.com.jccs.cursomv.domain.Endereco;
import br.com.jccs.cursomv.domain.enums.TipoCliente;
import br.com.jccs.cursomv.dto.ClienteDTO;
import br.com.jccs.cursomv.dto.ClienteNewDTO;
import br.com.jccs.cursomv.repositories.CidadeRepository;
import br.com.jccs.cursomv.repositories.ClienteRepository;
import br.com.jccs.cursomv.repositories.EnderecoRepository;
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
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repo;
    
    @Autowired
    private CidadeRepository cidadeRepository;
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    public Cliente find(Integer id){
        Optional<Cliente> obj = repo.findById(id);
        
        /*
        Caso o objeto nao seja encontrado, lanca minha excecao personalizada ObjectNotFoundException
        */
        return obj.orElseThrow(
                () -> new ObjectNotFouondException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())
        );
    }
    
        public Cliente insert(Cliente obj){
        //verifica se o id é nulo. Se não for nulo, é uma atualização
        obj.setId(null);
        
        obj = repo.save(obj);
        
        enderecoRepository.saveAll(obj.getEnderecos());
        
        //persiste na tabela categoria o obj
        return obj;        
    }

    @Transactional
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
    
    public Cliente fromDTO(ClienteNewDTO objDto){
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
        Optional<Cidade> cid = cidadeRepository.findById(objDto.getCidadeId());
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid.get());
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if(objDto.getTelefone2()!= null){
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if(objDto.getTelefone3()!= null){
            cli.getTelefones().add(objDto.getTelefone3());
        }
        
        return cli;
        
    }
    
    private void updateData(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}

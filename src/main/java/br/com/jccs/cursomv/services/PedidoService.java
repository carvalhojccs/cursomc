package br.com.jccs.cursomv.services;

import br.com.jccs.cursomv.domain.ItemPedido;
import br.com.jccs.cursomv.domain.PagamentoComBoleto;
import br.com.jccs.cursomv.domain.Pedido;
import br.com.jccs.cursomv.domain.enums.EstadoPagamento;
import br.com.jccs.cursomv.repositories.ItemPedidoRepository;
import br.com.jccs.cursomv.repositories.PagamentoRepository;
import br.com.jccs.cursomv.repositories.PedidoRepository;
import br.com.jccs.cursomv.repositories.ProdutoRepository;
import br.com.jccs.cursomv.services.exceptions.ObjectNotFouondException;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository repo;
    
    @Autowired
    private BoletoService boletoService;
    
    @Autowired
    private PagamentoRepository pagamentoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    public Pedido find(Integer id){
        Optional<Pedido> obj = repo.findById(id);
        
        /*
        Caso o objeto nao seja encontrado, lanca minha excecao personalizada ObjectNotFoundException
        */
        return obj.orElseThrow(
                () -> new ObjectNotFouondException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())
        );
    }
    
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if(obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        
        for(ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        
        itemPedidoRepository.saveAll(obj.getItens());
        
        return obj;
        
    }
}

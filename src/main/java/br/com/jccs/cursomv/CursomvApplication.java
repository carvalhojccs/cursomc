package br.com.jccs.cursomv;

import br.com.jccs.cursomv.domain.Categoria;
import br.com.jccs.cursomv.domain.Cidade;
import br.com.jccs.cursomv.domain.Cliente;
import br.com.jccs.cursomv.domain.Endereco;
import br.com.jccs.cursomv.domain.Estado;
import br.com.jccs.cursomv.domain.Pagamento;
import br.com.jccs.cursomv.domain.PagamentoComBoleto;
import br.com.jccs.cursomv.domain.PagamentoComCartao;
import br.com.jccs.cursomv.domain.Pedido;
import br.com.jccs.cursomv.domain.Produto;
import br.com.jccs.cursomv.domain.enums.EstadoPagamento;
import br.com.jccs.cursomv.domain.enums.TipoCliente;
import br.com.jccs.cursomv.repositories.CategoriaRepository;
import br.com.jccs.cursomv.repositories.CidadeRepository;
import br.com.jccs.cursomv.repositories.ClienteRepository;
import br.com.jccs.cursomv.repositories.EnderecoRepository;
import br.com.jccs.cursomv.repositories.EstadoRepository;
import br.com.jccs.cursomv.repositories.PagamentoRepository;
import br.com.jccs.cursomv.repositories.PedidoRepository;
import br.com.jccs.cursomv.repositories.ProdutoRepository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomvApplication implements CommandLineRunner{

        @Autowired
        private CategoriaRepository categoriaRepository;
    
        @Autowired
        private ProdutoRepository produtoRepository;
        
        @Autowired
        private CidadeRepository cidadeRepository;
        
        @Autowired
        private EstadoRepository estadoRepository;
        
        @Autowired
        private ClienteRepository clienteRepository;
    
        @Autowired
        private EnderecoRepository enderecoRepository;
        
        @Autowired
        private PedidoRepository pedidoRepository;
        
        @Autowired
        private PagamentoRepository pagamentoRepository;
        
	public static void main(String[] args) {
		SpringApplication.run(CursomvApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.0);
        
        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));
        
        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));
        
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
        
        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");
        
        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);
        
        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2,c3));
        
        estadoRepository.saveAll(Arrays.asList(est1,est2));
        
        cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
        
        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
        
        //adicionar os telefones
        cli1.getTelefones().addAll(Arrays.asList("27362323", "93838393"));
        
        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "6690000", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "66090000", cli1, c2);
        
        cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
        
        clienteRepository.saveAll(Arrays.asList(cli1));
        
        enderecoRepository.saveAll(Arrays.asList(e1,e2));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm" );
        
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:30"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:45"), cli1, e2);
        
        Pagamento pgto1 = new PagamentoComCartao(null, 6, EstadoPagamento.QUITADO, ped1);
        ped1.setPagamento(pgto1);
        
        Pagamento pgto2 = new PagamentoComBoleto(sdf.parse("20/10/2017 00:00"), null, null, EstadoPagamento.PENDENTE, ped2);
        ped2.setPagamento(pgto2);
        
        cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
        
        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        
        pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
        
        
    }

}


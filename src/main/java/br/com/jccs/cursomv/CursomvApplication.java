package br.com.jccs.cursomv;

import br.com.jccs.cursomv.domain.Categoria;
import br.com.jccs.cursomv.domain.Cidade;
import br.com.jccs.cursomv.domain.Cliente;
import br.com.jccs.cursomv.domain.Endereco;
import br.com.jccs.cursomv.domain.Estado;
import br.com.jccs.cursomv.domain.ItemPedido;
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
import br.com.jccs.cursomv.repositories.ItemPedidoRepository;
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
        
        @Autowired
        private ItemPedidoRepository itemPedidoRepository;
        
	public static void main(String[] args) {
		SpringApplication.run(CursomvApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Cama mesa e banho");
        Categoria cat4 = new Categoria(null, "Eletrônico");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Produto p1  = new Produto(null, "Computador", 2000.00);
        Produto p2  = new Produto(null, "Impressora", 800.00);
        Produto p3  = new Produto(null, "Mouse", 80.0);
        Produto p4  = new Produto(null, "Mesa de escritório", 700.0);
        Produto p5  = new Produto(null, "Toalha", 20.0);
        Produto p6  = new Produto(null, "Colcha", 90.0);
        Produto p7  = new Produto(null, "TV true color", 1200.0);
        Produto p8  = new Produto(null, "Roçadeira", 500.0);
        Produto p9  = new Produto(null, "Abajur", 180.0);
        Produto p10 = new Produto(null, "Pendente", 100.0);
        Produto p11 = new Produto(null, "Shampoo", 10.0);
        
        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));
        
        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));
        
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
        
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
        
        
        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
        
        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        
        ped2.getItens().addAll(Arrays.asList(ip3));
        
        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));
        
        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }

}


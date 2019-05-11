package br.com.jccs.cursomv.domain;

import br.com.jccs.cursomv.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Date;
import javax.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento{
    private static final long serialVersionUID = 1L;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPagamento;

    //Construtores
    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Date dataVencimento, Date dataPagamento, Integer id, EstadoPagamento estado, Pedido pedido) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
    
    //GET/SET
    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}

package classes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pagamento {
    private int id;
    private int agendamentoId;
    private String metodo;
    private BigDecimal valor;
    private String status;
    private String codigoTransacao;
    private LocalDateTime dataPagamento;
    private String dadosPagamento;

    public Pagamento() {
        this.status = "pendente";
    }

    public Pagamento(int agendamentoId, String metodo, BigDecimal valor, String dadosPagamento) {
        this();
        this.agendamentoId = agendamentoId;
        this.metodo = metodo;
        this.valor = valor;
        this.dadosPagamento = dadosPagamento;
    }

}

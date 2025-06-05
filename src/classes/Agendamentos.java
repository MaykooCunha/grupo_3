package classes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Agendamento {
    private int id;
    private int clienteId;
    private Integer prestadorId;
    private int servicoId;
    private LocalDateTime dataAgendamento;
    private LocalDateTime dataConclusao;
    private String modeloEquipamento;
    private boolean primeiraManutencao;
    private String observacoes;
    private int enderecoId;
    private String status;
    private BigDecimal valor;
    private LocalDateTime dataCriacao;

    public Agendamento() {
        this.status = "pendente";
        this.dataCriacao = LocalDateTime.now();
    }

    public Agendamento(int clienteId, Integer prestadorId, int servicoId, LocalDateTime dataAgendamento,
                       String modeloEquipamento, boolean primeiraManutencao, String observacoes,
                       int enderecoId, BigDecimal valor) {
        this();
        this.clienteId = clienteId;
        this.prestadorId = prestadorId;
        this.servicoId = servicoId;
        this.dataAgendamento = dataAgendamento;
        this.modeloEquipamento = modeloEquipamento;
        this.primeiraManutencao = primeiraManutencao;
        this.observacoes = observacoes;
        this.enderecoId = enderecoId;
        this.valor = valor;
    }

    // Getters e Setters omitidos por brevidade
}

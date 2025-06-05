package classes;

import java.time.LocalDateTime;

public class EnderecoPrestador {
    private int id;
    private int prestadorId;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String tipo;
    private boolean principal;
    private LocalDateTime dataCadastro;
    private boolean ativo;

    public EnderecoPrestador() {
        this.dataCadastro = LocalDateTime.now();
        this.ativo = true;
    }

    public EnderecoPrestador(int prestadorId, String cep, String logradouro, String numero, String complemento,
                              String bairro, String cidade, String estado, String tipo, boolean principal) {
        this();
        this.prestadorId = prestadorId;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.tipo = tipo;
        this.principal = principal;
    }

}

package classes;

public class Servico {
    private int id;
    private String nome;
    private String descricao;
    private String categoria;
    private boolean ativo;

    public Servico() {
        this.ativo = true;
    }

    public Servico(String nome, String descricao, String categoria) {
        this();
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
    }

}

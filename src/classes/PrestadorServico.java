package classes;

import java.time.LocalDateTime;

public class PrestadorServico {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cnpj;
    private String telefone;
    private String especialidade;
    private LocalDateTime dataCadastro;
    private boolean ativo;

    public PrestadorServico() {
    }

    public PrestadorServico(int id, String nome, String email, String senha, String cnpj, String telefone, String especialidade, LocalDateTime dataCadastro, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.especialidade = especialidade;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
    }

    public PrestadorServico(String nome, String email, String senha, String cnpj, String telefone, String especialidade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.especialidade = especialidade;
        this.dataCadastro = LocalDateTime.now();
        this.ativo = true;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void desativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    public String resumo() {
        return "Prestador: " + nome + " | CNPJ: " + cnpj + " | Especialidade: " + especialidade;
    }
}

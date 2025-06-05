package src.data_access;

import src.interfaces.EntidadeInterface;
import java.sql.*;

public class BancoDeDados {
    private Connection conexao;
    private static BancoDeDados instancia;

    private BancoDeDados() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/refrigeradb";
        String usuario = "root";
        String senha = "senha123";
        
        this.conexao = DriverManager.getConnection(url, usuario, senha);
    }

    public static BancoDeDados conectar() throws SQLException {
        if (instancia == null) {
            instancia = new BancoDeDados();
        }
        return instancia;
    }

    public void inserir(EntidadeInterface entidade) throws SQLException {
        String sql = montarSqlInsert(entidade);
        executarSql(sql);
    }

    public void atualizar(EntidadeInterface entidade) throws SQLException {
        String sql = montarSqlUpdate(entidade);
        executarSql(sql);
    }

    public void deletar(EntidadeInterface entidade) throws SQLException {
        String sql = montarSqlDelete(entidade);
        executarSql(sql);
    }

    public ResultSet buscar(String sql) throws SQLException {
        Statement stmt = conexao.createStatement();
        return stmt.executeQuery(sql);
    }

    private String montarSqlInsert(EntidadeInterface entidade) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(entidade.getTableName()).append(" (");
        
        String[] colunas = entidade.getColumnNames();
        for (int i = 0; i < colunas.length; i++) {
            if (i > 0) sql.append(", ");
            sql.append(colunas[i]);
        }
        
        sql.append(") VALUES (");
        
        String[] valores = entidade.getFormatedValues();
        for (int i = 0; i < valores.length; i++) {
            if (i > 0) sql.append(", ");
            sql.append(valores[i]);
        }
        
        sql.append(")");
        return sql.toString();
    }

    private String montarSqlUpdate(EntidadeInterface entidade) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(entidade.getTableName()).append(" SET ");
        
        String[] colunas = entidade.getColumnNames();
        String[] valores = entidade.getFormatedValues();
        boolean primeiro = true;
        
        for (int i = 0; i < colunas.length; i++) {
            if (!ehChavePrimaria(entidade, colunas[i])) {
                if (!primeiro) sql.append(", ");
                sql.append(colunas[i]).append(" = ").append(valores[i]);
                primeiro = false;
            }
        }
        
        sql.append(" WHERE ");
        String[] chaves = entidade.getPrimaryKeyColumns();
        String[] valChaves = entidade.getPrimaryKeyValues();
        
        for (int i = 0; i < chaves.length; i++) {
            if (i > 0) sql.append(" AND ");
            sql.append(chaves[i]).append(" = ").append(valChaves[i]);
        }
        
        return sql.toString();
    }

    private String montarSqlDelete(EntidadeInterface entidade) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(entidade.getTableName()).append(" WHERE ");
        
        // Condição WHERE com chave primária
        String[] chaves = entidade.getPrimaryKeyColumns();
        String[] valChaves = entidade.getPrimaryKeyValues();
        
        for (int i = 0; i < chaves.length; i++) {
            if (i > 0) sql.append(" AND ");
            sql.append(chaves[i]).append(" = ").append(valChaves[i]);
        }
        
        return sql.toString();
    }

    private boolean ehChavePrimaria(EntidadeInterface entidade, String coluna) {
        for (String chave : entidade.getPrimaryKeyColumns()) {
            if (chave.equals(coluna)) {
                return true;
            }
        }
        return false;
    }

    private void executarSql(String sql) throws SQLException {
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void fechar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}

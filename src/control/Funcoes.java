import conexao.DAO;
import interfaces.EntidadeInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Funcoes {

    public static void cadastrar(EntidadeInterface entidade) {
        try {
            DAO.getInstance().inserir(entidade);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public static void alterar(EntidadeInterface entidade) {
        try {
            DAO.getInstance().alterar(entidade);
        } catch (SQLException e) {
            System.out.println("Erro ao alterar: " + e.getMessage());
        }
    }

    public static void excluir(Class<? extends EntidadeInterface> classe, int id) {
        try {
            DAO.getInstance().excluir(classe, id);
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }

    public static List<EntidadeInterface> listar(Class<? extends EntidadeInterface> classe) {
        try {
            return DAO.getInstance().listar(classe);
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static EntidadeInterface buscarPorId(Class<? extends EntidadeInterface> classe, int id) {
        try {
            return DAO.getInstance().buscarPorId(classe, id);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar por ID: " + e.getMessage());
            return null;
        }
    }

    public static List<EntidadeInterface> buscarPorCampo(Class<? extends EntidadeInterface> classe, String campo, Object valor) {
        try {
            return DAO.getInstance().buscarPorCampo(classe, campo, valor);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar por campo: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static int contar(Class<? extends EntidadeInterface> classe) {
        try {
            return DAO.getInstance().contar(classe);
        } catch (SQLException e) {
            System.out.println("Erro ao contar: " + e.getMessage());
            return 0;
        }
    }

    public static List<Map<String, Object>> executarConsulta(String sql) {
        List<Map<String, Object>> resultado = new ArrayList<>();
        try (ResultSet rs = DAO.getInstance().executarConsulta(sql)) {
            int colunas = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Map<String, Object> linha = new HashMap<>();
                for (int i = 1; i <= colunas; i++) {
                    linha.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                resultado.add(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }
        return resultado;
    }

    public static Map<String, Object> executarConsultaUnica(String sql) {
        try (ResultSet rs = DAO.getInstance().executarConsulta(sql)) {
            if (rs.next()) {
                Map<String, Object> linha = new HashMap<>();
                int colunas = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= colunas; i++) {
                    linha.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                return linha;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta única: " + e.getMessage());
        }
        return null;
    }
}


}
public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("ERRO NA CONEXÃO:");
            throw new RuntimeException("Falha na conexão", e);
        }
    }
}

import java.sql.Connection;
import java.util.List;
import dao.*;
import database.ConexaoMySQL;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conexao = ConexaoMySQL.getInstance().getConexao();

            UsuarioClienteDAO usuarioDAO = new UsuarioClienteDAO(conexao);
            EnderecoClienteDAO enderecoDAO = new EnderecoClienteDAO(conexao);
            PrestadorServicoDAO prestadorDAO = new PrestadorServicoDAO(conexao);
            ServicoSolicitadoDAO servicoDAO = new ServicoSolicitadoDAO(conexao);
            OrcamentoDAO orcamentoDAO = new OrcamentoDAO(conexao);

            UsuarioCliente cliente1 = new UsuarioCliente(0, "Julia Souza", "julia@email.com", "senha123");
            UsuarioCliente cliente2 = new UsuarioCliente(0, "Marcos Lima", "marcos@email.com", "1234");
            usuarioDAO.inserir(cliente1);
            usuarioDAO.inserir(cliente2);

            cliente1 = usuarioDAO.buscarPorEmail("julia@email.com");
            cliente2 = usuarioDAO.buscarPorEmail("marcos@email.com");

            EnderecoCliente end1 = new EnderecoCliente(0, cliente1.getIdUsuario(), "Rua das Flores", "123", "Centro", "São Paulo", "SP");
            EnderecoCliente end2 = new EnderecoCliente(0, cliente2.getIdUsuario(), "Av. Brasil", "987", "Jardins", "Campinas", "SP");
            enderecoDAO.inserir(end1);
            enderecoDAO.inserir(end2);

            
            PrestadorServico prestador1 = new PrestadorServico(0, "Carlos Técnico", "carlos@refrisolucoes.com", "11999999999", "Reparo em geladeiras");
            PrestadorServico prestador2 = new PrestadorServico(0, "Ana Climatiza", "ana@climatec.com", "11988888888", "Instalação de ar-condicionado");
            prestadorDAO.inserir(prestador1);
            prestadorDAO.inserir(prestador2);

            prestador1 = prestadorDAO.buscarPorEmail("carlos@refrisolucoes.com");
            prestador2 = prestadorDAO.buscarPorEmail("ana@climatec.com");

            ServicoSolicitado servico1 = new ServicoSolicitado(0, cliente1.getIdUsuario(), "Geladeira Brastemp com defeito", "Geladeira", "Urgente");
            ServicoSolicitado servico2 = new ServicoSolicitado(0, cliente2.getIdUsuario(), "Instalar ar-condicionado Split", "Ar-condicionado", "Agendar visita");
            servicoDAO.inserir(servico1);
            servicoDAO.inserir(servico2);

            
            servico1 = servicoDAO.buscarTodos().get(0);
            servico2 = servicoDAO.buscarTodos().get(1);

            Orcamento orc1 = new Orcamento(0, servico1.getIdServico(), prestador1.getIdPrestador(), 220.0f, "Troca do motor");
            Orcamento orc2 = new Orcamento(0, servico1.getIdServico(), prestador2.getIdPrestador(), 300.0f, "Manutenção geral");
            Orcamento orc3 = new Orcamento(0, servico2.getIdServico(), prestador2.getIdPrestador(), 180.0f, "Instalação com material incluso");

            orcamentoDAO.inserir(orc1);
            orcamentoDAO.inserir(orc2);
            orcamentoDAO.inserir(orc3);

            System.out.println("Clientes cadastrados:");
            for (UsuarioCliente c : usuarioDAO.buscarTodos()) {
                System.out.println("- " + c.getNome() + " | " + c.getEmail());
            }

            System.out.println("\nPrestadores de serviço:");
            for (PrestadorServico p : prestadorDAO.buscarTodos()) {
                System.out.println("- " + p.getNome() + " | " + p.getEspecialidade());
            }

            System.out.println("\nServiços solicitados:");
            for (ServicoSolicitado s : servicoDAO.buscarTodos()) {
                System.out.println("- " + s.getTipoEquipamento() + ": " + s.getDescricaoProblema());
            }

            System.out.println("\nOrçamentos para cada serviço:");
            for (ServicoSolicitado s : servicoDAO.buscarTodos()) {
                List<Orcamento> orcs = orcamentoDAO.buscarPorIdServico(s.getIdServico());
                System.out.println("Serviço ID " + s.getIdServico() + ":");
                for (Orcamento o : orcs) {
                    System.out.println("  > Prestador ID " + o.getIdPrestador() + " propôs R$ " + o.getValor() + " - " + o.getDescricao());
                }
            }

            System.out.println("\nCliente " + cliente1.getNome() + " aceitou o orçamento de R$ " + orc1.getValor() + " proposto por " + prestador1.getNome());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

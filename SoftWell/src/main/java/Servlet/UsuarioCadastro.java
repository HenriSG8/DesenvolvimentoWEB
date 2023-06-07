package Servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UsuarioCadastro")
public class UsuarioCadastro extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String sexo = request.getParameter("sexo");
		String email = request.getParameter("emailCadastro");
		String celular = request.getParameter("celular");
		String senha = request.getParameter("Cadastrorsenha");

		if (verificarEmailRepetido(email)) {
			request.setAttribute("mensagem", "O email já está cadastrado.");
			request.getRequestDispatcher("Paginainicial.jsp").forward(request, response);
			return;
		}

		Connection conexao = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/prova_jr", "postgres", "postgresql");

			String sql = "INSERT INTO prv_pessoa (pes_nome, pes_sexo, pes_email, pes_celular, pes_senha) VALUES (?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, sexo);
			stmt.setString(3, email);
			stmt.setString(4, celular);
			stmt.setString(5, senha);

			int linhasAfetadas = stmt.executeUpdate();
			request.setAttribute("mensagem", "Cadastro realizado com sucesso.");
			request.getRequestDispatcher("Sucesso.jsp").forward(request, response);
			System.out.println("<p>" + linhasAfetadas + " linha(s) inserida(s) com sucesso!</p>");

		} catch (ClassNotFoundException e) {
			request.setAttribute("mensagem", "Erro ao carregar o driver JDBC.");
			request.getRequestDispatcher("Sucesso.jsp").forward(request, response);
			System.out.println("<p>Erro ao carregar o driver JDBC: " + e.getMessage() + "</p>");

		} catch (SQLException e) {
			request.setAttribute("mensagem", "Erro ao inserir os dados.");
			request.getRequestDispatcher("Sucesso.jsp").forward(request, response);
			System.out.println("<p>Erro ao inserir os dados: " + e.getMessage() + "</p>");
		} finally {
			fecharConexao(conexao, stmt, null);
		}
	}

	private boolean verificarEmailRepetido(String email) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = obterConexao();

			String sql = "SELECT COUNT(*) FROM prv_pessoa WHERE pes_email = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao(conn, stmt, rs);
		}

		return false;
	}

	private Connection obterConexao() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/prova_jr", "postgres", "8745623");
	}

	private void fecharConexao(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

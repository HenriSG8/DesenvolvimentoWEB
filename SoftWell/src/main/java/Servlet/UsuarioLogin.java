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

@WebServlet("/UsuarioLogin")
public class UsuarioLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		if (realizarLogin(email, senha)) {
			request.setAttribute("mensagem", "Login bem-sucedido!");
			request.getRequestDispatcher("Sucesso.jsp").forward(request, response);
			return;
		} else {
			request.setAttribute("mensagem", "Credenciais inválidas");
			request.getRequestDispatcher("Sucesso.jsp").forward(request, response);
		}
	}

	private boolean realizarLogin(String email, String senha) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = obterConexao();

			String sql = "SELECT COUNT(*) FROM prv_pessoa WHERE pes_email = ? AND pes_senha = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, senha);
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

		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/prova_jr", "postgres",
				"postgresql");
	}

	private void fecharConexao(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

package br.com.courseracourse.forum.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.courseracourse.forum.exception.UsuarioNaoAutenticadoException;

public class AutenticadorJDBC implements Autenticador {

	private static final String CONNECTION_STRING = "jdbc:h2:tcp://localhost/~/coursera";
	
	private static String SQL = "select nome from usuario where login = ? and senha = ?";

	static {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(Autenticador.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	@Override
	public String autenticar(String login, String senha) throws UsuarioNaoAutenticadoException {

		try(Connection c = DriverManager.getConnection(CONNECTION_STRING, "sa", "")) {
			
			PreparedStatement stmt = c.prepareStatement(SQL);
			stmt.setString(1, login);
			stmt.setString(2, senha);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("nome");
			}

			throw new UsuarioNaoAutenticadoException("Não foi possível autenticar o usuário!");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UsuarioNaoAutenticadoException("Não foi possível autenticar o usuário!", e);
		}
	}

}

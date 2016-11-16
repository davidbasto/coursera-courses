package br.com.courseracourse.manipuladorbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOJDBC implements UsuarioDAO {

	private static final String CONNECTION_STRING = "jdbc:h2:tcp://localhost/~/usuarios";
	
	private static final String USER = "sa";

	private static final String PASSWORD = "";
	
	private static final String INSERT_USUARIO_SQL = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
	
	private static final String SELECT_USUARIO_POR_LOGIN_SQL = "SELECT * FROM usuario WHERE login = ?";
	
	private static final String UPDATE_SOMA_PONTOS_SQL = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?";

	private static final String SELECT_USUARIO_ORDENADO_PONTOS_SQL = "SELECT * FROM usuario ORDER BY pontos DESC;";
	
	static {

		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void inserir(Usuario u) {

		try (Connection con = getConnection()){
			
			PreparedStatement stmt = con.prepareStatement(INSERT_USUARIO_SQL);
			
			stmt.setString(1, u.getLogin());
			stmt.setString(2, u.getEmail());
			stmt.setString(3, u.getNome());
			stmt.setString(4, u.getSenha());
			stmt.setInt(5, u.getPontos());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir dados no Banco de Dados", e);
		}
	}

	public Usuario recuperar(String login) {

		try (Connection con = getConnection()){
			
			PreparedStatement stmt = con.prepareStatement(SELECT_USUARIO_POR_LOGIN_SQL);
			
			stmt.setString(1, login);
			
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				throw new RuntimeException("Não foi encontrado nenhum usuário com este login");
			}
			
			Usuario usuario = new Usuario(rs.getString("login"), rs.getString("email"), rs.getString("nome"), rs.getString("senha"), rs.getInt("pontos"));
			
			return usuario;
			
		}catch (SQLException e) {
			throw new RuntimeException("Erro ao executar consulta no Banco de Dados", e);
		}
	}

	public void adicionarPontos(String login, int pontos) {

		try (Connection con = getConnection()){
			
			PreparedStatement stmt = con.prepareStatement(UPDATE_SOMA_PONTOS_SQL);
			
			stmt.setInt(1, pontos);
			stmt.setString(2, login);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao Atualizar quantidade de pontos de usuário no Banco de Dados", e);
		}
	}

	public List<Usuario> ranking() {
		
		List<Usuario> rankingUsuarios = new ArrayList<>();
		
		try (Connection con = getConnection()){
			
			PreparedStatement stmt = con.prepareStatement(SELECT_USUARIO_ORDENADO_PONTOS_SQL);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				rankingUsuarios.add(new Usuario(rs.getString("login"), rs.getString("email"), rs.getString("nome"),
						rs.getString("senha"), rs.getInt("pontos")));
			}
			
		}catch (SQLException e) {
			throw new RuntimeException("Erro ao executar consulta no Banco de Dados", e);
		}
		
		return rankingUsuarios;
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
	}
}

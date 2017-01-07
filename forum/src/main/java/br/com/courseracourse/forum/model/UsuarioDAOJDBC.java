package br.com.courseracourse.forum.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.courseracourse.forum.factory.ConnectionFactory;

public class UsuarioDAOJDBC implements UsuarioDAO {
	
	private static final String INSERT_USUARIO_SQL = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";

	private static final String SELECT_USUARIO_POR_LOGIN_SQL = "SELECT * FROM usuario WHERE login = ?";

	private static final String SELECT_USUARIOS_ORDENADOS_PONTOS = "SELECT * FROM usuario ORDER BY PONTOS DESC";
	
	private static final String UPDATE_PONTOS_USUARIO = "UPDATE usuario SET PONTOS = ? WHERE login = ?";
	
	private ConnectionFactory connectionFactory;
	
	public UsuarioDAOJDBC(ConnectionFactory connectionFactory) {
		super();
		this.connectionFactory = connectionFactory;
	}

	@Override
	public void inserirUsuario(Usuario usuario) {

		try (Connection con = connectionFactory.getConnection()){
			
			PreparedStatement stmt = con.prepareStatement(INSERT_USUARIO_SQL);
			
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getNome());
			stmt.setString(4, usuario.getSenha());
			stmt.setInt(5, usuario.getPontos());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir dados no Banco de Dados", e);
		}
	}

	@Override
	public Usuario recuperaUsuarioPorLogin(String login) {

		try (Connection con = connectionFactory.getConnection()){
			
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

	@Override
	public void atualizaPontuacaoUsuario(Usuario usuario) {

		try (Connection con = connectionFactory.getConnection()){

			PreparedStatement stmt = con.prepareStatement(UPDATE_PONTOS_USUARIO);
			
			stmt.setInt(1, usuario.getPontos());
			stmt.setString(2, usuario.getLogin());
			
			stmt.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("Erro ao executar consulta no Banco de Dados", e);
		}
	}

	@Override
	public List<Usuario> recuperaRankingUsuarios() {

		List<Usuario> rankingUsuarios = new ArrayList<>();
		try (Connection con = connectionFactory.getConnection()){
			
			PreparedStatement stmt;
			
			stmt = con.prepareStatement(SELECT_USUARIOS_ORDENADOS_PONTOS);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				rankingUsuarios.add(new Usuario(rs.getString("login"), rs.getString("email"), rs.getString("nome"), null, rs.getInt("pontos")));
			}
			
			return rankingUsuarios;
			
		}catch (SQLException e) {
			throw new RuntimeException("Erro ao executar consulta no Banco de Dados", e);
		}
	}

}

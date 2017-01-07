package br.com.courseracourse.forum.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.courseracourse.forum.factory.ConnectionFactory;

public class TopicoDAOJDBC implements TopicoDAO {

	private static final String SELECT_TOPICOS_POR_LOGIN_SQL = "SELECT * FROM topico WHERE login = ?";
	private static final String SELECT_TOPICOS = "SELECT * FROM topico order by id_topico";
	private static final String SELECT_TOPICOS_POR_ID = "SELECT * FROM topico WHERE id_topico = ?";
	private static final String SELECT_COMENTARIOS_POR_TOPICO = "SELECT * FROM COMENTARIO WHERE ID_TOPICO = ? order by ID_COMENTARIO";
	private static final String INSERT_TOPICO = "INSERT INTO TOPICO(TITULO, CONTEUDO, LOGIN) VALUES (?, ?, ?)";
	private static final String INSERT_COMENTARIO_TOPICO = "INSERT INTO COMENTARIO(COMENTARIO, LOGIN, ID_TOPICO) VALUES (?, ?, ?)";
	
	private ConnectionFactory connectionFactory;
	
	private UsuarioDAO usuarioDAO; 
	
	public TopicoDAOJDBC(ConnectionFactory connectionFactory) {
		super();
		this.connectionFactory = connectionFactory;
		this.usuarioDAO = new UsuarioDAOJDBC(connectionFactory);
	}

	@Override
	public List<Topico> recuperaTopicosPorUsuario(Usuario usuario) {

		List<Topico> listaTopicosUsuario = new ArrayList<>();
		try (Connection con = connectionFactory.getConnection()){
			
			PreparedStatement stmt;
			
			if (usuario != null) {
				stmt = con.prepareStatement(SELECT_TOPICOS_POR_LOGIN_SQL);
				stmt.setString(1, usuario.getLogin());
			}
			else {
				stmt = con.prepareStatement(SELECT_TOPICOS);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Usuario usuarioTopico = usuarioDAO.recuperaUsuarioPorLogin(rs.getString("login"));
				listaTopicosUsuario.add(new Topico(usuarioTopico, rs.getInt("id_topico"), rs.getString("titulo"), rs.getString("conteudo")));
			}
			
			return listaTopicosUsuario;
			
		}catch (SQLException e) {
			throw new RuntimeException("Erro ao executar consulta no Banco de Dados", e);
		}
	}

	@Override
	public Topico recuperaTopicoCompletoPorId(Integer idTopico) {

		try (Connection con = connectionFactory.getConnection()){
			
			PreparedStatement stmt = con.prepareStatement(SELECT_TOPICOS_POR_ID);
			
			stmt.setInt(1, idTopico);
			
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				throw new RuntimeException("Não foi encontrado nenhum tópico com este id");
			}
			
			String loginUsuario = rs.getString("login");
			Usuario usuarioTopico = usuarioDAO.recuperaUsuarioPorLogin(loginUsuario);
			Topico topico = new Topico(usuarioTopico, rs.getInt("id_topico"), rs.getString("titulo"), rs.getString("conteudo"));
			
			rs.close();
			stmt.close();
			
			stmt = con.prepareStatement(SELECT_COMENTARIOS_POR_TOPICO);
			stmt.setInt(1, idTopico);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Usuario usuarioComentario = usuarioDAO.recuperaUsuarioPorLogin(rs.getString("login"));
				topico.adicionaComentario(new Comentario(rs.getInt("ID_COMENTARIO"), topico, usuarioComentario, rs.getString("COMENTARIO")));
			}
			
			return topico;
			
		}catch (SQLException e) {
			throw new RuntimeException("Erro ao executar consulta no Banco de Dados", e);
		}
	}

	@Override
	public void persisteComentariosTopico(Topico topico) {
		try (Connection con = connectionFactory.getConnection()) {
			
			topico.getComentarios().stream().filter(c -> c.getId() == null || c.getId() == 0).forEach(c -> {
				try {
					PreparedStatement stmt = con.prepareStatement(INSERT_COMENTARIO_TOPICO);
					stmt.setString(1, c.getTextoComentario());
					stmt.setString(2, c.getUsuario().getLogin());
					stmt.setInt(3, topico.getId());
					
					stmt.executeUpdate();
					stmt.close();
				} catch (SQLException e) {

					throw new RuntimeException("Erro ao inserir comentários", e);
				}
			});
		}catch (SQLException e) {
			throw new RuntimeException("Erro ao se conectar no banco de dados", e);
		}
	}
	
	@Override
	public void insereNovoTopico(Usuario usuario, String tituloNovoTopico, String conteudoTopico) {
		try (Connection con = connectionFactory.getConnection()) {
	
			PreparedStatement stmt = con.prepareStatement(INSERT_TOPICO);
			stmt.setString(1, tituloNovoTopico);
			stmt.setString(2, conteudoTopico);
			stmt.setString(3, usuario.getLogin());
			
			stmt.executeUpdate();
			stmt.close();
			
		}catch (SQLException e) {
			throw new RuntimeException("Erro ao se conectar no banco de dados", e);
		}		
	}

	@Override
	public List<Topico> recuperaTodosTopicos() {
		return recuperaTopicosPorUsuario(null);
	}


}

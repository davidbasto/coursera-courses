package br.com.courseracourse.forum.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.courseracourse.forum.model.GerenciadorUsuario;
import br.com.courseracourse.forum.model.Usuario;

/**
 * Servlet implementation class RankingServlet
 */
public class RankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GerenciadorUsuario gerenciadorUsuario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingServlet() {
        super();
       	this.gerenciadorUsuario = new GerenciadorUsuario();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> ranking = this.gerenciadorUsuario.recuperaRankingUsuarios();
		
		request.setAttribute("ranking", ranking);
		request.getRequestDispatcher("ranking.jsp").forward(request, response);
	}

}

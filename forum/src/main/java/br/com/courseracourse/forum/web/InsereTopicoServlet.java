package br.com.courseracourse.forum.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.courseracourse.forum.model.GerenciadorTopico;

/**
 * Servlet implementation class InsereTopicoServlet
 */
public class InsereTopicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GerenciadorTopico gerenciadorTopico;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsereTopicoServlet() {
        super();
        this.gerenciadorTopico = new GerenciadorTopico();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loginUsuarioLogado = (String) request.getSession().getAttribute("usuarioLogado");
		
		if(loginUsuarioLogado != null ) {

			String tituloTopico = request.getParameter("titulo");
			String conteudoTopico = request.getParameter("conteudo");
			
			gerenciadorTopico.insereTopico(loginUsuarioLogado, tituloTopico, conteudoTopico);
			
			request.setAttribute("tituloTopicoInserido", tituloTopico);
			
			request.getRequestDispatcher("TopicoServlet").forward(request, response);
			
		}
	}

}

package br.com.courseracourse.forum.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.courseracourse.forum.model.GerenciadorTopico;
import br.com.courseracourse.forum.model.Topico;

/**
 * Servlet implementation class ExibeTopico
 */
public class ExibeTopicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GerenciadorTopico gerenciadorTopico;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExibeTopicoServlet() {
        super();
        this.gerenciadorTopico = new GerenciadorTopico();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		recuperaTopicoERedireciona(request, response);
		
	}

	private void recuperaTopicoERedireciona(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idTopico = request.getParameter("idTopico");
		
		if(idTopico == null) {
			throw new ServletException("Não foi especificado um id de tópico para ser exibido!");
		}
		
		Topico topico = gerenciadorTopico.recuperaTopicoPorId(Integer.parseInt(idTopico));
		request.setAttribute("topico", topico);
		request.getRequestDispatcher("exibeTopico.jsp").forward(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idTopico = request.getParameter("idTopico");
		
		String loginUsuarioLogado = (String) request.getSession().getAttribute("usuarioLogado");
		String novoComentParam = request.getParameter("novoComentario");
		
		if(loginUsuarioLogado != null && novoComentParam != null && novoComentParam.equals("SIM")) {

			String conteudoComentario = request.getParameter("textoComentario");
			
			gerenciadorTopico.insereNovoComentario(Integer.valueOf(idTopico), loginUsuarioLogado, conteudoComentario);
			
			recuperaTopicoERedireciona(request, response);
			
		}
	}
}

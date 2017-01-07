package br.com.courseracourse.forum.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.courseracourse.forum.model.GerenciadorUsuario;

/**
 * Servlet implementation class CadastroServlet
 */
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GerenciadorUsuario gerenciadorUsuario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroServlet() {
        super();
        gerenciadorUsuario = new GerenciadorUsuario();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		
		gerenciadorUsuario.cadastraUsuario(login, nome, email, senha);
		request.setAttribute("sucesso", "Cadastro realizado com sucesso");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}

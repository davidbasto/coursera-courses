package br.com.courseracourse.tradutor.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.courseracourse.tradutor.DicionarioTraducoes;
import br.com.courseracourse.tradutor.exception.TraducaoNaoEncontradaException;

/**
 * Servlet implementation class TraduzirPalavraServlet
 */

@WebServlet("/traduzirPalavra")
public class TraduzirPalavraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraduzirPalavraServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String palavra = request.getParameter("palavra");
		
		String palavraTraduzida = "";
		
		if(palavra != null) {
		DicionarioTraducoes dicionario = (DicionarioTraducoes) getServletContext().getAttribute("dicionario");
			
			try{
				palavraTraduzida = dicionario.recuperaTraducao(palavra);
			}
			catch (TraducaoNaoEncontradaException e) {
				request.getRequestDispatcher("erroTraducao.jsp").forward(request, response);
				
				return;
			}
			request.setAttribute("palavraTraduzida", palavraTraduzida);
			request.getRequestDispatcher("traducao.jsp").forward(request, response);
		}
	}

}

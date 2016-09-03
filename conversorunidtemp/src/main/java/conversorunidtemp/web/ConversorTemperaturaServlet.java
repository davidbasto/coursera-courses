package conversorunidtemp.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conversorunidtemp.business.Conversor;
import conversorunidtemp.business.ConversorFactory;
import conversorunidtemp.business.TipoConversao;

/**
 * Servlet implementation class ConversorTemperaturaServlet
 */
@WebServlet("/converterTemperatura")
public class ConversorTemperaturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ConversorFactory conversorFactory;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConversorTemperaturaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	conversorFactory = new ConversorFactory();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Double parametro = Double.parseDouble(request.getParameter("parametro"));
		
		String tipoConversao = request.getParameter("tipoConversao");
		
		response.setContentType("text/html;charset=UTF-8");
		
		Conversor conversor = conversorFactory.getConversor(TipoConversao.valueOf(tipoConversao));
		
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");

			out.println("<html>");
			out.println("<head>");
			out.println("	<title>Restultado da Conversão</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("	<h1>" + conversor.descreveConversao(parametro) + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

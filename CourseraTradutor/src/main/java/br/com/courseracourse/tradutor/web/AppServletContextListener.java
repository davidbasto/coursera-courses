package br.com.courseracourse.tradutor.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.courseracourse.tradutor.CarregaDicionarioComArquivo;
import br.com.courseracourse.tradutor.DicionarioTraducoes;

@WebListener
public class AppServletContextListener implements ServletContextListener {

	private static final String FILE_NAME = "/WEB-INF/classes/ArquivoVerbetesTraducoes.txt";
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//nothing
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		String realPath = servletContextEvent.getServletContext().getRealPath(FILE_NAME);
		System.out.println(realPath);
		DicionarioTraducoes dicionario = new CarregaDicionarioComArquivo(realPath).geraDicionarioAtravesDeArquivo();
		
		servletContextEvent.getServletContext().setAttribute("dicionario", dicionario);
	}
}

package br.com.courseracourse.forum.model;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

public class DBUnitController {

	private static final String DRIVER_CLASS_NAME = "org.h2.Driver";

	private static final String JDBC_URL_CONNECTION = "jdbc:h2:tcp://localhost/~/coursera";

	private static final String JDBC_USER = "sa";
	
	private static final String JDBC_PASSWORD = "";

	private JdbcDatabaseTester jdt;
	
	public DBUnitController(String caminhoArquivoDados) throws Exception {
		jdt = new JdbcDatabaseTester(DRIVER_CLASS_NAME, JDBC_URL_CONNECTION, JDBC_USER, JDBC_PASSWORD);
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load(caminhoArquivoDados));
		jdt.onSetup();
	}
}

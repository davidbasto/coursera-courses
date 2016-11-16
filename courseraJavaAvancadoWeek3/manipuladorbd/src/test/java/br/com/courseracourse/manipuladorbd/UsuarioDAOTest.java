package br.com.courseracourse.manipuladorbd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioDAOTest {

	private JdbcDatabaseTester jdt;
	
	private UsuarioDAO dao;
	
	@Before
	public void setUp() throws Exception {
		
		dao = new UsuarioDAOJDBC();
		
		jdt = new JdbcDatabaseTester("org.h2.Driver", "jdbc:h2:tcp://localhost/~/usuarios", "sa", "");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}
	
	@Test
	public void testInsertUsuario() throws SQLException, Exception {
		Usuario usuario = new Usuario("davidbasto", "davidbasto@gmail.com", "David Basto Neto", "senha_david", 104);
		
		dao.inserir(usuario);
		
		assertTabelasBancoIgualAoArquivoXML("/usuarios_tabela_apos_insercao.xml", "USUARIO");
	}
	
	@Test
	public void testaConsultaBancoDados() throws SQLException, Exception {
		Usuario expectedUsuario = new Usuario("joaojose", "joaojose@gmail.com", "Joao Jose", "123456", 150);
		
		Usuario usuarioRecuperado = dao.recuperar(expectedUsuario.getLogin());
		
		Assert.assertEquals(expectedUsuario, usuarioRecuperado);
		
		assertTabelasBancoIgualAoArquivoXML("/inicio.xml", "USUARIO");
	}
	
	@Test(expected=RuntimeException.class)
	public void testaConsultaNaoBancoDadosNaoEncontrou() {
		dao.recuperar("login_que_nao_existe");
	}
	
	@Test
	public void testaAdicionaPontos() throws SQLException, Exception {

		Integer pontosSomados = 30;
		Integer totalPontos = 150 + pontosSomados;
		
		Usuario expectedUsuario = new Usuario("joaojose", "joaojose@gmail.com", "Joao Jose", "123456", totalPontos);
		
		dao.adicionarPontos(expectedUsuario.getLogin(), pontosSomados);

		assertTabelasBancoIgualAoArquivoXML("/usuarios_tabela_apos_somar_pontos_joaojose.xml", "USUARIO");
	}
	
	@Test
	public void testaRankingUsuariosPorPontos() throws SQLException, Exception {

		List<Usuario> expetedRanking = new ArrayList<>();
		Collections.addAll(expetedRanking, 
				new Usuario("cintiatchuca", "cintiatchuca@gmail.com", "Cintia Tchuca", "123", 100),
				new Usuario("maria", "mt@gmail.com", "Maria Tereza", "567", 200),
				new Usuario("joaojose", "joaojose@gmail.com", "Joao Jose", "123456", 150),
				new Usuario("pedropaulo", "pedropaulo@gmail.com", "Pedro Paulo", "67890", 140)
			);
		
		//Ordenando a lista de resultado esperado de acordo com o critério de pontos.
		expetedRanking.sort((o1, o2) -> o1.getPontos().compareTo(o2.getPontos()) * -1);
		
		List<Usuario> currentRanking = dao.ranking();
		
		Assert.assertEquals(expetedRanking, currentRanking);
	}

	private void assertTabelasBancoIgualAoArquivoXML(String expectedTableFileName, String nomeTabelaComparacao)
			throws SQLException, Exception {

		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentUsuarioTable = currentDataSet.getTable(nomeTabelaComparacao);
		
		DataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet dataSet = loader.load(expectedTableFileName);
		
		ITable expectedUsuarioTable = dataSet.getTable(nomeTabelaComparacao);
		Column[] expectedColumns = currentUsuarioTable.getTableMetaData().getColumns();
		
		currentUsuarioTable = new SortedTable(currentUsuarioTable, expectedColumns);
		expectedUsuarioTable = new SortedTable(expectedUsuarioTable, expectedColumns);
		
		Assertion.assertEquals(expectedUsuarioTable, currentUsuarioTable);
	}
}

package br.com.courseracourse.tradutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CarregaDicionarioComArquivo {

	private String fileName;
	
	private static final String PADRAO_ENTRADA_ARQUIVO = "^([\\p{L}\\-0-9]+)=([\\p{L}\\-0-9]+)$";
	
	public CarregaDicionarioComArquivo(String fileName) {
		super();
		this.fileName = fileName;
	}

	public DicionarioTraducoes geraDicionarioAtravesDeArquivo() {
		DicionarioTraducoes dicionarioTraducoes = new DicionarioTraducoes();

		try (Stream<String> streamLines = Files.lines(Paths.get(fileName))){
			
			streamLines
				.filter(s -> s.matches(PADRAO_ENTRADA_ARQUIVO))
				.forEach(s -> {
					Matcher matcher = Pattern.compile(PADRAO_ENTRADA_ARQUIVO).matcher(s);
					if(matcher.find()) {
						dicionarioTraducoes.adicionaVerbete(matcher.group(1), matcher.group(2));
					}
				});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dicionarioTraducoes;
	}
	
}

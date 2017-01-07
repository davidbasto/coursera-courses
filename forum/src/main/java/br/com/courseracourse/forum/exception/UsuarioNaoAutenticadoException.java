package br.com.courseracourse.forum.exception;

public class UsuarioNaoAutenticadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNaoAutenticadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioNaoAutenticadoException(Throwable cause) {
		super(cause);
	}

	public UsuarioNaoAutenticadoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
}

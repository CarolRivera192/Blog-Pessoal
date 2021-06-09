package br.com.generation.blog.pessoal.model;

public class UserLogin {
	
	private String nome;
	private String usuario;
	private String senha;
	private String token;

	public UserLogin() {
		
	}
	
	public UserLogin(Usuario usuarioclass) {
		this.nome = usuarioclass.getNome();
		this.usuario = usuarioclass.getUsuario();
		this.senha = usuarioclass.getSenha();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

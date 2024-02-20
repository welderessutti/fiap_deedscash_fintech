package br.com.deedscash.bean;

import java.io.Serializable;
import java.sql.Date;

import br.com.deedscash.util.CriptografiaUtils;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idUsuario;
	private String nomeUsuario;
	private String sobrenomeUsuario;
	private String email;
	private String senha;
	private Date dataNascimento;
	private String genero;
	
	public Usuario() {
		super();
	}
	
	public Usuario(String email, String senha) {
		super();
		this.email = email;
		setSenha(senha);
	}
	
	public Usuario(
			String nomeUsuario,
			String sobrenomeUsuario,
			String email,
			String senha,
			Date dataNascimento,
			String genero)
	{
		super();
		this.nomeUsuario = nomeUsuario;
		this.sobrenomeUsuario = sobrenomeUsuario;
		this.email = email;
		setSenha(senha);
		this.dataNascimento = dataNascimento;
		this.genero = genero;
	}
	
	public Usuario(
			int idUsuario,
			String nomeUsuario,
			String sobrenomeUsuario,
			String email,
			String senha,
			Date dataNascimento,
			String genero)
	{
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.sobrenomeUsuario = sobrenomeUsuario;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSobrenomeUsuario() {
		return sobrenomeUsuario;
	}

	public void setSobrenomeUsuario(String sobrenomeUsuario) {
		this.sobrenomeUsuario = sobrenomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		try {
			this.senha = CriptografiaUtils.criptografar(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}

package br.com.deedscash.bean;

import java.io.Serializable;

public class Carteira implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idCarteira;
	private Usuario usuario;
	private String tipoCarteira;
	private String nomeBanco;
	private double valorSaldo;
	private String descricaoCarteira;
	
	public Carteira() {
		super();
	}
	
	public Carteira(
			int idCarteira, 
			double valorSaldo) 
	{
		super();
		this.idCarteira = idCarteira;
		this.valorSaldo = valorSaldo;
	}
	
	public Carteira(
			String tipoCarteira,
			String nomeBanco,
			double valorSaldo,
			String descricaoCarteira)
	{
		super();
		this.tipoCarteira = tipoCarteira;
		this.nomeBanco = nomeBanco;
		this.valorSaldo = valorSaldo;
		this.descricaoCarteira = descricaoCarteira;
	}
	
	public Carteira(
			int idCarteira,
			String tipoCarteira,
			String nomeBanco,
			double valorSaldo,
			String descricaoCarteira)
	{
		super();
		this.idCarteira = idCarteira;
		this.tipoCarteira = tipoCarteira;
		this.nomeBanco = nomeBanco;
		this.valorSaldo = valorSaldo;
		this.descricaoCarteira = descricaoCarteira;
	}

	public int getIdCarteira() {
		return idCarteira;
	}

	public void setIdCarteira(int idCarteira) {
		this.idCarteira = idCarteira;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTipoCarteira() {
		return tipoCarteira;
	}

	public void setTipoCarteira(String tipoCarteira) {
		this.tipoCarteira = tipoCarteira;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public double getValorSaldo() {
		return valorSaldo;
	}

	public void setValorSaldo(double valorSaldo) {
		this.valorSaldo = valorSaldo;
	}

	public String getDescricaoCarteira() {
		return descricaoCarteira;
	}

	public void setDescricaoCarteira(String descricaoCarteira) {
		this.descricaoCarteira = descricaoCarteira;
	}
	
	public void ajustaSaldo(String tipoMovimentacao, double valorMovimentacao) {
		
		double saldo;
		
		if (tipoMovimentacao.equals("Despesa")) {
			
			saldo = this.valorSaldo - valorMovimentacao;
			setValorSaldo(saldo);
			
		} else if (tipoMovimentacao.equals("Receita")) {
			
			saldo = this.valorSaldo + valorMovimentacao;
			setValorSaldo(saldo);
		}
	}
}

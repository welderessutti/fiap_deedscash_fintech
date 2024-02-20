package br.com.deedscash.bean;

import java.io.Serializable;

public class CartaoCredito implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idCartaoCredito;
	private Carteira carteira;
	private String nomeBandeira;
	private int diaFechamento;
	private int diaVencimento;
	private double valorFatura;
	private String descricaoCartaoCredito;
	
	public CartaoCredito() {
		super();		
	}
	
	public CartaoCredito(
			int idCartaoCredito,
			String nomeBandeira,
			int diaFechamento,
			int diaVencimento,
			double valorFatura,
			String descricaoCartaoCredito)
	{
		super();
		this.idCartaoCredito = idCartaoCredito;
		this.nomeBandeira = nomeBandeira;
		this.diaFechamento = diaFechamento;
		this.diaVencimento = diaVencimento;
		this.valorFatura = valorFatura;
		this.descricaoCartaoCredito = descricaoCartaoCredito;
	}

	public int getIdCartaoCredito() {
		return idCartaoCredito;
	}

	public void setIdCartaoCredito(int idCartaoCredito) {
		this.idCartaoCredito = idCartaoCredito;
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}

	public String getNomeBandeira() {
		return nomeBandeira;
	}

	public void setNomeBandeira(String nomeBandeira) {
		this.nomeBandeira = nomeBandeira;
	}

	public int getDiaFechamento() {
		return diaFechamento;
	}

	public void setDiaFechamento(int diaFechamento) {
		this.diaFechamento = diaFechamento;
	}

	public int getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(int diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public double getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(double valorFatura) {
		this.valorFatura = valorFatura;
	}

	public String getDescricaoCartaoCredito() {
		return descricaoCartaoCredito;
	}

	public void setDescricaoCartaoCredito(String descricaoCartaoCredito) {
		this.descricaoCartaoCredito = descricaoCartaoCredito;
	}
}

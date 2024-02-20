package br.com.deedscash.bean;

import java.io.Serializable;
import java.sql.Date;

public class Divida implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idDivida;
	private Carteira carteira;
	private String tipoDivida;
	private Date dataInicio;
	private Date dataVencimento;
	private int quantidadeParcelas;
	private double valorDivida;
	private double valorTaxa;
	private String descricaoDivida;
	
	public Divida() {
		super();		
	}
	
	public Divida(
			int idDivida,
			String tipoDivida,
			Date dataInicio,
			Date dataVencimento,
			int quantidadeParcelas,
			double valorDivida,
			double valorTaxa,
			String descricaoDivida)
	{
		super();
		this.idDivida = idDivida;
		this.tipoDivida = tipoDivida;
		this.dataInicio = dataInicio;
		this.dataVencimento = dataVencimento;
		this.quantidadeParcelas = quantidadeParcelas;
		this.valorDivida = valorDivida;
		this.valorTaxa = valorTaxa;
		this.descricaoDivida = descricaoDivida;
	}

	public int getIdDivida() {
		return idDivida;
	}

	public void setIdDivida(int idDivida) {
		this.idDivida = idDivida;
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}

	public String getTipoDivida() {
		return tipoDivida;
	}

	public void setTipoDivida(String tipoDivida) {
		this.tipoDivida = tipoDivida;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public double getValorDivida() {
		return valorDivida;
	}

	public void setValorDivida(double valorDivida) {
		this.valorDivida = valorDivida;
	}

	public double getValorTaxa() {
		return valorTaxa;
	}

	public void setValorTaxa(double valorTaxa) {
		this.valorTaxa = valorTaxa;
	}

	public String getDescricaoDivida() {
		return descricaoDivida;
	}

	public void setDescricaoDivida(String descricaoDivida) {
		this.descricaoDivida = descricaoDivida;
	}
}

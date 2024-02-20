package br.com.deedscash.bean;

import java.sql.Date;

public abstract class InvestimentoAbs {
	
	private int idInvestimento;
	private Carteira carteira;
	private String codigoTicker;
	private String tipoOperacao;
	private Date dataOperacao;
	private int quantidadeCota;
	private double valorOperacao;
	
	public InvestimentoAbs() {
		super();
	}
	
	public InvestimentoAbs(
			int idInvestimento,
			String codigoTicker,
			String tipoOperacao,
			Date dataOperacao,
			int quantidadeCota,
			double valorOperacao)
	{
		super();
		this.idInvestimento = idInvestimento;
		this.codigoTicker = codigoTicker;
		this.tipoOperacao = tipoOperacao;
		this.dataOperacao = dataOperacao;
		this.quantidadeCota = quantidadeCota;
		this.valorOperacao = valorOperacao;
	}

	public int getIdInvestimento() {
		return idInvestimento;
	}

	public void setIdInvestimento(int idInvestimento) {
		this.idInvestimento = idInvestimento;
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}

	public String getCodigoTicker() {
		return codigoTicker;
	}

	public void setCodigoTicker(String codigoTicker) {
		this.codigoTicker = codigoTicker;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public int getQuantidadeCota() {
		return quantidadeCota;
	}

	public void setQuantidadeCota(int quantidadeCota) {
		this.quantidadeCota = quantidadeCota;
	}

	public double getValorOperacao() {
		return valorOperacao;
	}

	public void setValorOperacao(double valorOperacao) {
		this.valorOperacao = valorOperacao;
	}
}

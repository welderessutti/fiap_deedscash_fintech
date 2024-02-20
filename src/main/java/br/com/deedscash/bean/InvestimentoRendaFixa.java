package br.com.deedscash.bean;

import java.io.Serializable;
import java.sql.Date;

public class InvestimentoRendaFixa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idInvestimento;
	private Carteira carteira;
	private String nomeCategoria;
	private String nomeEmissor;
	private String tipoIndice;
	private double valorTaxa;
	private String tipoOperacao;
	private Date dataOperacao;
	private int quantidadeCota;
	private double valorOperacao;
	
	public InvestimentoRendaFixa() {
		super();
	}
	
	public InvestimentoRendaFixa(
			int idInvestimento,
			String nomeCategoria,
			String nomeEmissor,
			String tipoIndice,
			double valorTaxa,
			String tipoOperacao,
			Date dataOperacao,
			int quantidadeCota,
			double valorOperacao)
	{
		super();
		this.idInvestimento = idInvestimento;
		this.nomeCategoria = nomeCategoria;
		this.nomeEmissor = nomeEmissor;
		this.tipoIndice = tipoIndice;
		this.valorTaxa = valorTaxa;
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

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getNomeEmissor() {
		return nomeEmissor;
	}

	public void setNomeEmissor(String nomeEmissor) {
		this.nomeEmissor = nomeEmissor;
	}

	public String getTipoIndice() {
		return tipoIndice;
	}

	public void setTipoIndice(String tipoIndice) {
		this.tipoIndice = tipoIndice;
	}

	public double getValorTaxa() {
		return valorTaxa;
	}

	public void setValorTaxa(double valorTaxa) {
		this.valorTaxa = valorTaxa;
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

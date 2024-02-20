package br.com.deedscash.bean;

import java.io.Serializable;
import java.sql.Date;

public class Movimentacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idMovimentacao;
	private Carteira carteira;
	private String tipoMovimentacao;
	private Date dataHoraMovimentacao;
	private double valorMovimentacao;
	private String descricaoMovimentacao;
	
	public Movimentacao() {
		super();
	}
	
	public Movimentacao(
			String tipoMovimentacao,
			Date dataHoraMovimentacao,
			double valorMovimentacao,
			String descricaoMovimentacao)
	{
		super();
		this.tipoMovimentacao = tipoMovimentacao;
		this.dataHoraMovimentacao = dataHoraMovimentacao;
		this.valorMovimentacao = valorMovimentacao;
		this.descricaoMovimentacao = descricaoMovimentacao;
	}
	
	public Movimentacao(
			int idMovimentacao,
			String tipoMovimentacao,
			Date dataHoraMovimentacao,
			double valorMovimentacao,
			String descricaoMovimentacao)
	{
		super();
		this.idMovimentacao = idMovimentacao;
		this.tipoMovimentacao = tipoMovimentacao;
		this.dataHoraMovimentacao = dataHoraMovimentacao;
		this.valorMovimentacao = valorMovimentacao;
		this.descricaoMovimentacao = descricaoMovimentacao;
	}

	public int getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(int idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Date getDataHoraMovimentacao() {
		return dataHoraMovimentacao;
	}

	public void setDataHoraMovimentacao(Date dataHoraMovimentacao) {
		this.dataHoraMovimentacao = dataHoraMovimentacao;
	}

	public double getValorMovimentacao() {
		return valorMovimentacao;
	}

	public void setValorMovimentacao(double valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public String getDescricaoMovimentacao() {
		return descricaoMovimentacao;
	}

	public void setDescricaoMovimentacao(String descricaoMovimentacao) {
		this.descricaoMovimentacao = descricaoMovimentacao;
	}
}

package br.com.deedscash.bean;

import java.io.Serializable;
import java.sql.Date;

public class Objetivo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idObjetivo;
	private Carteira carteira;
	private String tipoObjetivo;
	private Date dataInicio;
	private Date dataFinal;
	private double valorObjetivo;
	private String descricaoObjetivo;
	
	public Objetivo() {
		super();
	}
	
	public Objetivo(
			int idObjetivo,
			String tipoObjetivo,
			Date dataInicio,
			Date dataFinal,
			double valorObjetivo,
			String descricaoObjetivo)
	{
		super();
		this.idObjetivo = idObjetivo;
		this.tipoObjetivo = tipoObjetivo;
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.valorObjetivo = valorObjetivo;
		this.descricaoObjetivo = descricaoObjetivo;
	}

	public int getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(int idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}

	public String getTipoObjetivo() {
		return tipoObjetivo;
	}

	public void setTipoObjetivo(String tipoObjetivo) {
		this.tipoObjetivo = tipoObjetivo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public double getValorObjetivo() {
		return valorObjetivo;
	}

	public void setValorObjetivo(double valorObjetivo) {
		this.valorObjetivo = valorObjetivo;
	}

	public String getDescricaoObjetivo() {
		return descricaoObjetivo;
	}

	public void setDescricaoObjetivo(String descricaoObjetivo) {
		this.descricaoObjetivo = descricaoObjetivo;
	}
}

package br.com.deedscash.bean;

import java.io.Serializable;
import java.sql.Date;

public class InvestimentoFii extends InvestimentoAbs implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomeFundo;
	
	public InvestimentoFii() {
		super();
	}
	
	public InvestimentoFii(
			int idInvestimento,
			String codigoTicker,
			String nomeFundo,
			String tipoOperacao,
			Date dataOperacao,
			int quantidadeCota,
			double valorOperacao)
	{
		super(
				idInvestimento,
				codigoTicker,
				tipoOperacao,
				dataOperacao,
				quantidadeCota,
				valorOperacao);
		this.nomeFundo = nomeFundo;
	}

	public String getNomeFundo() {
		return nomeFundo;
	}

	public void setNomeFundo(String nomeFundo) {
		this.nomeFundo = nomeFundo;
	}
}

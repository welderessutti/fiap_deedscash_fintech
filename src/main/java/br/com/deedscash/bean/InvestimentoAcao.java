package br.com.deedscash.bean;

import java.io.Serializable;
import java.sql.Date;

public class InvestimentoAcao extends InvestimentoAbs implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomeEmpresa;
	
	public InvestimentoAcao() {
		super();
	}
	
	public InvestimentoAcao(
			int idInvestimento,
			String codigoTicker,
			String nomeEmpresa,
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
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
}

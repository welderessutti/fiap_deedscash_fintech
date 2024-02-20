package br.com.deedscash.dao;

import java.util.List;
import br.com.deedscash.bean.InvestimentoFii;
import br.com.deedscash.exception.DBException;

public interface InvestimentoFiiDAO {
	
	void insert(InvestimentoFii investimentoFii) throws DBException;
	
	List<InvestimentoFii> getAll();
	
	void atualizar(InvestimentoFii investimentoFii) throws DBException;
	
	void remover(int idInvestimentoFii) throws DBException;
	
	InvestimentoFii buscarPorId(int idInvestimentoFii);
}

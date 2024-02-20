package br.com.deedscash.dao;

import java.util.List;
import br.com.deedscash.bean.InvestimentoAcao;
import br.com.deedscash.exception.DBException;

public interface InvestimentoAcaoDAO {
	
	void insert(InvestimentoAcao investimentoAcao) throws DBException;
	
	List<InvestimentoAcao> getAll();
	
	void atualizar(InvestimentoAcao investimentoAcao) throws DBException;
	
	void remover(int idInvestimentoAcao) throws DBException;
	
	InvestimentoAcao buscarPorId(int idInvestimentoAcao);
}

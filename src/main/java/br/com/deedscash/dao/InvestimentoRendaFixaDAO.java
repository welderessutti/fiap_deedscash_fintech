package br.com.deedscash.dao;

import java.util.List;
import br.com.deedscash.bean.InvestimentoRendaFixa;
import br.com.deedscash.exception.DBException;

public interface InvestimentoRendaFixaDAO {
	
	void insert(InvestimentoRendaFixa investimentoRendaFixa) throws DBException;
	
	List<InvestimentoRendaFixa> getAll();
	
	void atualizar(InvestimentoRendaFixa investimentoRendaFixa) throws DBException;
	
	void remover(int idInvestimentoRendaFixa) throws DBException;
	
	InvestimentoRendaFixa buscarPorId(int idInvestimentoRendaFixa);
}

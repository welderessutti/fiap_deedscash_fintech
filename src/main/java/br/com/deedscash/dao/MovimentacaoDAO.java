package br.com.deedscash.dao;

import java.util.List;
import br.com.deedscash.bean.Movimentacao;
import br.com.deedscash.exception.DBException;

public interface MovimentacaoDAO {
	
	void insert(Movimentacao movimentacao) throws DBException;
	
	List<Movimentacao> getAll();
	
	void atualizar(Movimentacao movimentacao) throws DBException;
	
	void remover(int idMovimentacao) throws DBException;
	
	Movimentacao buscarPorId(int idMovimentacao);
	
	List<Movimentacao> buscarPorIdCarteira(int idCarteira);
}

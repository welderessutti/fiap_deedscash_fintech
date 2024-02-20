package br.com.deedscash.dao;

import java.util.List;
import br.com.deedscash.bean.Carteira;
import br.com.deedscash.exception.DBException;

public interface CarteiraDAO {
	
	void insert(Carteira carteira) throws DBException;
	
	List<Carteira> getAll();
	
	void atualizar(Carteira carteira) throws DBException;
	
	void remover(int idCarteira) throws DBException;
	
	Carteira buscarPorId(int idCarteira);
	
	List<Carteira> buscarPorIdUsuario(int idUsuario);
	
	void atualizarSaldo(Carteira carteira) throws DBException;
}

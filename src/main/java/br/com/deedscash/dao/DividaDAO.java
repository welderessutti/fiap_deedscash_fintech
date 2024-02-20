package br.com.deedscash.dao;

import java.util.List;
import br.com.deedscash.bean.Divida;
import br.com.deedscash.exception.DBException;

public interface DividaDAO {
	
	void insert(Divida divida) throws DBException;
	
	List<Divida> getAll();
	
	void atualizar(Divida divida) throws DBException;
	
	void remover(int idDivida) throws DBException;
	
	Divida buscarPorId(int idDivida);
}

package br.com.deedscash.dao;

import java.util.List;
import br.com.deedscash.bean.Objetivo;
import br.com.deedscash.exception.DBException;

public interface ObjetivoDAO {
	
	void insert(Objetivo objetivo) throws DBException;
	
	List<Objetivo> getAll();
	
	void atualizar(Objetivo objetivo) throws DBException;
	
	void remover(int idObjetivo) throws DBException;
	
	Objetivo buscarPorId(int idObjetivo);
}

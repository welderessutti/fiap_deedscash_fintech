package br.com.deedscash.dao;

import java.util.List;
import br.com.deedscash.bean.CartaoCredito;
import br.com.deedscash.exception.DBException;

public interface CartaoCreditoDAO {
	
	void insert(CartaoCredito cartaoCredito) throws DBException;
	
	List<CartaoCredito> getAll();
	
	void atualizar(CartaoCredito cartaoCredito) throws DBException;
	
	void remover(int idCartaoCredito) throws DBException;
	
	CartaoCredito buscarPorId(int idCartaoCredito);
}

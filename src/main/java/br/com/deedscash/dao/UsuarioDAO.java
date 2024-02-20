package br.com.deedscash.dao;

import java.util.List;
import br.com.deedscash.bean.Usuario;
import br.com.deedscash.exception.DBException;

public interface UsuarioDAO {
	
	boolean validarUsuario(Usuario usuario) throws DBException;
	
	void insert(Usuario usuario) throws DBException;
	
	List<Usuario> getAll();
	
	void atualizar(Usuario usuario) throws DBException;
	
	void remover(int idUsuario) throws DBException;
	
	Usuario buscarPorId(int idUsuario);
	
	boolean verificarEmailExistente(String email);
	
	Usuario buscarPorEmail(String email);
}

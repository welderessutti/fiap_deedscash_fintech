package br.com.deedscash.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.deedscash.bean.Usuario;
import br.com.deedscash.dao.UsuarioDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.singleton.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO {
	
	private Connection conexao;
	
	// Insere um usuário na tabela usuário
	@Override
	public void insert(Usuario usuario) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_USUARIO "
					+ "(ID_USUARIO, NM_USUARIO, "
					+ "SN_USUARIO, DS_EMAIL, DS_SENHA, "
					+ "DT_NASCIMENTO, DS_GENERO) VALUES "
					+ "(SQ_USUARIO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNomeUsuario());
			stmt.setString(2, usuario.getSobrenomeUsuario());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getSenha());
			stmt.setDate(5, usuario.getDataNascimento());
			stmt.setString(6, usuario.getGenero());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				stmt.close();
				conexao.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	// Retorna todos os usuários da tabela usuário
	@Override
	public List<Usuario> getAll() {
		
		List<Usuario> lista = new ArrayList<Usuario>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_USUARIO ORDER BY ID_USUARIO";
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				int idUsuario = rs.getInt("ID_USUARIO");
				String nomeUsuario = rs.getString("NM_USUARIO");
				String sobrenomeUsuario = rs.getString("SN_USUARIO");
				String email = rs.getString("DS_EMAIL");
				String senha = rs.getString("DS_SENHA");
				Date dataNascimento = rs.getDate("DT_NASCIMENTO");
				String genero = rs.getString("DS_GENERO");
				
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				lista.add(usuario);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				stmt.close();
				rs.close();
				conexao.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	// Atualiza um usuário na tabela usuário
	@Override
	public void atualizar(Usuario usuario) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_USUARIO "
					+ "SET NM_USUARIO = ?, SN_USUARIO = ?, DS_EMAIL = ?, "
					+ "DS_SENHA = ?, DT_NASCIMENTO = ?, DS_GENERO = ? "
					+ "WHERE ID_USUARIO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNomeUsuario());
			stmt.setString(2, usuario.getSobrenomeUsuario());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getSenha());
			stmt.setDate(5, usuario.getDataNascimento());
			stmt.setString(6, usuario.getGenero());
			stmt.setInt(7, usuario.getIdUsuario());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				stmt.close();
				conexao.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	// Remove um usuário da tabela usuário
	@Override
	public void remover(int idUsuario) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_USUARIO "
					+ "WHERE ID_USUARIO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				stmt.close();
				conexao.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	// Retorna um usuário da tabela usuário
	@Override
	public Usuario buscarPorId(int idUsuario) {
		
		Usuario usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_USUARIO "
					+ "WHERE ID_USUARIO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				int _idUsuario = rs.getInt("ID_USUARIO");
				String nomeUsuario = rs.getString("NM_USUARIO");
				String sobrenomeUsuario = rs.getString("SN_USUARIO");
				String email = rs.getString("DS_EMAIL");
				String senha = rs.getString("DS_SENHA");
				Date dataNascimento = rs.getDate("DT_NASCIMENTO");
				String genero = rs.getString("DS_GENERO");
				
				usuario = new Usuario(_idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				rs.close();
				stmt.close();
				conexao.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return usuario;
	}
	
	@Override
	public boolean validarUsuario(Usuario usuario) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				stmt.close();
				rs.close();
				conexao.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public boolean verificarEmailExistente(String email) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_USUARIO WHERE DS_EMAIL = ?";
			stmt = conexao.prepareStatement(sql);			
			stmt.setString(1, email);			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			
		} finally {
			
			try {
				
				rs.close();
				stmt.close();
				conexao.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public Usuario buscarPorEmail(String email) {
		
		Usuario usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT ID_USUARIO, NM_USUARIO, DS_EMAIL "
					+ "FROM T_USUARIO WHERE DS_EMAIL = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				int idUsuario = rs.getInt("ID_USUARIO");
				String nomeUsuario = rs.getString("NM_USUARIO");
				String _email = rs.getString("DS_EMAIL");
				
				usuario = new Usuario();
				usuario.setIdUsuario(idUsuario);
				usuario.setNomeUsuario(nomeUsuario);
				usuario.setEmail(_email);
				
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				rs.close();
				stmt.close();
				conexao.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return usuario;
	}
}

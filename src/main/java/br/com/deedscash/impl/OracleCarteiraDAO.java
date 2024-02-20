package br.com.deedscash.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.deedscash.bean.Carteira;
import br.com.deedscash.bean.Usuario;
import br.com.deedscash.dao.CarteiraDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.singleton.ConnectionManager;

public class OracleCarteiraDAO implements CarteiraDAO {
	
	private Connection conexao;
	
	// Insere uma carteira na tabela carteira
	@Override
	public void insert(Carteira carteira) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_CARTEIRA "
					+ "(ID_CARTEIRA, ID_USUARIO, "
					+ "TP_CARTEIRA, NM_BANCO, "
					+ "VL_SALDO, DS_CARTEIRA) VALUES "
					+ "(SQ_CARTEIRA.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, carteira.getUsuario().getIdUsuario());
			stmt.setString(2, carteira.getTipoCarteira());
			stmt.setString(3, carteira.getNomeBanco());
			stmt.setDouble(4, carteira.getValorSaldo());
			stmt.setString(5, carteira.getDescricaoCarteira());
			
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
	
	// Retorna todas as carteira da tabela carteira
	@Override
	public List<Carteira> getAll() {
		
		List<Carteira> lista = new ArrayList<Carteira>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_CARTEIRA C "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "ORDER BY ID_CARTEIRA";
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int idCarteira = rs.getInt("ID_CARTEIRA");
				String tipoCarteira = rs.getString("TP_CARTEIRA");
				String nomeBanco = rs.getString("NM_BANCO");
				double valorSaldo = rs.getDouble("VL_SALDO");
				String descricaoCarteira = rs.getString("DS_CARTEIRA");
				
				int idUsuario = rs.getInt("ID_USUARIO");
				String nomeUsuario = rs.getString("NM_USUARIO");
				String sobrenomeUsuario = rs.getString("SN_USUARIO");
				String email = rs.getString("DS_EMAIL");
				String senha = rs.getString("DS_SENHA");
				Date dataNascimento = rs.getDate("DT_NASCIMENTO");
				String genero = rs.getString("DS_GENERO");
				
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, 
						nomeBanco, valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				carteira.setUsuario(usuario);
				
				lista.add(carteira);
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
	
	// Atualiza uma carteira da tabela carteira
	@Override
	public void atualizar(Carteira carteira) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_CARTEIRA "
					+ "SET ID_USUARIO = ?, TP_CARTEIRA = ?, "
					+ "NM_BANCO = ?, VL_SALDO = ?, DS_CARTEIRA = ? "
					+ "WHERE ID_CARTEIRA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, carteira.getUsuario().getIdUsuario());
			stmt.setString(2, carteira.getTipoCarteira());
			stmt.setString(3, carteira.getNomeBanco());
			stmt.setDouble(4, carteira.getValorSaldo());
			stmt.setString(5, carteira.getDescricaoCarteira());
			stmt.setInt(6, carteira.getIdCarteira());
			
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
	
	// Remover uma carteira da tabela carteira
	@Override
	public void remover(int idCarteira) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_CARTEIRA "
					+ "WHERE ID_CARTEIRA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idCarteira);
			
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
	
	// Retornar uma carteira da tabela carteira
	@Override
	public Carteira buscarPorId(int idCarteira) {
		
		Carteira carteira = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_CARTEIRA C"
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "WHERE ID_CARTEIRA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idCarteira);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int _idCarteira = rs.getInt("ID_CARTEIRA");
				String tipoCarteira = rs.getString("TP_CARTEIRA");
				String nomeBanco = rs.getString("NM_BANCO");
				double valorSaldo = rs.getDouble("VL_SALDO");
				String descricaoCarteira = rs.getString("DS_CARTEIRA");
				
				int idUsuario = rs.getInt("ID_USUARIO");
				String nomeUsuario = rs.getString("NM_USUARIO");
				String sobrenomeUsuario = rs.getString("SN_USUARIO");
				String email = rs.getString("DS_EMAIL");
				String senha = rs.getString("DS_SENHA");
				Date dataNascimento = rs.getDate("DT_NASCIMENTO");
				String genero = rs.getString("DS_GENERO");
				
				carteira = new Carteira(_idCarteira, tipoCarteira, 
						nomeBanco, valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				carteira.setUsuario(usuario);				
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
		return carteira;
	}
	
	@Override
	public List<Carteira> buscarPorIdUsuario(int idUsuario) {
		
		List<Carteira> lista = new ArrayList<Carteira>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Carteira carteira = null;
		Usuario usuario = new Usuario();
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_CARTEIRA WHERE ID_USUARIO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();
			
			while (rs.next()) {				
				int idCarteira = rs.getInt("ID_CARTEIRA");
				int _idUsuario = rs.getInt("ID_USUARIO");
				String tipoCarteira = rs.getString("TP_CARTEIRA");
				String nomeBanco = rs.getString("NM_BANCO");
				double valorSaldo = rs.getDouble("VL_SALDO");
				String descricaoCarteira = rs.getString("DS_CARTEIRA");
				
				carteira = new Carteira(idCarteira, tipoCarteira, 
						nomeBanco, valorSaldo, descricaoCarteira);
				
				usuario.setIdUsuario(_idUsuario);
				carteira.setUsuario(usuario);
				
				lista.add(carteira);
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
		return lista;
	}
	
	@Override
	public void atualizarSaldo(Carteira carteira) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_CARTEIRA "
					+ "SET VL_SALDO = ? "
					+ "WHERE ID_CARTEIRA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, carteira.getValorSaldo());
			stmt.setInt(2, carteira.getIdCarteira());
			
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
}

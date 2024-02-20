package br.com.deedscash.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.deedscash.bean.Carteira;
import br.com.deedscash.bean.Objetivo;
import br.com.deedscash.bean.Usuario;
import br.com.deedscash.dao.ObjetivoDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.singleton.ConnectionManager;

public class OracleObjetivoDAO implements ObjetivoDAO {
	
	private Connection conexao;
	
	// Insere uma objetivo na tabela objetivo
	@Override
	public void insert(Objetivo objetivo) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_OBJETIVO "
					+ "(ID_OBJETIVO, ID_CARTEIRA, "
					+ "TP_OBJETIVO, DT_INICIO, DT_FINAL, "
					+ "VL_OBJETIVO, DS_OBJETIVO) VALUES "
					+ "(SQ_OBJETIVO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, objetivo.getCarteira().getIdCarteira());
			stmt.setString(2, objetivo.getTipoObjetivo());
			stmt.setDate(3, objetivo.getDataInicio());
			stmt.setDate(4, objetivo.getDataFinal());
			stmt.setDouble(5, objetivo.getValorObjetivo());
			stmt.setString(6, objetivo.getDescricaoObjetivo());
			
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
	
	// Retorna todas as objetivo da tabela objetivo
	@Override
	public List<Objetivo> getAll() {
		
		List<Objetivo> lista = new ArrayList<Objetivo>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_OBJETIVO O "
					+ "INNER JOIN T_CARTEIRA C ON O.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "ORDER BY ID_OBJETIVO";
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int idObjetivo = rs.getInt("ID_OBJETIVO");
				String tipoObjetivo = rs.getString("TP_OBJETIVO");
				Date dataInicio = rs.getDate("DT_INICIO");
				Date dataFinal = rs.getDate("DT_FINAL");
				double valorObjetivo = rs.getDouble("VL_OBJETIVO");
				String descricaoObjetivo = rs.getString("DS_OBJETIVO");
				
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
				
				Objetivo objetivo = new Objetivo(idObjetivo, tipoObjetivo, 
						dataInicio, dataFinal, valorObjetivo, descricaoObjetivo);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				carteira.setUsuario(usuario);
				objetivo.setCarteira(carteira);
				
				lista.add(objetivo);
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
	
	// Atualiza uma objetivo da tabela objetivo
	@Override
	public void atualizar(Objetivo objetivo) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_OBJETIVO "
					+ "SET ID_CARTEIRA = ?, TP_OBJETIVO = ?, "
					+ "DT_INICIO = ?, DT_FINAL = ?, VL_OBJETIVO = ?, DS_OBJETIVO = ? "
					+ "WHERE ID_OBJETIVO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, objetivo.getCarteira().getIdCarteira());
			stmt.setString(2, objetivo.getTipoObjetivo());
			stmt.setDate(3, objetivo.getDataInicio());
			stmt.setDate(4, objetivo.getDataFinal());
			stmt.setDouble(5, objetivo.getValorObjetivo());
			stmt.setString(6, objetivo.getDescricaoObjetivo());
			stmt.setInt(7, objetivo.getIdObjetivo());
			
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
	
	// Remover uma objetivo da tabela objetivo
	@Override
	public void remover(int idObjetivo) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_OBJETIVO "
					+ "WHERE ID_OBJETIVO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idObjetivo);
			
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
	
	// Retornar uma objetivo da tabela objetivo
	@Override
	public Objetivo buscarPorId(int idObjetivo) {
		
		Objetivo objetivo = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_OBJETIVO O "
					+ "INNER JOIN T_CARTEIRA C ON O.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "WHERE ID_OBJETIVO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idObjetivo);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int _idObjetivo = rs.getInt("ID_OBJETIVO");
				String tipoObjetivo = rs.getString("TP_OBJETIVO");
				Date dataInicio = rs.getDate("DT_INICIO");
				Date dataFinal = rs.getDate("DT_FINAL");
				double valorObjetivo = rs.getDouble("VL_OBJETIVO");
				String descricaoObjetivo = rs.getString("DS_OBJETIVO");
				
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
				
				objetivo = new Objetivo(_idObjetivo, tipoObjetivo, 
						dataInicio, dataFinal, valorObjetivo, descricaoObjetivo);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				carteira.setUsuario(usuario);
				objetivo.setCarteira(carteira);
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
		return objetivo;
	}
}

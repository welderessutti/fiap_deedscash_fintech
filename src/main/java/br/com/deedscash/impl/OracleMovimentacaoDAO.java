package br.com.deedscash.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.deedscash.bean.Carteira;
import br.com.deedscash.bean.Movimentacao;
import br.com.deedscash.bean.Usuario;
import br.com.deedscash.dao.MovimentacaoDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.singleton.ConnectionManager;

public class OracleMovimentacaoDAO implements MovimentacaoDAO {
	
	private Connection conexao;
	
	// Insere uma movimentacao na tabela movimentacao
	@Override
	public void insert(Movimentacao movimentacao) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_MOVIMENTACAO "
					+ "(ID_MOVIMENTACAO, ID_CARTEIRA, "
					+ "TP_MOVIMENTACAO, DT_HR_MOVIMENTACAO, "
					+ "VL_MOVIMENTACAO, DS_MOVIMENTACAO) VALUES "
					+ "(SQ_MOVIMENTACAO.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, movimentacao.getCarteira().getIdCarteira());
			stmt.setString(2, movimentacao.getTipoMovimentacao());
			stmt.setDate(3, movimentacao.getDataHoraMovimentacao());
			stmt.setDouble(4, movimentacao.getValorMovimentacao());
			stmt.setString(5, movimentacao.getDescricaoMovimentacao());
			
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
	
	// Retorna todas as movimentacao da tabela movimentacao
	@Override
	public List<Movimentacao> getAll() {
		
		List<Movimentacao> lista = new ArrayList<Movimentacao>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_MOVIMENTACAO M "
					+ "INNER JOIN T_CARTEIRA C ON M.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "ORDER BY ID_MOVIMENTACAO";
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int idMovimentacao = rs.getInt("ID_MOVIMENTACAO");
				String tipoMovimentacao = rs.getString("TP_MOVIMENTACAO");
				Date dataHoraMovimentacao = rs.getDate("DT_HR_MOVIMENTACAO");
				double valorMovimentacao = rs.getDouble("VL_MOVIMENTACAO");
				String descricaoMovimentacao = rs.getString("DS_MOVIMENTACAO");
				
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
				
				Movimentacao movimentacao = new Movimentacao(idMovimentacao, tipoMovimentacao, 
						dataHoraMovimentacao, valorMovimentacao, descricaoMovimentacao);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				carteira.setUsuario(usuario);
				movimentacao.setCarteira(carteira);
				
				lista.add(movimentacao);
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
	
	// Atualiza uma movimentacao da tabela movimentacao
	@Override
	public void atualizar(Movimentacao movimentacao) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_MOVIMENTACAO "
					+ "SET ID_CARTEIRA = ?, TP_MOVIMENTACAO = ?, "
					+ "DT_HR_MOVIMENTACAO = ?, VL_MOVIMENTACAO = ?, DS_MOVIMENTACAO = ? "
					+ "WHERE ID_MOVIMENTACAO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, movimentacao.getCarteira().getIdCarteira());
			stmt.setString(2, movimentacao.getTipoMovimentacao());
			stmt.setDate(3, movimentacao.getDataHoraMovimentacao());
			stmt.setDouble(4, movimentacao.getValorMovimentacao());
			stmt.setString(5, movimentacao.getDescricaoMovimentacao());
			stmt.setInt(6, movimentacao.getIdMovimentacao());
			
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
	
	// Remover uma movimentacao da tabela movimentacao
	@Override
	public void remover(int idMovimentacao) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_MOVIMENTACAO "
					+ "WHERE ID_MOVIMENTACAO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idMovimentacao);
			
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
	
	// Retornar uma movimentacao da tabela movimentacao
	@Override
	public Movimentacao buscarPorId(int idMovimentacao) {
		
		Movimentacao movimentacao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_MOVIMENTACAO M "
					+ "INNER JOIN T_CARTEIRA C ON M.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "WHERE ID_MOVIMENTACAO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idMovimentacao);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int _idMovimentacao = rs.getInt("ID_MOVIMENTACAO");
				String tipoMovimentacao = rs.getString("TP_MOVIMENTACAO");
				Date dataHoraMovimentacao = rs.getDate("DT_HR_MOVIMENTACAO");
				double valorMovimentacao = rs.getDouble("VL_MOVIMENTACAO");
				String descricaoMovimentacao = rs.getString("DS_MOVIMENTACAO");
				
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
				
				movimentacao = new Movimentacao(_idMovimentacao, tipoMovimentacao, 
						dataHoraMovimentacao, valorMovimentacao, descricaoMovimentacao);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				carteira.setUsuario(usuario);
				movimentacao.setCarteira(carteira);				
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
		return movimentacao;
	}
	
	@Override
	public List<Movimentacao> buscarPorIdCarteira(int idCarteira) {
		
		List<Movimentacao> lista = new ArrayList<Movimentacao>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Movimentacao movimentacao = null;
		Carteira carteira = new Carteira();
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_MOVIMENTACAO "
					+ "WHERE ID_CARTEIRA = ? "
					+ "ORDER BY DT_HR_MOVIMENTACAO DESC";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idCarteira);
			rs = stmt.executeQuery();
			
			while (rs.next()) {				
				int idMovimentacao = rs.getInt("ID_MOVIMENTACAO");
				int _idCarteira = rs.getInt("ID_CARTEIRA");
				String tipoMovimentacao = rs.getString("TP_MOVIMENTACAO");
				Date dataHoraMovimentacao = rs.getDate("DT_HR_MOVIMENTACAO");
				double valorMovimentacao = rs.getDouble("VL_MOVIMENTACAO");
				String descricaoMovimentacao = rs.getString("DS_MOVIMENTACAO");
				
				movimentacao = new Movimentacao(idMovimentacao, tipoMovimentacao, 
						dataHoraMovimentacao, valorMovimentacao, descricaoMovimentacao);
				
				carteira.setIdCarteira(_idCarteira);
				movimentacao.setCarteira(carteira);
				
				lista.add(movimentacao);
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
}

package br.com.deedscash.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.deedscash.bean.CartaoCredito;
import br.com.deedscash.bean.Carteira;
import br.com.deedscash.bean.Usuario;
import br.com.deedscash.dao.CartaoCreditoDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.singleton.ConnectionManager;

public class OracleCartaoCreditoDAO implements CartaoCreditoDAO {
	
	private Connection conexao;
	
	// Insere uma cartaoCredito na tabela cartaoCredito
	@Override
	public void insert(CartaoCredito cartaoCredito) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_CC "
					+ "(ID_CC, ID_CARTEIRA, "
					+ "NM_BANDEIRA, DIA_FECHAMENTO, "
					+ "DIA_VENCIMENTO, VL_FATURA, DS_CARTAO_DE_CREDITO) VALUES "
					+ "(SQ_CC.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, cartaoCredito.getCarteira().getIdCarteira());
			stmt.setString(2, cartaoCredito.getNomeBandeira());
			stmt.setInt(3, cartaoCredito.getDiaFechamento());
			stmt.setInt(4, cartaoCredito.getDiaVencimento());
			stmt.setDouble(5, cartaoCredito.getValorFatura());
			stmt.setString(6, cartaoCredito.getDescricaoCartaoCredito());
			
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
	
	// Retorna todas as cartaoCredito da tabela cartaoCredito
	@Override
	public List<CartaoCredito> getAll() {
		
		List<CartaoCredito> lista = new ArrayList<CartaoCredito>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_CC CC "
					+ "INNER JOIN T_CARTEIRA C ON CC.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "ORDER BY ID_CC";
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int idCartaoCredito = rs.getInt("ID_CC");
				String nomeBandeira = rs.getString("NM_BANDEIRA");
				int diaFechamento = rs.getInt("DIA_FECHAMENTO");
				int diaVencimento = rs.getInt("DIA_VENCIMENTO");
				double valorFatura = rs.getDouble("VL_FATURA");
				String descricaoCartaoCredito = rs.getString("DS_CARTAO_DE_CREDITO");
				
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
				
				CartaoCredito cartaoCredito = new CartaoCredito(idCartaoCredito, nomeBandeira, 
						diaFechamento, diaVencimento, valorFatura, descricaoCartaoCredito);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				carteira.setUsuario(usuario);
				cartaoCredito.setCarteira(carteira);
				
				lista.add(cartaoCredito);
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
	
	// Atualiza uma cartaoCredito da tabela cartaoCredito
	@Override
	public void atualizar(CartaoCredito cartaoCredito) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_CC "
					+ "SET ID_CARTEIRA = ?, NM_BANDEIRA = ?, "
					+ "DIA_FECHAMENTO = ?, DIA_VENCIMENTO = ?, VL_FATURA = ?, DS_CARTAO_DE_CREDITO = ? "
					+ "WHERE ID_CC = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, cartaoCredito.getCarteira().getIdCarteira());
			stmt.setString(2, cartaoCredito.getNomeBandeira());
			stmt.setInt(3, cartaoCredito.getDiaFechamento());
			stmt.setInt(4, cartaoCredito.getDiaVencimento());
			stmt.setDouble(5, cartaoCredito.getValorFatura());
			stmt.setString(6, cartaoCredito.getDescricaoCartaoCredito());
			stmt.setInt(7, cartaoCredito.getIdCartaoCredito());
			
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
	
	// Remover uma cartaoCredito da tabela cartaoCredito
	@Override
	public void remover(int idCartaoCredito) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_CC "
					+ "WHERE ID_CC = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idCartaoCredito);
			
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
	
	// Retornar uma cartaoCredito da tabela cartaoCredito
	@Override
	public CartaoCredito buscarPorId(int idCartaoCredito) {
		
		CartaoCredito cartaoCredito = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_CC CC "
					+ "INNER JOIN T_CARTEIRA C ON CC.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "WHERE ID_CC = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idCartaoCredito);
			rs = stmt.executeQuery();
			
			if (rs.next()) {				
				int _idCartaoCredito = rs.getInt("ID_CC");
				String nomeBandeira = rs.getString("NM_BANDEIRA");
				int diaFechamento = rs.getInt("DIA_FECHAMENTO");
				int diaVencimento = rs.getInt("DIA_VENCIMENTO");
				double valorFatura = rs.getDouble("VL_FATURA");
				String descricaoCartaoCredito = rs.getString("DS_CARTAO_DE_CREDITO");
				
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
				
				cartaoCredito = new CartaoCredito(_idCartaoCredito, nomeBandeira, 
						diaFechamento, diaVencimento, valorFatura, descricaoCartaoCredito);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				carteira.setUsuario(usuario);
				cartaoCredito.setCarteira(carteira);
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
		return cartaoCredito;
	}
}

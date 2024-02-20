package br.com.deedscash.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.deedscash.bean.Carteira;
import br.com.deedscash.bean.InvestimentoFii;
import br.com.deedscash.bean.Usuario;
import br.com.deedscash.dao.InvestimentoFiiDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.singleton.ConnectionManager;

public class OracleInvestimentoFiiDAO implements InvestimentoFiiDAO {
	
	private Connection conexao;
	
	// Insere uma investimentoFii na tabela investimentoFii
	@Override
	public void insert(InvestimentoFii investimentoFii) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_INVEST_FII "
					+ "(ID_INVESTIMENTO, ID_CARTEIRA, "
					+ "CD_TICKER, NM_FUNDO, TP_OPERACAO, "
					+ "DT_OPERACAO, QT_COTA, VL_OPERACAO) VALUES "
					+ "(SQ_INVEST_FII.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, investimentoFii.getCarteira().getIdCarteira());
			stmt.setString(2, investimentoFii.getCodigoTicker());
			stmt.setString(3, investimentoFii.getNomeFundo());
			stmt.setString(4, investimentoFii.getTipoOperacao());
			stmt.setDate(5, investimentoFii.getDataOperacao());
			stmt.setInt(6, investimentoFii.getQuantidadeCota());
			stmt.setDouble(7, investimentoFii.getValorOperacao());
			
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
	
	// Retorna todas as investimentoFii da tabela investimentoFii
	@Override
	public List<InvestimentoFii> getAll() {
		
		List<InvestimentoFii> lista = new ArrayList<InvestimentoFii>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_INVEST_FII IF "
					+ "INNER JOIN T_CARTEIRA C ON IF.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "ORDER BY ID_INVESTIMENTO";
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int idInvestimento = rs.getInt("ID_INVESTIMENTO");
				String codigoTicker = rs.getString("CD_TICKER");
				String nomeFundo = rs.getString("NM_FUNDO");
				String tipoOperacao = rs.getString("TP_OPERACAO");
				Date dataOperacao = rs.getDate("DT_OPERACAO");
				int quantidadeCota = rs.getInt("QT_COTA");
				double valorOperacao = rs.getDouble("VL_OPERACAO");
				
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
				
				InvestimentoFii investimentoFii = new InvestimentoFii(idInvestimento, 
						codigoTicker, nomeFundo, tipoOperacao, dataOperacao, quantidadeCota, 
						valorOperacao);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				carteira.setUsuario(usuario);
				investimentoFii.setCarteira(carteira);
				
				lista.add(investimentoFii);
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
	
	// Atualiza uma investimentoFii da tabela investimentoFii
	@Override
	public void atualizar(InvestimentoFii investimentoFii) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_INVEST_FII "
					+ "SET ID_CARTEIRA = ?, CD_TICKER = ?, "
					+ "NM_FUNDO = ?, TP_OPERACAO = ?, DT_OPERACAO = ?, "
					+ "QT_COTA = ?, VL_OPERACAO = ? "
					+ "WHERE ID_INVESTIMENTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, investimentoFii.getCarteira().getIdCarteira());
			stmt.setString(2, investimentoFii.getCodigoTicker());
			stmt.setString(3, investimentoFii.getNomeFundo());
			stmt.setString(4, investimentoFii.getTipoOperacao());
			stmt.setDate(5, investimentoFii.getDataOperacao());
			stmt.setInt(6, investimentoFii.getQuantidadeCota());
			stmt.setDouble(7, investimentoFii.getValorOperacao());
			stmt.setInt(8, investimentoFii.getIdInvestimento());
			
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
	
	// Remover uma investimentoFii da tabela investimentoFii
	@Override
	public void remover(int idInvestimento) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_INVEST_FII "
					+ "WHERE ID_INVESTIMENTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idInvestimento);
			
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
	
	// Retornar uma investimentoFii da tabela investimentoFii
	@Override
	public InvestimentoFii buscarPorId(int idInvestimento) {
		
		InvestimentoFii investimentoFii = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_INVEST_FII IF "
					+ "INNER JOIN T_CARTEIRA C ON IF.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "WHERE ID_INVESTIMENTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idInvestimento);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int _idInvestimento = rs.getInt("ID_INVESTIMENTO");
				String codigoTicker = rs.getString("CD_TICKER");
				String nomeFundo = rs.getString("NM_FUNDO");
				String tipoOperacao = rs.getString("TP_OPERACAO");
				Date dataOperacao = rs.getDate("DT_OPERACAO");
				int quantidadeCota = rs.getInt("QT_COTA");
				double valorOperacao = rs.getDouble("VL_OPERACAO");
				
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
				
				investimentoFii = new InvestimentoFii(_idInvestimento, 
						codigoTicker, nomeFundo, tipoOperacao, dataOperacao, quantidadeCota, 
						valorOperacao);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				carteira.setUsuario(usuario);
				investimentoFii.setCarteira(carteira);				
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
		return investimentoFii;
	}
}

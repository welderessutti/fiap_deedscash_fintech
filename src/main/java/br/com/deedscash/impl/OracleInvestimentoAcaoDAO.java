package br.com.deedscash.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.deedscash.bean.Carteira;
import br.com.deedscash.bean.InvestimentoAcao;
import br.com.deedscash.bean.Usuario;
import br.com.deedscash.dao.InvestimentoAcaoDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.singleton.ConnectionManager;

public class OracleInvestimentoAcaoDAO implements InvestimentoAcaoDAO {
	
	private Connection conexao;
	
	// Insere uma investimentoAcao na tabela investimentoAcao
	@Override
	public void insert(InvestimentoAcao investimentoAcao) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_INVEST_ACAO "
					+ "(ID_INVESTIMENTO, ID_CARTEIRA, "
					+ "CD_TICKER, NM_EMPRESA, TP_OPERACAO, "
					+ "DT_OPERACAO, QT_COTA, VL_OPERACAO) VALUES "
					+ "(SQ_INVEST_ACAO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, investimentoAcao.getCarteira().getIdCarteira());
			stmt.setString(2, investimentoAcao.getCodigoTicker());
			stmt.setString(3, investimentoAcao.getNomeEmpresa());
			stmt.setString(4, investimentoAcao.getTipoOperacao());
			stmt.setDate(5, investimentoAcao.getDataOperacao());
			stmt.setInt(6, investimentoAcao.getQuantidadeCota());
			stmt.setDouble(7, investimentoAcao.getValorOperacao());
			
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
	
	// Retorna todas as investimentoAcao da tabela investimentoAcao
	@Override
	public List<InvestimentoAcao> getAll() {
		
		List<InvestimentoAcao> lista = new ArrayList<InvestimentoAcao>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_INVEST_ACAO IA "
					+ "INNER JOIN T_CARTEIRA C ON IA.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "ORDER BY ID_INVESTIMENTO";
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int idInvestimento = rs.getInt("ID_INVESTIMENTO");
				String codigoTicker = rs.getString("CD_TICKER");
				String nomeEmpresa = rs.getString("NM_EMPRESA");
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
				String emailUsuario = rs.getString("DS_EMAIL");
				String senhaUsuario = rs.getString("DS_SENHA");
				Date dataNascimento = rs.getDate("DT_NASCIMENTO");
				String sexoUsuario = rs.getString("DS_SEXO");
				
				InvestimentoAcao investimentoAcao = new InvestimentoAcao(idInvestimento, 
						codigoTicker, nomeEmpresa, tipoOperacao, dataOperacao, quantidadeCota, 
						valorOperacao);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						emailUsuario, senhaUsuario, dataNascimento, sexoUsuario);
				
				carteira.setUsuario(usuario);
				investimentoAcao.setCarteira(carteira);
				
				lista.add(investimentoAcao);
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
	
	// Atualiza uma investimentoAcao da tabela investimentoAcao
	@Override
	public void atualizar(InvestimentoAcao investimentoAcao) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_INVEST_ACAO "
					+ "SET ID_CARTEIRA = ?, CD_TICKER = ?, "
					+ "NM_EMPRESA = ?, TP_OPERACAO = ?, DT_OPERACAO = ?, "
					+ "QT_COTA = ?, VL_OPERACAO = ? "
					+ "WHERE ID_INVESTIMENTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, investimentoAcao.getCarteira().getIdCarteira());
			stmt.setString(2, investimentoAcao.getCodigoTicker());
			stmt.setString(3, investimentoAcao.getNomeEmpresa());
			stmt.setString(4, investimentoAcao.getTipoOperacao());
			stmt.setDate(5, investimentoAcao.getDataOperacao());
			stmt.setInt(6, investimentoAcao.getQuantidadeCota());
			stmt.setDouble(7, investimentoAcao.getValorOperacao());
			stmt.setInt(8, investimentoAcao.getIdInvestimento());
			
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
	
	// Remover uma investimentoAcao da tabela investimentoAcao
	@Override
	public void remover(int idInvestimento) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_INVEST_ACAO "
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
	
	// Retornar uma investimentoAcao da tabela investimentoAcao
	@Override
	public InvestimentoAcao buscarPorId(int idInvestimento) {
		
		InvestimentoAcao investimentoAcao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_INVEST_ACAO IA "
					+ "INNER JOIN T_CARTEIRA C ON IA.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "WHERE ID_INVESTIMENTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idInvestimento);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int _idInvestimento = rs.getInt("ID_INVESTIMENTO");
				String codigoTicker = rs.getString("CD_TICKER");
				String nomeEmpresa = rs.getString("NM_EMPRESA");
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
				String emailUsuario = rs.getString("DS_EMAIL");
				String senhaUsuario = rs.getString("DS_SENHA");
				Date dataNascimento = rs.getDate("DT_NASCIMENTO");
				String sexoUsuario = rs.getString("DS_SEXO");
				
				investimentoAcao = new InvestimentoAcao(_idInvestimento, 
						codigoTicker, nomeEmpresa, tipoOperacao, dataOperacao, quantidadeCota, 
						valorOperacao);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						emailUsuario, senhaUsuario, dataNascimento, sexoUsuario);
				
				carteira.setUsuario(usuario);
				investimentoAcao.setCarteira(carteira);				
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
		return investimentoAcao;
	}
}

package br.com.deedscash.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.deedscash.bean.Carteira;
import br.com.deedscash.bean.InvestimentoRendaFixa;
import br.com.deedscash.bean.Usuario;
import br.com.deedscash.dao.InvestimentoRendaFixaDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.singleton.ConnectionManager;

public class OracleInvestimentoRendaFixaDAO implements InvestimentoRendaFixaDAO {
	
	private Connection conexao;
	
	// Insere uma investimentoRendaFixa na tabela investimentoRendaFixa
	@Override
	public void insert(InvestimentoRendaFixa investimentoRendaFixa) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_INVEST_RF "
					+ "(ID_INVESTIMENTO, ID_CARTEIRA, "
					+ "NM_CATEGORIA, NM_EMISSOR, TP_INDICE, "
					+ "VL_TAXA, TP_OPERACAO, DT_OPERACAO, "
					+ "QT_COTA, VL_OPERACAO) VALUES "
					+ "(SQ_INVEST_RF.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, investimentoRendaFixa.getCarteira().getIdCarteira());
			stmt.setString(2, investimentoRendaFixa.getNomeCategoria());
			stmt.setString(3, investimentoRendaFixa.getNomeEmissor());
			stmt.setString(4, investimentoRendaFixa.getTipoIndice());
			stmt.setDouble(5, investimentoRendaFixa.getValorTaxa());
			stmt.setString(6, investimentoRendaFixa.getTipoOperacao());
			stmt.setDate(7, investimentoRendaFixa.getDataOperacao());
			stmt.setInt(8, investimentoRendaFixa.getQuantidadeCota());
			stmt.setDouble(9, investimentoRendaFixa.getValorOperacao());
			
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
	
	// Retorna todas as investimentoRendaFixa da tabela investimentoRendaFixa
	@Override
	public List<InvestimentoRendaFixa> getAll() {
		
		List<InvestimentoRendaFixa> lista = new ArrayList<InvestimentoRendaFixa>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_INVEST_RF IRF "
					+ "INNER JOIN T_CARTEIRA C ON IRF.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "ORDER BY ID_INVESTIMENTO";
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int idInvestimento = rs.getInt("ID_INVESTIMENTO");
				String nomeCategoria = rs.getString("NM_CATEGORIA");
				String nomeEmissor = rs.getString("NM_EMISSOR");
				String tipoIndice = rs.getString("TP_INDICE");
				double valorTaxa = rs.getDouble("VL_TAXA");
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
				
				InvestimentoRendaFixa investimentoRendaFixa = new InvestimentoRendaFixa(
						idInvestimento, nomeCategoria, 
						nomeEmissor, tipoIndice, valorTaxa, tipoOperacao, 
						dataOperacao, quantidadeCota, valorOperacao);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				carteira.setUsuario(usuario);
				investimentoRendaFixa.setCarteira(carteira);
				
				lista.add(investimentoRendaFixa);
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
	
	// Atualiza uma investimentoRendaFixa da tabela investimentoRendaFixa
	@Override
	public void atualizar(InvestimentoRendaFixa investimentoRendaFixa) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_INVEST_RF "
					+ "SET ID_CARTEIRA = ?, NM_CATEGORIA = ?, NM_EMISSOR = ?, "
					+ "TP_INDICE = ?, VL_TAXA = ?, TP_OPERACAO = ?, "
					+ "DT_OPERACAO = ?, QT_COTA = ?, VL_OPERACAO = ? "
					+ "WHERE ID_INVESTIMENTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, investimentoRendaFixa.getCarteira().getIdCarteira());
			stmt.setString(2, investimentoRendaFixa.getNomeCategoria());
			stmt.setString(3, investimentoRendaFixa.getNomeEmissor());
			stmt.setString(4, investimentoRendaFixa.getTipoIndice());
			stmt.setDouble(5, investimentoRendaFixa.getValorTaxa());
			stmt.setString(6, investimentoRendaFixa.getTipoOperacao());
			stmt.setDate(7, investimentoRendaFixa.getDataOperacao());
			stmt.setInt(8, investimentoRendaFixa.getQuantidadeCota());
			stmt.setDouble(9, investimentoRendaFixa.getValorOperacao());
			stmt.setInt(10, investimentoRendaFixa.getIdInvestimento());
			
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
	
	// Remover uma investimentoRendaFixa da tabela investimentoRendaFixa
	@Override
	public void remover(int idInvestimento) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_INVEST_RF "
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
	
	// Retornar uma investimentoRendaFixa da tabela investimentoRendaFixa
	@Override
	public InvestimentoRendaFixa buscarPorId(int idInvestimento) {
		
		InvestimentoRendaFixa investimentoRendaFixa = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_INVEST_RF IRF "
					+ "INNER JOIN T_CARTEIRA C ON IRF.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "WHERE ID_INVESTIMENTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idInvestimento);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int _idInvestimento = rs.getInt("ID_INVESTIMENTO");
				String nomeCategoria = rs.getString("NM_CATEGORIA");
				String nomeEmissor = rs.getString("NM_EMISSOR");
				String tipoIndice = rs.getString("TP_INDICE");
				double valorTaxa = rs.getDouble("VL_TAXA");
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
				
				investimentoRendaFixa = new InvestimentoRendaFixa(
						_idInvestimento, nomeCategoria, 
						nomeEmissor, tipoIndice, valorTaxa, tipoOperacao, 
						dataOperacao, quantidadeCota, valorOperacao);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						email, senha, dataNascimento, genero);
				
				carteira.setUsuario(usuario);
				investimentoRendaFixa.setCarteira(carteira);
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
		return investimentoRendaFixa;
	}
}

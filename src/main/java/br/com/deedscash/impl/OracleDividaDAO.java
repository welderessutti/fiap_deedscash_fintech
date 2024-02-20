package br.com.deedscash.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.deedscash.bean.Carteira;
import br.com.deedscash.bean.Divida;
import br.com.deedscash.bean.Usuario;
import br.com.deedscash.dao.DividaDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.singleton.ConnectionManager;

public class OracleDividaDAO implements DividaDAO {
	
	private Connection conexao;
	
	// Insere uma divida na tabela divida
	@Override
	public void insert(Divida divida) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_DIVIDA "
					+ "(ID_DIVIDA, ID_CARTEIRA, "
					+ "TP_DIVIDA, DT_INICIO, DT_VENCIMENTO, "
					+ "QT_PARCELAS, VL_DIVIDA, VL_TAXA, DS_DIVIDA) VALUES "
					+ "(SQ_DIVIDA.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, divida.getCarteira().getIdCarteira());
			stmt.setString(2, divida.getTipoDivida());
			stmt.setDate(3, divida.getDataInicio());
			stmt.setDate(4, divida.getDataVencimento());
			stmt.setInt(5, divida.getQuantidadeParcelas());
			stmt.setDouble(6, divida.getValorDivida());
			stmt.setDouble(7, divida.getValorTaxa());
			stmt.setString(8, divida.getDescricaoDivida());
			
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
	
	// Retorna todas as divida da tabela divida
	@Override
	public List<Divida> getAll() {
		
		List<Divida> lista = new ArrayList<Divida>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_DIVIDA D"
					+ "INNER JOIN T_CARTEIRA C ON D.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "ORDER BY ID_DIVIDA";
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int idDivida = rs.getInt("ID_DIVIDA");
				String tipoDivida = rs.getString("TP_DIVIDA");
				Date dataInicio = rs.getDate("DT_INICIO");
				Date dataVencimento = rs.getDate("DT_VENCIMENTO");
				int quantidadeParcelas = rs.getInt("QT_PARCELAS");
				double valorDivida = rs.getDouble("VL_DIVIDA");
				double valorTaxa = rs.getDouble("VL_TAXA");
				String descricaoDivida = rs.getString("DS_DIVIDA");
				
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
				
				Divida divida = new Divida(idDivida, tipoDivida, 
						dataInicio, dataVencimento, quantidadeParcelas, valorDivida,
						valorTaxa, descricaoDivida);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						emailUsuario, senhaUsuario, dataNascimento, sexoUsuario);
				
				carteira.setUsuario(usuario);
				divida.setCarteira(carteira);
				
				lista.add(divida);
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
	
	// Atualiza uma divida da tabela divida
	@Override
	public void atualizar(Divida divida) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_DIVIDA "
					+ "SET ID_CARTEIRA = ?, TP_DIVIDA = ?, "
					+ "DT_INICIO = ?, DT_VENCIMENTO = ?, QT_PARCELAS = ?, "
					+ "VL_DIVIDA = ?, VL_TAXA = ?, DS_DIVIDA = ? "
					+ "WHERE ID_DIVIDA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, divida.getCarteira().getIdCarteira());
			stmt.setString(2, divida.getTipoDivida());
			stmt.setDate(3, divida.getDataInicio());
			stmt.setDate(4, divida.getDataVencimento());
			stmt.setInt(5, divida.getQuantidadeParcelas());
			stmt.setDouble(6, divida.getValorDivida());
			stmt.setDouble(7, divida.getValorTaxa());
			stmt.setString(8, divida.getDescricaoDivida());
			stmt.setInt(9, divida.getIdDivida());
			
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
	
	// Remover uma divida da tabela divida
	@Override
	public void remover(int idDivida) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_DIVIDA "
					+ "WHERE ID_DIVIDA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idDivida);
			
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
	
	// Retornar uma divida da tabela divida
	@Override
	public Divida buscarPorId(int idDivida) {
		
		Divida divida = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_DIVIDA D"
					+ "INNER JOIN T_CARTEIRA C ON D.ID_CARTEIRA = C.ID_CARTEIRA "
					+ "INNER JOIN T_USUARIO U ON C.ID_USUARIO = U.ID_USUARIO "
					+ "WHERE ID_DIVIDA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idDivida);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int _idDivida = rs.getInt("ID_DIVIDA");
				String tipoDivida = rs.getString("TP_DIVIDA");
				Date dataInicio = rs.getDate("DT_INICIO");
				Date dataVencimento = rs.getDate("DT_VENCIMENTO");
				int quantidadeParcelas = rs.getInt("QT_PARCELAS");
				double valorDivida = rs.getDouble("VL_DIVIDA");
				double valorTaxa = rs.getDouble("VL_TAXA");
				String descricaoDivida = rs.getString("DS_DIVIDA");
				
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
				
				divida = new Divida(_idDivida, tipoDivida, 
						dataInicio, dataVencimento, quantidadeParcelas, valorDivida,
						valorTaxa, descricaoDivida);
				Carteira carteira = new Carteira(idCarteira, tipoCarteira, nomeBanco, 
						valorSaldo, descricaoCarteira);
				Usuario usuario = new Usuario(idUsuario, nomeUsuario, sobrenomeUsuario, 
						emailUsuario, senhaUsuario, dataNascimento, sexoUsuario);
				
				carteira.setUsuario(usuario);
				divida.setCarteira(carteira);
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
		return divida;
	}
}

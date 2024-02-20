package br.com.deedscash.factory;

import br.com.deedscash.dao.CartaoCreditoDAO;
import br.com.deedscash.dao.CarteiraDAO;
import br.com.deedscash.dao.DividaDAO;
import br.com.deedscash.dao.InvestimentoAcaoDAO;
import br.com.deedscash.dao.InvestimentoFiiDAO;
import br.com.deedscash.dao.InvestimentoRendaFixaDAO;
import br.com.deedscash.dao.MovimentacaoDAO;
import br.com.deedscash.dao.ObjetivoDAO;
import br.com.deedscash.dao.UsuarioDAO;
import br.com.deedscash.impl.OracleCartaoCreditoDAO;
import br.com.deedscash.impl.OracleCarteiraDAO;
import br.com.deedscash.impl.OracleDividaDAO;
import br.com.deedscash.impl.OracleInvestimentoAcaoDAO;
import br.com.deedscash.impl.OracleInvestimentoFiiDAO;
import br.com.deedscash.impl.OracleInvestimentoRendaFixaDAO;
import br.com.deedscash.impl.OracleMovimentacaoDAO;
import br.com.deedscash.impl.OracleObjetivoDAO;
import br.com.deedscash.impl.OracleUsuarioDAO;

public class DAOFactory {
	
	public static CartaoCreditoDAO getCartaoCreditoDAO() {
		return new OracleCartaoCreditoDAO();
	}
	
	public static CarteiraDAO getCarteiraDAO() {
		return new OracleCarteiraDAO();
	}
	
	public static DividaDAO getDividaDAO() {
		return new OracleDividaDAO();
	}
	
	public static InvestimentoAcaoDAO getInvestimentoAcaoDAO() {
		return new OracleInvestimentoAcaoDAO();
	}
	
	public static InvestimentoFiiDAO getInvestimentoFiiDAO() {
		return new OracleInvestimentoFiiDAO();
	}
	
	public static InvestimentoRendaFixaDAO getInvestimentoRendaFixaDAO() {
		return new OracleInvestimentoRendaFixaDAO();
	}
	
	public static MovimentacaoDAO getMovimentacaoDAO() {
		return new OracleMovimentacaoDAO();
	}
	
	public static ObjetivoDAO getObjetivoDAO() {
		return new OracleObjetivoDAO();
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}
}

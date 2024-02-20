package br.com.deedscash.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.deedscash.bean.Carteira;
import br.com.deedscash.bean.Movimentacao;
import br.com.deedscash.dao.MovimentacaoDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.factory.DAOFactory;

@WebServlet("/movimentacao")
public class MovimentacaoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private MovimentacaoDAO dao;

    public MovimentacaoServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	dao = DAOFactory.getMovimentacaoDAO();
    }
    
// GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getAttribute("acao");
		
		if (acao == null) {
			acao = request.getParameter("acao");
		}
		
		switch (acao) {
		
		case "carregar":
			listarMovimentacoes(request, response);
			break;
			
		case "editarMovimentacao":
			editarMovimentacao(request, response);
			break;
		}
	}

	private void editarMovimentacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idMovimentacao = Integer.parseInt(request.getParameter("idMovimentacao"));
		Movimentacao movimentacao = dao.buscarPorId(idMovimentacao);
		
		request.setAttribute("movimentacao", movimentacao);
		request.getRequestDispatcher("editar-movimentacao.jsp").forward(request, response);
	}

	private void listarMovimentacoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Carteira> carteirasUsuario = new ArrayList<Carteira>();
		List<List<Movimentacao>> listaDeListaMovimentacoes = new ArrayList<>();
		HttpSession session = request.getSession();
		carteirasUsuario = (List<Carteira>) session.getAttribute("carteirasUsuario");
		
		for (Carteira carteira: carteirasUsuario) {
			List<Movimentacao> movimentacoesCarteira = new ArrayList<Movimentacao>();
			int idCarteira = carteira.getIdCarteira();
			movimentacoesCarteira = dao.buscarPorIdCarteira(idCarteira);
			
			if (!movimentacoesCarteira.isEmpty()) {
				listaDeListaMovimentacoes.add(movimentacoesCarteira);
			}
		}
		
		if (!listaDeListaMovimentacoes.isEmpty()) {
			session.setAttribute("movimentacoesCarteiras", listaDeListaMovimentacoes);
		}
		response.sendRedirect("main.jsp");
	}

// POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		switch (acao) {
		
		case "editar":
			
			atualizarMovimentacao(request, response);
			break;
			
		case "cadastrarMovimentacao":
			
			cadastrarMovimentacao(request, response);
			break;
		}
	}

	private void cadastrarMovimentacao(HttpServletRequest request, HttpServletResponse response) {
		
		Movimentacao movimentacao = null;
		Carteira carteira = new Carteira();
		
		try {
			
			int idCarteira = Integer.parseInt(request.getParameter("idCarteira"));
			double valorSaldo = Double.parseDouble(request.getParameter("valorSaldo"));
			String tipoMovimentacao = request.getParameter("tipoMovimentacao");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dataUtil = format.parse(request.getParameter("dataHoraMovimentacao"));
			java.sql.Date dataHoraMovimentacao = new java.sql.Date(dataUtil.getTime());
			double valorMovimentacao = Double.parseDouble(request.getParameter("valorMovimentacao"));
			String descricaoMovimentacao = request.getParameter("descricaoMovimentacao");
			
			movimentacao = new Movimentacao(tipoMovimentacao, dataHoraMovimentacao, 
					valorMovimentacao, descricaoMovimentacao);
			carteira.setIdCarteira(idCarteira);
			movimentacao.setCarteira(carteira);
			
			dao.insert(movimentacao);
			
			request.setAttribute("idCarteira", idCarteira);
			request.setAttribute("valorSaldo", valorSaldo);
			request.setAttribute("tipoMovimentacao", tipoMovimentacao);
			request.setAttribute("valorMovimentacao", valorMovimentacao);
			request.setAttribute("acao", "atualizarSaldo");
			
			request.getRequestDispatcher("carteira").forward(request, response);
			
		} catch (DBException db) {
			
			db.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	private void atualizarMovimentacao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Movimentacao movimentacao = null;
		Carteira carteira = new Carteira();
		
		try {
			
			int idMovimentacao = Integer.parseInt(request.getParameter("idMovimentacao"));
			int idCarteira = Integer.parseInt(request.getParameter("idCarteira"));
			String tipoMovimentacao = request.getParameter("tipoMovimentacao");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dataUtil = format.parse(request.getParameter("dataHoraMovimentacao"));
			java.sql.Date dataHoraMovimentacao = new java.sql.Date(dataUtil.getTime());
			double valorMovimentacao = Double.parseDouble(request.getParameter("valorMovimentacao"));
			String descricaoMovimentacao = request.getParameter("descricaoMovimentacao");
			
			movimentacao = new Movimentacao(idMovimentacao, tipoMovimentacao, dataHoraMovimentacao, 
					valorMovimentacao, descricaoMovimentacao);
			carteira.setIdCarteira(idCarteira);
			movimentacao.setCarteira(carteira);
			
			dao.atualizar(movimentacao);
			
		} catch (DBException db) {
			
			db.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		response.sendRedirect("main");
	}
}

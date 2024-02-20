package br.com.deedscash.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.deedscash.bean.Carteira;
import br.com.deedscash.bean.Usuario;
import br.com.deedscash.dao.CarteiraDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.factory.DAOFactory;

@WebServlet("/carteira")
public class CarteiraServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private CarteiraDAO dao;

    public CarteiraServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	dao = DAOFactory.getCarteiraDAO();
    }
    
// GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getAttribute("acao");

		if (acao == null) {
			acao = request.getParameter("acao");
		}
		
		switch (acao) {
		
		case "carregar":
			listarCarteiras(request, response);
			break;
		
		case "cadastroMovimentacao":
			
			HttpSession session = request.getSession();
			List<Carteira> carteirasUsuario = (ArrayList<Carteira>) session.getAttribute("carteirasUsuario");
			Carteira carteira = carteirasUsuario.get(0);
			
			request.setAttribute("carteira", carteira);
			request.getRequestDispatcher("cadastro-movimentacao.jsp").forward(request, response);
			break;
		}
	}

	private void listarCarteiras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Carteira> listaCarteiras = new ArrayList<Carteira>();
		HttpSession session = request.getSession();
		Usuario usuarioCarteira = (Usuario) session.getAttribute("usuarioSessao");
		
		int idUsuarioCarteira = usuarioCarteira.getIdUsuario();
		listaCarteiras = dao.buscarPorIdUsuario(idUsuarioCarteira);
		
		if (!listaCarteiras.isEmpty()) {
			
			session.setAttribute("carteirasUsuario", listaCarteiras);
			
			request.getRequestDispatcher("movimentacao").forward(request, response);
			
		} else {
			// Preciso implementar no JSP a validação da variável "usuarioVazio" caso ele não tenha carteira cadastrada
			// criar um elemento do tipo "Select" para ele selecionar as carteiras disponíveis
			request.setAttribute("usuarioVazio", "Usuário ainda não possui carteira");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}
	}
	
// POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acaoHttp = request.getParameter("acao");
		String acaoServlet = (String) request.getAttribute("acao");
		
		String acao;
		
		if (acaoServlet != null) {
			acao = acaoServlet;
		} else {
			acao = acaoHttp;
		}
		
		switch (acao) {
		
		case "cadastrar":
			
			cadastrarCarteira(request, response);
			break;
		
		case "atualizarSaldo":
			
			atualizarSaldoCarteira(request, response);
			break;
		}
	}

	private void atualizarSaldoCarteira(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			
			int idCarteira = Integer.parseInt(request.getParameter("idCarteira"));
			String tipoMovimentacao = request.getParameter("tipoMovimentacao");
			double valorSaldo = Double.parseDouble(request.getParameter("valorSaldo"));
			double valorMovimentacao = Double.parseDouble(request.getParameter("valorMovimentacao"));
			
			Carteira carteira = new Carteira(idCarteira, valorSaldo);
			carteira.ajustaSaldo(tipoMovimentacao, valorMovimentacao);
			
			dao.atualizarSaldo(carteira);

		} catch (DBException db) {
			
			db.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		response.sendRedirect("main");
	}

	private void cadastrarCarteira(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Usuario usuarioCarteira = (Usuario) request.getAttribute("usuarioCarteira");
			Carteira carteiraUsuario = new Carteira("Conta Corrente", "Banco X", 0.0, "Minha carteira");
			carteiraUsuario.setUsuario(usuarioCarteira);
			
			dao.insert(carteiraUsuario);
			
		} catch (DBException db) {
			
			db.printStackTrace();
			request.setAttribute("erro", "Ocorreu um erro ao cadastrar a carteira!");
			
		}
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}
}

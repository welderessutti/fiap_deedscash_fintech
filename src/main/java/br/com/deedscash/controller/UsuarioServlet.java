package br.com.deedscash.controller;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.deedscash.bean.Usuario;
import br.com.deedscash.dao.UsuarioDAO;
import br.com.deedscash.exception.DBException;
import br.com.deedscash.factory.DAOFactory;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao;

    public UsuarioServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	dao = DAOFactory.getUsuarioDAO();
    }
    
// GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

// POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		switch (acao) {
		
		case "cadastrar":			
			cadastrarUsuario(request, response);
			break;
		}
	}

	private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {				
			
			String nome = request.getParameter("nome");
			String sobrenome = request.getParameter("sobrenome");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dataUtil = format.parse(request.getParameter("nascimento"));
			java.sql.Date dataNascimento = new java.sql.Date(dataUtil.getTime());				
			String genero = request.getParameter("genero");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			if (dao.verificarEmailExistente(email)) {
				
				request.setAttribute("erro", "E-mail existente!");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
				
			} else {
				
				Usuario usuarioCadastrar = new Usuario(nome, sobrenome, email, senha, dataNascimento, genero);
				dao.insert(usuarioCadastrar);
				
				Usuario usuarioCarteira = dao.buscarPorEmail(email);
				request.setAttribute("usuarioCarteira", usuarioCarteira);
				request.setAttribute("acao", "cadastrar");
				request.setAttribute("msg", "Usuário cadastrado com sucesso!");
				
				request.getRequestDispatcher("/carteira").forward(request, response);
			}
			
		} catch (DBException db) {
			
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar usuário!");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		    
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados!");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}
}
